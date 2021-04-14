package com.example.Chiaradia1erparcial.controller;

import com.example.Chiaradia1erparcial.model.Persona;
import com.example.Chiaradia1erparcial.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/persona")
public class PersonaController {

    @Autowired
    PersonaService personaService;

    @GetMapping
    public List<Persona> getAll() {

        return personaService.getAll();
    }

    @GetMapping("/{id}")
    public Persona getPersonById(@PathVariable String id) {

        return personaService.getByID(id);
    }

    @PostMapping
    public void addPersona(@RequestBody Persona persona) {

        personaService.add(persona);
    }

    @DeleteMapping
    public void deletePerson(@PathVariable String id){
        personaService.deleteById(id);
    }
}
