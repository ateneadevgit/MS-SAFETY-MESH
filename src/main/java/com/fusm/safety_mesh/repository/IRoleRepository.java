package com.fusm.safety_mesh.repository;

import com.fusm.safety_mesh.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Integer> {

    @Query(
            value = "select * from role " +
            "order by created_at desc ",
            nativeQuery = true
    )
    List<Role> findAllRolesOrdered();

    @Query(
            value = "SELECT * " +
                    "FROM public.role " +
                    "where not exists ( " +
                    "   select * from permission where permission.role_id = role.id_role and permission.module_id = :moduleId " +
                    ") " +
                    "order by role.id_role asc ",
            nativeQuery = true
    )
    List<Role> findAllRolesWithNotPermissionInModule(
            @Param("moduleId") Integer moduleId
    );

}
