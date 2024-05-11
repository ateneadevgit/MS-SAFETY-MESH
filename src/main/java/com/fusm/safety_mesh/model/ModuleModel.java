package com.fusm.safety_mesh.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModuleModel {

    private Integer moduleId;
    private String name;
    private String icon;
    private String path;
    private Boolean isMenu;
    private Date createdAt;
    private Date updatedAt;
    private String createdBy;
    private Integer moduleFather;
    private String description;

}
