package com.fusm.safety_mesh.controller;

import com.fusm.safety_mesh.model.*;
import com.fusm.safety_mesh.service.IPermissionService;
import com.fusm.safety_mesh.util.AppRoutes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Clase que expone los servicios relacionados con los permisos de la aplicación
 * ITSense Inc - Andrea Gómez
 */

@RestController
@RequestMapping(value = AppRoutes.PERMISSION_ROUTE)
public class PermissionController {

    @Autowired
    private IPermissionService permissionService;


    /**
     * Obtiene los permisos que tiene un rol
     * @param roleId ID del rol
     * @return lista de permisos
     */
    @GetMapping("/by-role/{id}")
    public ResponseEntity<List<PermissionModel>> getPermissionByRole(
            @PathVariable("id") Integer roleId
    ){
        return ResponseEntity.ok(permissionService.getPermissionByRole(roleId));
    }

    /**
     * Crea un nuevo permiso
     * @param permissionRequest Modelo que contiene la información necesaria para crear un permiso
     * @return OK
     */
    @PostMapping
    public ResponseEntity<String> createPermission(
            @RequestBody PermissionRequest permissionRequest
            ) {
        permissionService.createPermission(permissionRequest);
        return ResponseEntity.ok(HttpStatus.OK.getReasonPhrase());
    }

    /**
     * Actualiza un permiso
     * @param permissionRequest Modelo que contiene la información necesaria para actualizar un permiso
     * @return OK
     */
    @PutMapping
    public ResponseEntity<String> updatePermission(
            @RequestBody PermissionRequest permissionRequest
    ) {
        permissionService.updatePermission(permissionRequest);
        return ResponseEntity.ok(HttpStatus.OK.getReasonPhrase());
    }

    /**
     * Obtiene los módulos a los que tiene acceso un usuario según su rol
     * @param roleId ID del rol
     * @return lista de módulos
     */
    @GetMapping("/module/by-role/{id}")
    public ResponseEntity<List<MenuModel>> getModuleByRole(
            @PathVariable("id") Integer roleId
    ) {
        return ResponseEntity.ok(permissionService.getModuleByRole(roleId));
    }

    /**
     * Obtiene los módulos flotantes a los que tiene acceso un usuario según su rol
     * @param roleId ID del rol
     * @return lista de módulos flotantes
     */
    @GetMapping("/floating/module/by-role/{id}")
    public ResponseEntity<List<MenuModel>> getFloatingModuleByRole(
            @PathVariable("id") Integer roleId
    ) {
        return ResponseEntity.ok(permissionService.getFloatingModuleByRole(roleId));
    }

    /**
     * Obtiene si un usuario tiene el permiso de visualización sobre un módulo
     * @param validatePermission Modelo que contiene la información para validar el permiso
     * @return Boolean
     */
    @PostMapping("/has-view")
    public ResponseEntity<Boolean> roleHasView(
            @RequestBody ValidatePermission validatePermission
            ) {
        return ResponseEntity.ok(permissionService.roleHasView(validatePermission));
    }

    /**
     * Obtiene si un usuario tiene el permiso de escritura sobre un módulo
     * @param validatePermission Modelo que contiene la información para validar el permiso
     * @return Boolean
     */
    @PostMapping("/has-write")
    public ResponseEntity<Boolean> roleHasWrite(
            @RequestBody ValidatePermission validatePermission
    ) {
        return ResponseEntity.ok(permissionService.roleHasWrite(validatePermission));
    }

    /**
     * Obtiene si un usuario tiene el permiso de actualización sobre un módulo
     * @param validatePermission Modelo que contiene la información para validar el permiso
     * @return Boolean
     */
    @PostMapping("/has-update")
    public ResponseEntity<Boolean> roleHasUpdate(
            @RequestBody ValidatePermission validatePermission
    ) {
        return ResponseEntity.ok(permissionService.roleHasEdit(validatePermission));
    }

    /**
     * Ontiene si un usuario tiene el permiso de eliminación sobre un módulo
     * @param validatePermission Modelo que contiene la información para validar el permiso
     * @return Boolean
     */
    @PostMapping("/has-delete")
    public ResponseEntity<Boolean> roleHasDelete(
            @RequestBody ValidatePermission validatePermission
    ) {
        return ResponseEntity.ok(permissionService.roleHasDelete(validatePermission));
    }

    /**
     * Obtiene los permisos sobre un módulo
     * @param moduleId ID del módulo
     * @return lista de permisos
     */
    @GetMapping("/module-id/{id}")
    public ResponseEntity<List<RolePermissionModel>> getPermissionsByModule(
            @PathVariable("id") Integer moduleId
    ) {
        return ResponseEntity.ok(permissionService.getPermissionsByModule(moduleId));
    }

}
