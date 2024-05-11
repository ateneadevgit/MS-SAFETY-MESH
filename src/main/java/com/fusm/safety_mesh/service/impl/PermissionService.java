package com.fusm.safety_mesh.service.impl;

import com.fusm.safety_mesh.dto.ModuleDto;
import com.fusm.safety_mesh.dto.ModuleDtoImpl;
import com.fusm.safety_mesh.entity.Module;
import com.fusm.safety_mesh.entity.Permission;
import com.fusm.safety_mesh.entity.PermissionPKId;
import com.fusm.safety_mesh.entity.Role;
import com.fusm.safety_mesh.model.*;
import com.fusm.safety_mesh.repository.IModuleRepository;
import com.fusm.safety_mesh.repository.IPermissionRepository;
import com.fusm.safety_mesh.repository.IRoleRepository;
import com.fusm.safety_mesh.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PermissionService implements IPermissionService {

    @Autowired
    private IPermissionRepository permissionRepository;

    @Autowired
    private IModuleRepository moduleRepository;

    @Autowired
    private IRoleRepository roleRepository;


    @Override
    public List<PermissionModel> getPermissionByRole(Integer roleId) {
        return permissionRepository.findAllPermissionByRole(roleId).stream().map(
                permission -> PermissionModel.builder()
                        .moduleId(permission.getPermissionPKId().getModuleId().getModuleId())
                        .module(permission.getPermissionPKId().getModuleId().getName())
                        .roleId(permission.getPermissionPKId().getRoleId().getRoleId())
                        .role(permission.getPermissionPKId().getRoleId().getNameRole())
                        .hasWrite(permission.getCanCreate())
                        .hasView(permission.getCanView())
                        .hasEdit(permission.getCanUpdate())
                        .hasDelete(permission.getCanDelete())
                        .build()
        ).toList();
    }

    @Override
    public void createPermission(PermissionRequest permissionRequest) {
        Optional<Role> roleOptional = roleRepository.findById(permissionRequest.getRoleId());
        Optional<Module> moduleOptional = moduleRepository.findById(permissionRequest.getModuleId());
        if (roleOptional.isPresent() && moduleOptional.isPresent()) {
            PermissionPKId permissionPKId = PermissionPKId.builder()
                    .moduleId(moduleOptional.get())
                    .roleId(roleOptional.get())
                    .build();
            savePermission(permissionPKId, permissionRequest);

            if (moduleOptional.get().getModuleFather() != null) {
                PermissionPKId permissionFatherPKId = PermissionPKId.builder()
                        .moduleId(moduleOptional.get().getModuleFather())
                        .roleId(roleOptional.get())
                        .build();
                savePermission(permissionFatherPKId, permissionRequest);
            }
        }

    }

    private void savePermission(PermissionPKId permissionPKId, PermissionRequest permissionRequest) {
        permissionRepository.save(
                Permission.builder()
                        .permissionPKId(permissionPKId)
                        .canView(permissionRequest.getHasView())
                        .canCreate(permissionRequest.getHasWrite())
                        .canUpdate(permissionRequest.getHasEdit())
                        .canDelete(permissionRequest.getHasDelete())
                        .createdAt(new Date())
                        .createdBy(permissionRequest.getCreatedBy())
                        .build()
        );
    }

    @Override
    public void updatePermission(PermissionRequest permissionRequest) {
        Optional<Role> roleOptional = roleRepository.findById(permissionRequest.getRoleId());
        Optional<Module> moduleOptional = moduleRepository.findById(permissionRequest.getModuleId());
        if (roleOptional.isPresent() && moduleOptional.isPresent()) {
            PermissionPKId permissionPKId = PermissionPKId.builder()
                    .moduleId(moduleOptional.get())
                    .roleId(roleOptional.get())
                    .build();
            Optional<Permission> permissionOptional =  permissionRepository.findById(permissionPKId);
            Permission permission = null;
            if (permissionOptional.isPresent()) {
                permission = permissionOptional.get();
                permission.setCanView(permissionRequest.getHasView());
                permission.setCanCreate(permissionRequest.getHasWrite());
                permission.setCanUpdate(permissionRequest.getHasEdit());
                permission.setCanDelete(permissionRequest.getHasDelete());
                permission.setUpdatedAt(new Date());
                permissionRepository.save(permission);
            }
        }
    }

    @Override
    public List<MenuModel> getModuleByRole(Integer roleId) {

        List<ModuleDto> moduleList = permissionRepository.getModuleByRole(roleId);
        Map<Integer, MenuModel> moduleFather = new HashMap<>();

        for (ModuleDto module : moduleList) {
            if (module.getModuleFather() == null) {
                moduleFather.put(module.getIdModule(),
                        MenuModel.builder()
                                .label(module.getName())
                                .icon(module.getIcon())
                                .routerLink(module.getPath())
                                .order(module.getMenuOrder())
                                .color(module.getColor())
                                .items(new ArrayList<>())
                                .canCreate(module.getCanCreate())
                                .canDelete(module.getCanDelete())
                                .canView(module.getCanView())
                                .canUpdate(module.getCanUpdate())
                                .build()
                );
            }
        }

        for (ModuleDto module: moduleList) {
            if (module.getModuleFather() != null) {
                MenuModel fatherModule = moduleFather.get(module.getModuleFather());
                if (fatherModule!= null) fatherModule.getItems().add(
                        MenuModel.builder()
                                .label(module.getName())
                                .icon(module.getIcon())
                                .routerLink(module.getPath())
                                .order(module.getMenuOrder())
                                .color(module.getColor())
                                .canCreate(module.getCanCreate())
                                .canDelete(module.getCanDelete())
                                .canView(module.getCanView())
                                .canUpdate(module.getCanUpdate())
                                .build()
                );
            }
        }

        ArrayList<MenuModel> menuList = new ArrayList<>(moduleFather.values());
        menuList.sort(Comparator.comparingInt(MenuModel::getOrder));
        return menuList;
    }

    @Override
    public List<MenuModel> getFloatingModuleByRole(Integer roleId) {
        return permissionRepository.getFloatingModuleByRole(roleId).stream().map(
                moduleDto -> MenuModel.builder()
                        .label(moduleDto.getName())
                        .icon(moduleDto.getIcon())
                        .routerLink(moduleDto.getPath())
                        .order(moduleDto.getMenuOrder())
                        .color(moduleDto.getColor())
                        .items(new ArrayList<>())
                        .canCreate(moduleDto.getCanCreate())
                        .canDelete(moduleDto.getCanDelete())
                        .canView(moduleDto.getCanView())
                        .canUpdate(moduleDto.getCanUpdate())
                        .build()
        ).toList();
    }

    @Override
    public Boolean roleHasWrite(ValidatePermission validatePermission) {
        Optional<Permission> hasWrite = permissionRepository
                .findByPermissionPKId_ModuleId_ModuleIdAndPermissionPKId_RoleId_RoleIdAndCanCreate(
                        validatePermission.getModuleId(), validatePermission.getRoleId(), true
                );
        return hasWrite.isPresent();
    }

    @Override
    public Boolean roleHasView(ValidatePermission validatePermission) {
        Optional<Permission> hasView = permissionRepository
                .findByPermissionPKId_ModuleId_ModuleIdAndPermissionPKId_RoleId_RoleIdAndCanView(
                        validatePermission.getModuleId(), validatePermission.getRoleId(), true
                );
        return hasView.isPresent();
    }

    @Override
    public Boolean roleHasEdit(ValidatePermission validatePermission) {
        Optional<Permission> hasEdit = permissionRepository
                .findByPermissionPKId_ModuleId_ModuleIdAndPermissionPKId_RoleId_RoleIdAndCanUpdate(
                        validatePermission.getModuleId(), validatePermission.getRoleId(), true
                );
        return hasEdit.isPresent();
    }

    @Override
    public Boolean roleHasDelete(ValidatePermission validatePermission) {
        Optional<Permission> hasDelete = permissionRepository
                .findByPermissionPKId_ModuleId_ModuleIdAndPermissionPKId_RoleId_RoleIdAndCanDelete(
                        validatePermission.getModuleId(), validatePermission.getRoleId(), true
                );
        return hasDelete.isPresent();
    }

    @Override
    public List<RolePermissionModel> getPermissionsByModule(Integer moduleId) {
        return permissionRepository.getModulePermission(moduleId).stream().map(
                permission -> RolePermissionModel.builder()
                        .moduleId(moduleId)
                        .roleId(permission.getPermissionPKId().getRoleId().getRoleId())
                        .canCreate(permission.getCanCreate())
                        .canUpdate(permission.getCanUpdate())
                        .canView(permission.getCanView())
                        .canDelete(permission.getCanDelete())
                        .roleName(permission.getPermissionPKId().getRoleId().getNameRole())
                        .build()
        ).toList();
    }

}
