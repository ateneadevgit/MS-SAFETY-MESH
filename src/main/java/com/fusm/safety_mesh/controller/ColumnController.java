package com.fusm.safety_mesh.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fusm.safety_mesh.model.ColumnModel;
import com.fusm.safety_mesh.model.ColumnPermission;
import com.fusm.safety_mesh.model.ColumnRequest;
import com.fusm.safety_mesh.model.ValidatePermission;
import com.fusm.safety_mesh.service.IColumnService;
import com.fusm.safety_mesh.util.AppRoutes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Clase que expone los servicios relacionados con las columnas de las tablas a las que puede acceder un usuario
 * ITSense Inc - Andrea Gómez
 */

@RestController
@RequestMapping(AppRoutes.COLUMN_ROUTE)
public class ColumnController {

    @Autowired
    private IColumnService columnService;


    /**
     * Obtiene las columnas
     * @return lista de columnas
     */
    @GetMapping
    public ResponseEntity<List<ColumnModel>> getColumns() {
        return ResponseEntity.ok(columnService.getColumns());
    }

    /**
     * Ontiene la columnas a las que tiene acceso un usuario según su rol y el módulo
     * @param validatePermission Modelo que permite validar el permiso
     * @return lista de columnas
     * @throws JsonProcessingException
     */
    @PostMapping("/permission")
    public ResponseEntity<List<ColumnPermission>> getColumnsByRoleAndModule(
            @RequestBody ValidatePermission validatePermission
            ) throws JsonProcessingException {
        return ResponseEntity.ok(columnService.getColumnsByRoleAndModule(validatePermission));
    }

    /**
     * Crea una nueva columna
     * @param columnRequest Modelo que permite crear una nueva columna
     * @return OK
     * @throws JsonProcessingException
     */
    @PostMapping
    public ResponseEntity<String> createColumn(
            @RequestBody ColumnRequest columnRequest
            ) throws JsonProcessingException {
        columnService.createColumn(columnRequest);
        return ResponseEntity.ok(HttpStatus.OK.getReasonPhrase());
    }

    /**
     * Actualizar columna
     * @param columnRequest Modelo que contiene la información para actualizar una columna
     * @param columnId ID de la columna
     * @return OK
     * @throws JsonProcessingException
     */
    @PutMapping("/{id}")
    public ResponseEntity<String> updateColumn(
            @RequestBody ColumnRequest columnRequest,
            @PathVariable("id") Integer columnId
    ) throws JsonProcessingException {
        columnService.updateColumn(columnRequest, columnId);
        return ResponseEntity.ok(HttpStatus.OK.getReasonPhrase());
    }

}
