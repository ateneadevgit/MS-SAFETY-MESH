package com.fusm.safety_mesh.controller;

import com.fusm.safety_mesh.model.ModuleModel;
import com.fusm.safety_mesh.model.ModuleRequest;
import com.fusm.safety_mesh.service.IModuleService;
import com.fusm.safety_mesh.util.AppRoutes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Clase que expone los servicios relacionados con los módulos de la aplicación
 * ITSense Inc - Andrea Gómez
 */

@RestController
@RequestMapping(value = AppRoutes.MODULE_ROUTE)
public class ModuleController {

    @Autowired
    private IModuleService moduleService;


    /**
     * Obtiene los módulos de la aplicación
     * @return lista de módulos
     */
    @GetMapping
    public ResponseEntity<List<ModuleModel>> getModules() {
        return ResponseEntity.ok(moduleService.getModules());
    }

    /**
     * Crea un módulo
     * @param moduleRequest Modelo que contiene la información para crear un nuevo módulo
     * @return OK
     */
    @PostMapping
    public ResponseEntity<String> createModule(
            @RequestBody ModuleRequest moduleRequest
            ){
        moduleService.createModule(moduleRequest);
        return ResponseEntity.ok(HttpStatus.OK.getReasonPhrase());
    }

    /**
     * Actualiza un módulo
     * @param moduleRequest Modelo que contiene la información para actualizar un módulo
     * @param moduleId ID del módulo
     * @return OK
     */
    @PutMapping("/{id}")
    public ResponseEntity<String> updateModule(
            @RequestBody ModuleRequest moduleRequest,
            @PathVariable("id") Integer moduleId
    ){
        moduleService.updateModule(moduleRequest, moduleId);
        return ResponseEntity.ok(HttpStatus.OK.getReasonPhrase());
    }

}
