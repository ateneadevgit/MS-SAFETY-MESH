package com.fusm.safety_mesh.dto;

import org.springframework.beans.factory.annotation.Value;

public interface ModuleDto {

    @Value("#{target.id_module}")
    Integer getIdModule();

    @Value("#{target.name}")
    String getName();

    @Value("#{target.icon}")
    String getIcon();

    @Value("#{target.path}")
    String getPath();

    @Value("#{target.color}")
    String getColor();

    @Value("#{target.menu_order}")
    Integer getMenuOrder();

    @Value("#{target.module_father}")
    Integer getModuleFather();

    @Value("#{target.can_create}")
    Boolean getCanCreate();

    @Value("#{target.can_view}")
    Boolean getCanView();

    @Value("#{target.can_delete}")
    Boolean getCanDelete();

    @Value("#{target.can_update}")
    Boolean getCanUpdate();

}
