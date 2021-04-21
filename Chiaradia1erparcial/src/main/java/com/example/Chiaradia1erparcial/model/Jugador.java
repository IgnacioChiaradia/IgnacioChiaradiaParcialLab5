package com.example.Chiaradia1erparcial.model;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Data

@Entity
public class Jugador extends Persona {

    private String peso;
    private String altura;
    private Integer goles;
    private Integer minutosJugados;
    @OneToOne(cascade = CascadeType.ALL) // te permite agregar la currency
    private Currency currency;
    private String fechaDeNacimiento;

    @Override
    public TypePersona typePersona() {
        return TypePersona.JUGADOR;
    }


}
