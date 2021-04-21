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
    public Persona getPersonById(@PathVariable Integer id) {

        return personaService.getByID(id);
    }

    @PostMapping
    public void addPersona(@RequestBody Persona persona) {

        personaService.add(persona);
    }

    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable Integer id) {
        personaService.deleteById(id);
    }

    @PutMapping("/{id}/jugadores/{idJugador}")
    public Persona updatePerson(@PathVariable Integer id, @PathVariable Integer idJugador){
        return personaService.addPlayerToListPlayers(id, idJugador);
    }

    @PutMapping("/{id}/amigo/{idAmigo}")
    public Persona addAmigoToRepresentante(@PathVariable Integer id, @PathVariable Integer idAmigo){
        return personaService.addAmigoToListAmigos(id, idAmigo);
    }

    @PutMapping("/{id}/cumpleanito/{idCumple}")
    public Persona addCumpleanitoToSetPersona(@PathVariable Integer id, @PathVariable Integer idCumple){
        return personaService.addCumpleanitoToPersona(id, idCumple);
    }




}
