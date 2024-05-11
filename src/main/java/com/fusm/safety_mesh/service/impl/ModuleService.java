package com.fusm.safety_mesh.service.impl;

import com.fusm.safety_mesh.entity.Module;
import com.fusm.safety_mesh.model.ModuleModel;
import com.fusm.safety_mesh.model.ModuleRequest;
import com.fusm.safety_mesh.repository.IModuleRepository;
import com.fusm.safety_mesh.service.IModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ModuleService implements IModuleService {

    @Autowired
    private IModuleRepository moduleRepository;


    @Override
    public List<ModuleModel> getModules() {
        return moduleRepository.findAllModuleOrdered().stream().map(
                module -> ModuleModel.builder()
                        .moduleId(module.getModuleId())
                        .name(module.getName())
                        .icon(module.getIcon())
                        .path(module.getPath())
                        .isMenu(module.getIsMenu())
                        .createdAt(module.getCreatedAt())
                        .updatedAt(module.getUpdatedAt())
                        .createdBy(module.getCreatedBy())
                        .moduleFather((module.getModuleFather() == null) ? null : module.getModuleFather().getModuleId())
                        .description(module.getDescription())
                        .build()
        ).toList();
    }

    @Override
    public void createModule(ModuleRequest moduleRequest) {
        Optional<Module> moduleOptional = (moduleRequest.getMenuFather() == null) ?
                Optional.empty() : moduleRepository.findById(moduleRequest.getMenuFather());
        Module moduleFather = null;
        if (moduleOptional.isPresent()) moduleFather = moduleOptional.get();
        moduleRepository.save(
                Module.builder()
                        .name(moduleRequest.getName())
                        .icon(moduleRequest.getIcon())
                        .path(moduleRequest.getPath())
                        .createdAt(new Date())
                        .createdBy(moduleRequest.getCreatedBy())
                        .moduleFather(moduleFather)
                        .description(moduleRequest.getDescription())
                        .build()
        );
    }

    @Override
    public void updateModule(ModuleRequest moduleRequest, Integer moduleId) {
        Optional<Module> moduleOptional = moduleRepository.findById(moduleId);
        if (moduleOptional.isPresent()) {
            Module module = moduleOptional.get();
            module.setName(moduleRequest.getName());
            module.setUpdatedAt(new Date());
            module.setDescription(moduleRequest.getDescription());
            moduleRepository.save(module);
        }
    }

}
