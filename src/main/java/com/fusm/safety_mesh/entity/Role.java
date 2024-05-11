package com.fusm.safety_mesh.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Builder
@Entity
@Data
@Table(name = "Role")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    @Id
    @Column(name = "id_role", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roleId;

    @NonNull
    @Column(name = "name_rol", length = 50, nullable = false)
    private String nameRole;

    @NonNull
    @Column(name = "id_sinu", length = 50, nullable = false)
    private String sinuId;

    @NonNull
    @Column(name = "enabled", nullable = false)
    private Boolean enabled;

    @NonNull
    @Column(name = "created_at", nullable = false)
    private Date createdAt;

    @Column(name = "updated_at", nullable = true)
    private Date updatedAt;

    @NonNull
    @Column(name = "created_by", length = 50, nullable = false)
    private String createdBy;

    @Column(name = "description", length = 255, nullable = false)
    private String description;

}
