package com.fusm.safety_mesh.entity;

import lombok.*;

import javax.persistence.*;

@Builder
@Entity
@Data
@Table(name = "Columns")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Columns {

    @Id
    @Column(name = "id_column", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer columnId;

    @NonNull
    @Column(name = "columns", length = 1000, nullable = false)
    private String columns;

    @ManyToOne
    @JoinColumn(name = "rol_id", nullable = true)
    private Role roleId;

    @ManyToOne
    @JoinColumn(name = "module_id", nullable = true)
    private Module moduleId;



}
