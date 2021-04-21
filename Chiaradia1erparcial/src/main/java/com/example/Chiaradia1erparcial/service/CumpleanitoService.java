package com.example.Chiaradia1erparcial.service;

import com.example.Chiaradia1erparcial.controller.PersonaController;
import com.example.Chiaradia1erparcial.exception.ErrorMasDeDiezInvitados;
import com.example.Chiaradia1erparcial.model.Jugador;
import com.example.Chiaradia1erparcial.model.Persona;
import com.example.Chiaradia1erparcial.model.Representante;
import com.example.Chiaradia1erparcial.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.Chiaradia1erparcial.model.Cumpleanito;
import com.example.Chiaradia1erparcial.repository.CumpleanitoRepository;

@Service
public class CumpleanitoService {

    @Autowired
    CumpleanitoRepository cumpleanitoRepository;
    @Autowired
    PersonaRepository personaRepository;

    public List<Cumpleanito> getAll() {
        return cumpleanitoRepository.findAll();
    }

    public Cumpleanito save(Cumpleanito cumpleanito) {
        return cumpleanitoRepository.save(cumpleanito);
    }

    public Cumpleanito getById(Integer id) {
        return cumpleanitoRepository.findById(id)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
    }

    public Cumpleanito addPersonaToSetCumpleanito(Integer id, Integer idPersona) {
        Cumpleanito cumple = getById(id);
        Persona persona = personaRepository.findById(idPersona).
                orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));

        if(((Cumpleanito) cumple).getInvitados().size() < 10)
        {
            ((Cumpleanito) cumple).getInvitados().add(persona);
            return cumpleanitoRepository.save(cumple);
        }
        else{
            throw new ErrorMasDeDiezInvitados("hay diez invitados cargados, debe crear otro cumpleanito");
        }

    }
}