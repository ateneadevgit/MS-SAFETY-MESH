package com.fusm.safety_mesh.repository;

import com.fusm.safety_mesh.entity.Columns;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IColumnRepository extends JpaRepository<Columns, Integer> {

     Optional<Columns> findByRoleId_RoleIdAndModuleId_ModuleId(Integer roleId, Integer moduleId);

}
