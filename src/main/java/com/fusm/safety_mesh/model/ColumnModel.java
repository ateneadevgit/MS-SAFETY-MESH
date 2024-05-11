package com.fusm.safety_mesh.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ColumnModel {

    private Integer columnId;
    private String columns;
    private Integer roleId;
    private String role;
    private Integer moduleId;
    private String module;

}
