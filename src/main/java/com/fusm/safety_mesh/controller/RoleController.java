package com.fusm.safety_mesh.controller;

import com.fusm.safety_mesh.entity.Role;
import com.fusm.safety_mesh.model.RoleRequest;
import com.fusm.safety_mesh.service.IRoleService;
import com.fusm.safety_mesh.util.AppRoutes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Clase que expone los servicios relacionados con los roles que interactúan con la aplicación
 * ITSense Inc - Andrea Gómez
 */

@RestController
@RequestMapping(value = AppRoutes.ROLE_ROUTE)
public class RoleController {

    @Autowired
    private IRoleService roleService;


    /**
     * Obtiene una lista de los roles
     * @return lista de roles
     */
    @GetMapping
    public ResponseEntity<List<Role>> getRoles() {
        return ResponseEntity.ok(roleService.getRoles());
    }

    /**
     * Crea un nuevo rol
     * @param roleRequest Modelo que contiene la información para crear un nuevo rol
     * @return OK
     */
    @PostMapping
    public ResponseEntity<String> createRole(
            @RequestBody RoleRequest roleRequest
            ) {
        roleService.createRole(roleRequest);
        return ResponseEntity.ok(HttpStatus.OK.getReasonPhrase());
    }

    /**
     * Actualiza el rol
     * @param roleRequest Modelo que contiene la información necesaria para actualizar un rol
     * @param roleId ID del rol
     * @return OK
     */
    @PutMapping("/{id}")
    public ResponseEntity<String> updateRole(
            @RequestBody RoleRequest roleRequest,
            @PathVariable("id") Integer roleId
    ) {
        roleService.updateRole(roleRequest, roleId);
        return ResponseEntity.ok(HttpStatus.OK.getReasonPhrase());
    }

    /**
     * Habilita o Desahbilita un rol
     * @param roleId ID del rol
     * @param enabled TRUE o FALSE en caso que se quiera habilitar o deshabilitar
     * @return OK
     */
    @DeleteMapping("/{id}/enabled/{enabled}")
    public ResponseEntity<String> enableDisableRole(
            @PathVariable("id") Integer roleId,
            @PathVariable("enabled") Boolean enabled
    ) {
        roleService.enableDisableRole(roleId, enabled);
        return ResponseEntity.ok(HttpStatus.OK.getReasonPhrase());
    }

    /**
     * Obtiene los roles que no tengan permisos dentro de un módulo
     * @param moduleId ID del módulo
     * @return lista de roles
     */
    @GetMapping("/not-included/module-id/{id}")
    public ResponseEntity<List<Role>> getRolesWithNotPermissionInModule(
            @PathVariable("id") Integer moduleId
    ) {
        return ResponseEntity.ok(roleService.getRolesWithNotPermissionInModule(moduleId));
    }

}
