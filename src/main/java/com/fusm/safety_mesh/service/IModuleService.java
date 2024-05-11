package com.fusm.safety_mesh.service;

import com.fusm.safety_mesh.entity.Module;
import com.fusm.safety_mesh.model.ModuleModel;
import com.fusm.safety_mesh.model.ModuleRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IModuleService {

    List<ModuleModel> getModules();
    void createModule(ModuleRequest moduleRequest);
    void updateModule(ModuleRequest moduleRequest, Integer moduleId);

}
