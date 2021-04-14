package com.example.Chiaradia1erparcial.model;

import lombok.Data;

import javax.persistence.Entity;

@Data

@Entity
public class Jugador extends Persona {

    private String peso;
    private String altura;
    private Integer goles;
    private Integer minutosJugados;
    private Currency currency;
    private String fechaDeNacimiento;


    @Override
    public TypePersona typePersona() {
        return TypePersona.JUGADOR;
    }


}
