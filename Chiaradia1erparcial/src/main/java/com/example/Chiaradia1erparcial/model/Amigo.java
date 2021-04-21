package com.example.Chiaradia1erparcial.model;

import lombok.Data;

import javax.persistence.Entity;

@Data

@Entity
public class Amigo extends Persona{

    private String profesion;
    private String statusSocial;

    @Override
    public TypePersona typePersona() {
        return TypePersona.AMIGO;
    }
}
