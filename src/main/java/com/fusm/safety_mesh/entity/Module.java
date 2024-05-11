package com.fusm.safety_mesh.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Builder
@Entity
@Data
@Table(name = "Module")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Module {

    @Id
    @Column(name = "id_module", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer moduleId;

    @NonNull
    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "icon", length = 30, nullable = true)
    private String icon;

    @Column(name = "path", length = 50, nullable = true)
    private String path;

    @Column(name = "description", length = 255, nullable = true)
    private String description;

    @NonNull
    @Column(name = "isMenu", nullable = false)
    private Boolean isMenu;

    @Column(name = "menu_order", nullable = true)
    private Integer menuOrder;

    @Column(name = "color", length = 10, nullable = true)
    private String color;

    @NonNull
    @Column(name = "created_at", nullable = false)
    private Date createdAt;

    @Column(name = "updated_at", nullable = true)
    private Date updatedAt;

    @Column(name = "is_floatin", nullable = true)
    private Boolean isFloating;

    @NonNull
    @Column(name = "created_by", length = 50, nullable = false)
    private String createdBy;

    @ManyToOne
    @JoinColumn(name = "module_father", nullable = true)
    private Module moduleFather;

}
