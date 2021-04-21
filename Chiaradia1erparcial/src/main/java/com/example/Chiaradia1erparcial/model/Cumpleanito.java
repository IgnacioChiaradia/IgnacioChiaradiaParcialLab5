package com.example.Chiaradia1erparcial.model;

import javax.persistence.*;

import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
@Entity
public class Cumpleanito {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String fecha;

    @OneToOne(cascade = CascadeType.ALL)
    private Persona cumpleaniero;

    @ManyToMany
    Set<Persona> invitados;

}