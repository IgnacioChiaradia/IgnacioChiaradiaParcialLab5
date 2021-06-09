package com.example.Chiaradia1erparcial.service;

import com.example.Chiaradia1erparcial.model.*;
import com.example.Chiaradia1erparcial.repository.CumpleanitoRepository;
import com.example.Chiaradia1erparcial.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class PersonaService {

    @Autowired
    PersonaRepository personaRepository;
    @Autowired
    CumpleanitoRepository cumpleanitoRepository;

    public List<Persona> getAll() {
        return personaRepository.findAll();
    }

    public Persona getByID(Integer id) {
        return personaRepository.findById(id)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
    }

    public void add(Persona persona) {
        personaRepository.save(persona);
    }

    public void deleteById(Integer id) {
        personaRepository.deleteById(id);
    }

    public Persona addPlayerToListPlayers(Integer id, Integer idJugador) {
        Persona representante = getByID(id);
        Persona jugador = getByID(idJugador);

        if(representante instanceof Representante && jugador instanceof Jugador){
            ((Representante) representante).getJugadores().add((Jugador)jugador);
            return personaRepository.save(representante);
        }
        else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    public Persona addCumpleanitoToPersona(Integer id, Integer idCumpleanito) {
        Persona persona = getByID(id);
        Cumpleanito cumple = cumpleanitoRepository.findById(idCumpleanito).
                orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));

        ((Persona) persona).getCumpleanitos().add(cumple);
        return personaRepository.save(persona);

    }

    public Amigo addAmigoToListAmigos(Integer id, Integer idAmigo) {
        /*Persona amigo = getByID(id);
        Persona representante = getByID(id);

        if(amigo instanceof Amigo && representante instanceof Representante){
            ((Representante) representante).getAmigos().add(amigo);
            return personaRepository.save(amigo);
        }
        else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }*/
        return null;
    }
}
