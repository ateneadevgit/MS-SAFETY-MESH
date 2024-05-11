package com.fusm.safety_mesh.entity;

import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.io.Serializable;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Embeddable
public class PermissionPKId implements Serializable {

    private static final long serialVersionUID = 1L;

    @OneToOne
    @JoinColumn(name = "module_id", nullable = false)
    private Module moduleId;

    @OneToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role roleId;

}
