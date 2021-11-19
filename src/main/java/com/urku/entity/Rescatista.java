package com.urku.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "rescatistas")
public class Rescatista implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "res_id")
    private Integer resId;

    @Column(name = "res_nombre")
    private String resNombre;

    @OneToMany(mappedBy = "rescatista")
    private List<Animal> animals;

}
