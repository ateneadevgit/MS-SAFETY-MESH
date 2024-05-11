package com.fusm.safety_mesh.repository;

import com.fusm.safety_mesh.entity.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IModuleRepository extends JpaRepository<Module, Integer> {

    @Query(
            value = "select * from module " +
                    "where is_menu = true " +
                    "or is_floating = true " +
                    "order by id_module asc ",
            nativeQuery = true
    )
    List<Module> findAllModuleOrdered();

}
