package com.example.Chiaradia1erparcial.controller;

import com.example.Chiaradia1erparcial.model.Cumpleanito;

import com.example.Chiaradia1erparcial.model.Persona;
import com.example.Chiaradia1erparcial.model.dto.PersonaDto;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
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

    @GetMapping("/listaDeInvitados")
    public ResponseEntity<List<PersonaDto>> getlistaDeInvitados(Pageable pageable) throws IOException, InterruptedException {
        Page<PersonaDto> listaDePersona = cumpleanitoService.getlistaDeInvitados(pageable);
        return response(listaDePersona);
    }

    public static ResponseEntity response(Page page){
        return ResponseEntity.status(page.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK)
                .header("X-Total-Pages", String.valueOf(page.getTotalPages()))
                .header("X-Total-Content",String.valueOf(page.getTotalElements()))
                .body(page.getContent());
    }
}