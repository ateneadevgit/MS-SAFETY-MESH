package com.fusm.safety_mesh.service.impl;

import com.fusm.safety_mesh.entity.Role;
import com.fusm.safety_mesh.model.RoleRequest;
import com.fusm.safety_mesh.repository.IRoleRepository;
import com.fusm.safety_mesh.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class RoleService implements IRoleService {

    @Autowired
    private IRoleRepository roleRepository;


    @Override
    public List<Role> getRoles() {
        return roleRepository.findAllRolesOrdered();
    }

    @Override
    public void createRole(RoleRequest roleRequest) {
        roleRepository.save(
                Role.builder()
                        .nameRole(roleRequest.getNameRole())
                        .sinuId(roleRequest.getSinuId())
                        .description(roleRequest.getDescription())
                        .createdAt(new Date())
                        .createdBy(roleRequest.getCreatedBy())
                        .enabled(true)
                        .build()
        );
    }

    @Override
    public void updateRole(RoleRequest roleRequest, Integer roleId) {
        Optional<Role> roleOptional = roleRepository.findById(roleId);
        if (roleOptional.isPresent()) {
            Role role = roleOptional.get();
            role.setNameRole(roleRequest.getNameRole());
            role.setSinuId(roleRequest.getSinuId());
            role.setDescription(roleRequest.getDescription());
            role.setUpdatedAt(new Date());
            roleRepository.save(role);
        }
    }

    @Override
    public void enableDisableRole(Integer roleId, Boolean enabled) {
        Optional<Role> roleOptional = roleRepository.findById(roleId);
        if (roleOptional.isPresent()) {
            Role role = roleOptional.get();
            role.setEnabled(enabled);
            role.setUpdatedAt(new Date());
            roleRepository.save(role);
        }
    }

    @Override
    public List<Role> getRolesWithNotPermissionInModule(Integer moduleId) {
        return roleRepository.findAllRolesWithNotPermissionInModule(moduleId);
    }

}
