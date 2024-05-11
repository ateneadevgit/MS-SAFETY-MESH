package com.fusm.safety_mesh.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuModel {

    private String label;
    private String icon;
    private String routerLink;
    private List<MenuModel> items;
    private Integer order;
    private String color;
    private Boolean canCreate;
    private Boolean canUpdate;
    private Boolean canView;
    private Boolean canDelete;

}
