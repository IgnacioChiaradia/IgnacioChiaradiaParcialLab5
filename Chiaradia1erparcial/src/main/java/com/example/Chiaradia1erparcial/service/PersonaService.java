package com.example.Chiaradia1erparcial.service;

import com.example.Chiaradia1erparcial.api.ApiCallFutbolService;
import com.example.Chiaradia1erparcial.api.ApiCallService;
import com.example.Chiaradia1erparcial.model.*;
import com.example.Chiaradia1erparcial.repository.CumpleanitoRepository;
import com.example.Chiaradia1erparcial.repository.PersonaRepository;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;

@Service
public class PersonaService {

    @Autowired
    PersonaRepository personaRepository;
    @Autowired
    CumpleanitoRepository cumpleanitoRepository;
    @Autowired
    ApiCallFutbolService apiCallFutbolService;

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
        Persona amigo = getByID(idAmigo);
        Persona representante = getByID(id);

        if(amigo instanceof Amigo && representante instanceof Representante){
            ((Representante) representante).getAmigos().add((Amigo)amigo);
            return personaRepository.save((Amigo)amigo);
        }
        else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    public void getlistaDeJugadores() throws IOException, InterruptedException {

        JsonArray jsonJugadores = apiCallFutbolService.getJugadores();

        for (int i = 0; i < jsonJugadores.size(); i++) {

            //jsonJugadores.get(i).getAsJsonObject().get("firstaname");
            Integer altura = Integer.parseInt(jsonJugadores.get(i).getAsJsonObject().get("height"));

            Jugador jugador = new Jugador();

            if((Integer)altura > 180){

            }

        }
    }
}
