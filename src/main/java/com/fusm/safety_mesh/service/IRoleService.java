package com.fusm.safety_mesh.service;

import com.fusm.safety_mesh.entity.Role;
import com.fusm.safety_mesh.model.RoleRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IRoleService {

    List<Role> getRoles();
    void createRole(RoleRequest roleRequest);
    void updateRole(RoleRequest roleRequest, Integer roleId);
    void enableDisableRole(Integer roleId, Boolean enabled);
    List<Role> getRolesWithNotPermissionInModule(Integer moduleId);

}
