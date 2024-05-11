package com.fusm.safety_mesh.repository;

import com.fusm.safety_mesh.dto.ModuleDto;
import com.fusm.safety_mesh.entity.Permission;
import com.fusm.safety_mesh.entity.PermissionPKId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IPermissionRepository extends JpaRepository<Permission, PermissionPKId> {

    @Query(
            value = "select * from permission " +
                    "where role_id = :rolId " +
                    "order by created_at desc ",
            nativeQuery = true
    )
    List<Permission> findAllPermissionByRole(@Param("rolId") Integer roleId);

    @Query(
            value = "select  " +
                    "module.id_module, " +
                    "module.name, " +
                    "module.icon, " +
                    "module.path, " +
                    "module.module_father, " +
                    "module.menu_order, " +
                    "module.color," +
                    "permission.can_create, " +
                    "permission.can_view, " +
                    "permission.can_delete, " +
                    "permission.can_update " +
                    "from permission " +
                    "join module on module.id_module = permission.module_id " +
                    "where permission.role_id = :rolId " +
                    "and can_view = true " +
                    "and module.is_menu = true " +
                    "order by module.menu_order asc",
            nativeQuery = true
    )
    List<ModuleDto> getModuleByRole(@Param("rolId") Integer roleId);

    @Query(
            value = "select  " +
                    "module.id_module, " +
                    "module.name, " +
                    "module.icon, " +
                    "module.path, " +
                    "module.module_father, " +
                    "module.menu_order, " +
                    "module.color, " +
                    "permission.can_create, " +
                    "permission.can_view, " +
                    "permission.can_delete, " +
                    "permission.can_update " +
                    "from permission " +
                    "join module on module.id_module = permission.module_id " +
                    "where permission.role_id = :rolId " +
                    "and can_view = true " +
                    "and module.is_floating = true " +
                    "order by module.menu_order asc",
            nativeQuery = true
    )
    List<ModuleDto> getFloatingModuleByRole(@Param("rolId") Integer roleId);

    Optional<Permission> findByPermissionPKId_ModuleId_ModuleIdAndPermissionPKId_RoleId_RoleIdAndCanView(
            Integer moduleId, Integer roleId, Boolean canView
    );

    Optional<Permission> findByPermissionPKId_ModuleId_ModuleIdAndPermissionPKId_RoleId_RoleIdAndCanCreate(
            Integer moduleId, Integer roleId, Boolean canCreate
    );

    Optional<Permission> findByPermissionPKId_ModuleId_ModuleIdAndPermissionPKId_RoleId_RoleIdAndCanUpdate(
            Integer moduleId, Integer roleId, Boolean canUpdate
    );

    Optional<Permission> findByPermissionPKId_ModuleId_ModuleIdAndPermissionPKId_RoleId_RoleIdAndCanDelete(
            Integer moduleId, Integer roleId, Boolean canDelete
    );

    @Query(
            value = "select * from permission " +
                    "join module on module.id_module = permission.module_id " +
                    "where module.id_module = :moduleId " +
                    "order by permission.created_at desc ",
            nativeQuery = true
    )
    List<Permission> getModulePermission(
            @Param("moduleId") Integer moduleId
    );

}
