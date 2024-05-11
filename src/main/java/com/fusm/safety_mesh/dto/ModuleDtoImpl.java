package com.fusm.safety_mesh.dto;

import lombok.*;

@Builder
@Data
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ModuleDtoImpl implements ModuleDto {

    private Integer idModule;
    private String name;
    private String icon;
    private String path;
    private Integer moduleFather;
    private String color;
    private Boolean canCreate;
    private Boolean canUpdate;
    private Boolean canView;
    private Boolean canDelete;

    @Override
    public Integer getIdModule() {
        return this.idModule;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getIcon() {
        return this.icon;
    }

    @Override
    public String getPath() {
        return this.path;
    }

    @Override
    public String getColor() {
        return this.color;
    }

    @Override
    public Integer getMenuOrder() {
        return this.getMenuOrder();
    }

    @Override
    public Integer getModuleFather() {
        return this.moduleFather;
    }

    @Override
    public Boolean getCanCreate() {
        return this.canCreate;
    }

    @Override
    public Boolean getCanView() {
        return this.canView;
    }

    @Override
    public Boolean getCanDelete() {
        return this.canDelete;
    }

    @Override
    public Boolean getCanUpdate() {
        return this.canUpdate;
    }
}
