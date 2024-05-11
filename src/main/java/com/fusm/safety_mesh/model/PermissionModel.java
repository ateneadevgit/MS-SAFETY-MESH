package com.fusm.safety_mesh.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PermissionModel {

    private Integer moduleId;
    private String module;
    private Integer roleId;
    private String role;
    private Boolean hasView;
    private Boolean hasWrite;
    private Boolean hasEdit;
    private Boolean hasDelete;

}
