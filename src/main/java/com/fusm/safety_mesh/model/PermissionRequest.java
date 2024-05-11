package com.fusm.safety_mesh.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PermissionRequest {

    private Integer moduleId;
    private Integer roleId;
    private Boolean hasView;
    private Boolean hasWrite;
    private Boolean hasEdit;
    private Boolean hasDelete;
    private String createdBy;

}
