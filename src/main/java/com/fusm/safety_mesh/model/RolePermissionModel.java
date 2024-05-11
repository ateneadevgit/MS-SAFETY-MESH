package com.fusm.safety_mesh.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RolePermissionModel {

    private Integer moduleId;
    private Integer roleId;
    private Boolean canCreate;
    private Boolean canUpdate;
    private Boolean canView;
    private Boolean canDelete;
    private String roleName;

}
