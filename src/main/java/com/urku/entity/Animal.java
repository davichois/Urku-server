package com.urku.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "animales")
public class Animal implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ani_id")
    private Integer aniId;

    @Column(name = "ani_nombre")
    private String aniNombre;

    @Column(name = "ani_fecha_ingreso")
    private LocalDateTime aniFechaIngreso;

    @Column(name = "ani_observaciones")
    private String aniObservaciones;

    @Column(name = "ani_lugar_rescate")
    private String aniLugarRescate;

    @Column(name = "id_genero")
    private Integer aniGenero;

    @Column(name = "id_rescatista")
    private Integer aniRescatista;

    @Column(name = "id_clasificacicon")
    private Integer aniClasificacion;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_genero",  insertable = false, updatable = false)
    private Genero genero;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_rescatista",  insertable = false, updatable = false)
    private Rescatista rescatista;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_clasificacicon", insertable = false, updatable = false)
    private Clasificacion clasificacion;

}
