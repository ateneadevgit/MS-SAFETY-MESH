package com.fusm.safety_mesh.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Builder
@Entity
@Data
@Table(name = "Permission")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Permission {

    @NonNull
    @EmbeddedId
    private PermissionPKId permissionPKId;

    @NonNull
    @Column(name = "can_view", nullable = false)
    private Boolean canView;

    @NonNull
    @Column(name = "can_create", nullable = false)
    private Boolean canCreate;

    @NonNull
    @Column(name = "can_update", nullable = false)
    private Boolean canUpdate;

    @NonNull
    @Column(name = "can_delete", nullable = false)
    private Boolean canDelete;

    @NonNull
    @Column(name = "created_at", nullable = false)
    private Date createdAt;

    @Column(name = "updated_at", nullable = true)
    private Date updatedAt;

    @NonNull
    @Column(name = "created_by", length = 50, nullable = false)
    private String createdBy;

}
