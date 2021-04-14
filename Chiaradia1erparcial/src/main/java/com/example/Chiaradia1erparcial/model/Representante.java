package com.example.Chiaradia1erparcial.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Representante extends Persona{

    private List<Jugador> Jugadores;
    private Integer pesoDeLaBoveda;
    private Integer montoTotal;

    @Override
    public TypePersona typePersona() {
        return TypePersona.JUGADOR;
    }
}
