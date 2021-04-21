package com.example.Chiaradia1erparcial.model;

import com.sun.xml.bind.v2.TODO;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Representante extends Persona{

    @OneToMany
    private List<Jugador> Jugadores;
    private Integer pesoDeLaBoveda;
    private Integer montoTotal;
    @OneToMany()
    private List<Amigo> amigos;

    @Override
    public TypePersona typePersona() {
        return TypePersona.REPRESENTANTE;
    }

    public Integer getMontoTotal()
    {
        Integer montoTotal = 0;
        for (Jugador jugador : Jugadores) {
            if(jugador.getCurrency() != null)
            {
                if(jugador.getCurrency().getTypeCurrency() == TypeCurrency.DOLARES){
                    montoTotal += jugador.getCurrency().getMonto() * 150;
                }
                if(jugador.getCurrency().getTypeCurrency() == TypeCurrency.EUROS){
                    montoTotal += jugador.getCurrency().getMonto() * 170;
                }
            }
        }
        return montoTotal;
    }

    public Double getPesoDeLaBoveda(){
        //Integer montoBoveda;
        return ((getMontoTotal() / 100) * 0.01);
    }
}
