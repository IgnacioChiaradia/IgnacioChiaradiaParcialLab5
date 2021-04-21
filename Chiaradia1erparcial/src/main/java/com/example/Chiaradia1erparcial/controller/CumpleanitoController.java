package com.example.Chiaradia1erparcial.controller;

import com.example.Chiaradia1erparcial.model.Cumpleanito;

import com.example.Chiaradia1erparcial.model.Persona;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Chiaradia1erparcial.service.CumpleanitoService;

@RestController
@RequestMapping("/cumpleanito")
public class CumpleanitoController {

    @Autowired
    CumpleanitoService cumpleanitoService;

    @GetMapping("/{id}")
    public Cumpleanito getById(@PathVariable Integer id) {
        return cumpleanitoService.getById(id);
    }

    @GetMapping
    public List<Cumpleanito> getAll() {
        return cumpleanitoService.getAll();
    }

    @PostMapping
    public Cumpleanito addCumpleanito(@RequestBody Cumpleanito cumpleanito) {
        return cumpleanitoService.save(cumpleanito);
    }

    @PutMapping("/{id}/persona/{idPersona}")
    public Cumpleanito addPersonaToCumpleanito(@PathVariable Integer id, @PathVariable Integer idPersona){
        return cumpleanitoService.addPersonaToSetCumpleanito(id, idPersona);
    }
}