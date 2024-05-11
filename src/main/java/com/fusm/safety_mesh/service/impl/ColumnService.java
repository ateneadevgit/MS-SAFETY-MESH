package com.fusm.safety_mesh.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fusm.safety_mesh.entity.Columns;
import com.fusm.safety_mesh.entity.Module;
import com.fusm.safety_mesh.entity.Role;
import com.fusm.safety_mesh.model.ColumnModel;
import com.fusm.safety_mesh.model.ColumnPermission;
import com.fusm.safety_mesh.model.ColumnRequest;
import com.fusm.safety_mesh.model.ValidatePermission;
import com.fusm.safety_mesh.repository.IColumnRepository;
import com.fusm.safety_mesh.repository.IModuleRepository;
import com.fusm.safety_mesh.repository.IRoleRepository;
import com.fusm.safety_mesh.service.IColumnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ColumnService implements IColumnService {

    @Autowired
    private IColumnRepository columnRepository;

    @Autowired
    private IRoleRepository roleRepository;

    @Autowired
    private IModuleRepository moduleRepository;

    ObjectMapper objectMapper = new ObjectMapper();


    @Override
    public List<ColumnModel> getColumns() {
        return columnRepository.findAll().stream().map(
                column -> ColumnModel.builder()
                        .columnId(column.getColumnId())
                        .columns(column.getColumns())
                        .roleId(column.getRoleId().getRoleId())
                        .role(column.getRoleId().getNameRole())
                        .moduleId(column.getModuleId().getModuleId())
                        .module(column.getModuleId().getName())
                        .build()
        ).toList();
    }

    @Override
    public void createColumn(ColumnRequest columnRequest) throws JsonProcessingException {
        saveColumn(columnRequest);
    }

    @Override
    public void updateColumn(ColumnRequest columnRequest, Integer columnId) throws JsonProcessingException {
        Optional<Columns> columnOptional = columnRepository.findById(columnId);
        if (columnOptional.isPresent()) {
            saveColumn(columnRequest);
        }
    }

    @Override
    public List<ColumnPermission> getColumnsByRoleAndModule(ValidatePermission validatePermission) throws JsonProcessingException {
        List<ColumnPermission> columnsResponse = new ArrayList<>();
        Optional<Columns> columnOptional = columnRepository
                .findByRoleId_RoleIdAndModuleId_ModuleId(validatePermission.getRoleId(), validatePermission.getModuleId());
        if (columnOptional.isPresent()) {
            ColumnPermission[] columnPermissions = objectMapper.readValue(columnOptional.get().getColumns(), ColumnPermission[].class);
            columnsResponse = Arrays.asList(columnPermissions);
        }
        return columnsResponse;
    }

    private void saveColumn(ColumnRequest columnRequest) throws JsonProcessingException {
        Optional<Role> roleOptional = roleRepository.findById(columnRequest.getRoleId());
        Optional<Module> moduleOptional = moduleRepository.findById(columnRequest.getModuleId());
        if (roleOptional.isPresent() && moduleOptional.isPresent()) {
            String json = objectMapper.writeValueAsString(columnRequest.getColumns());
            columnRepository.save(
                    Columns.builder()
                            .columns(json)
                            .roleId(roleOptional.get())
                            .moduleId(moduleOptional.get())
                            .build()
            );
        }
    }

}
