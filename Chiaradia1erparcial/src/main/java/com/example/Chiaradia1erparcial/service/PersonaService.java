package com.example.Chiaradia1erparcial.service;

import com.example.Chiaradia1erparcial.model.Persona;
import com.example.Chiaradia1erparcial.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@Service
public class PersonaService {

    @Autowired
    PersonaRepository personaRepository;

    public List<Persona> getAll() {
        return personaRepository.findAll();
    }

    public Persona getByID(String id) {
        return personaRepository.findById(id)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
    }

    public void add(Persona persona) {
        personaRepository.save(persona);
    }

    public void deleteById(String id)
    {
        personaRepository.deleteById(id);
    }
}
