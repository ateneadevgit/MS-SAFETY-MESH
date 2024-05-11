package com.fusm.safety_mesh.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fusm.safety_mesh.model.ColumnModel;
import com.fusm.safety_mesh.model.ColumnPermission;
import com.fusm.safety_mesh.model.ColumnRequest;
import com.fusm.safety_mesh.model.ValidatePermission;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IColumnService {

    List<ColumnModel> getColumns();
    void createColumn(ColumnRequest columnRequest) throws JsonProcessingException;
    void updateColumn(ColumnRequest columnRequest, Integer columnId) throws JsonProcessingException;
    List<ColumnPermission> getColumnsByRoleAndModule(ValidatePermission validatePermission) throws JsonProcessingException;

}
