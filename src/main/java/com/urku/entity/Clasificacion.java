package com.urku.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "clasificaciones")
public class Clasificacion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cla_id")
    private Integer clasId;

    @Column(name = "clas_tipo")
    private String clasTipo;

    @OneToMany(mappedBy = "clasificacion")
    private List<Animal> animals;

}
