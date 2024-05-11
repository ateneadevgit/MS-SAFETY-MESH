package com.fusm.safety_mesh.service;

import com.fusm.safety_mesh.entity.Role;
import com.fusm.safety_mesh.model.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IPermissionService {

    List<PermissionModel> getPermissionByRole(Integer roleId);
    void createPermission(PermissionRequest permissionRequest);
    void updatePermission(PermissionRequest permissionRequest);
    List<MenuModel> getModuleByRole(Integer roleId);
    List<MenuModel> getFloatingModuleByRole(Integer roleId);
    Boolean roleHasWrite(ValidatePermission validatePermission);
    Boolean roleHasView(ValidatePermission validatePermission);
    Boolean roleHasEdit(ValidatePermission validatePermission);
    Boolean roleHasDelete(ValidatePermission validatePermission);
    List<RolePermissionModel> getPermissionsByModule(Integer moduleId);

}
