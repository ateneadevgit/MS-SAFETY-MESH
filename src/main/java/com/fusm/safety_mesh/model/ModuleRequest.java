package com.fusm.safety_mesh.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModuleRequest {

    private String name;
    private String icon;
    private String path;
    private Integer menuFather;
    private String createdBy;
    private String description;

}
