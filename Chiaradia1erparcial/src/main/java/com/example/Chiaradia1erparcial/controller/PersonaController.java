package com.example.Chiaradia1erparcial.controller;

import com.example.Chiaradia1erparcial.model.Persona;
import com.example.Chiaradia1erparcial.model.dto.JugadorFutbolDto;
import com.example.Chiaradia1erparcial.model.dto.PersonaDto;
import com.example.Chiaradia1erparcial.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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

    @GetMapping("/listaDeJugadores")
    public ResponseEntity<List<JugadorFutbolDto>> getlistaDeJugadores(Pageable pageable) throws IOException, InterruptedException {
        personaService.getlistaDeJugadores();
    }

    public static ResponseEntity response(Page page){
        return ResponseEntity.status(page.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK)
                .header("X-Total-Pages", String.valueOf(page.getTotalPages()))
                .header("X-Total-Content",String.valueOf(page.getTotalElements()))
                .body(page.getContent());
    }


}
