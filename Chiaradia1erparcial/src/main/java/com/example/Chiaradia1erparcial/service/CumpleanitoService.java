package com.example.Chiaradia1erparcial.service;

import com.example.Chiaradia1erparcial.api.ApiCallService;
import com.example.Chiaradia1erparcial.exception.ErrorMasDeDiezInvitados;
import com.example.Chiaradia1erparcial.model.*;
import com.example.Chiaradia1erparcial.model.dto.PersonaDto;
import com.example.Chiaradia1erparcial.repository.PersonaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import com.example.Chiaradia1erparcial.repository.CumpleanitoRepository;

@Service
public class CumpleanitoService {

    CumpleanitoRepository cumpleanitoRepository;

    PersonaRepository personaRepository;

    ApiCallService apiCallService;

    public CumpleanitoService(CumpleanitoRepository cumpleanitoRepository, PersonaRepository personaRepository, ApiCallService apiCallService){
        this.cumpleanitoRepository = cumpleanitoRepository;
        this.personaRepository = personaRepository;
        this.apiCallService = apiCallService;
    }

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

    public Page<PersonaDto> getlistaDeInvitados(Pageable pageable) throws IOException, InterruptedException {
        Page<Cumpleanito> cumpleanitos = cumpleanitoRepository.findAll(pageable);

        Float dolarValue = apiCallService.getDolar();
        Float euroValue = apiCallService.getEuro();

        List<PersonaDto> listaDeInvitados = new ArrayList<>();

        for (Cumpleanito c: cumpleanitos){
            for (Persona invitado: c.getInvitados()){
                if(invitado instanceof Jugador){
                    PersonaDto personaInv = new PersonaDto();

                    personaInv.setName(invitado.getName());
                    Currency currency = ((Jugador)invitado).getCurrency();

                    if(currency.getTypeCurrency() == TypeCurrency.EUR)
                        personaInv.setAmount(25000 / dolarValue);
                    else
                        personaInv.setAmount(2500 / euroValue);

                    personaInv.setCurrency(((Jugador)invitado).getCurrency());
                    listaDeInvitados.add(personaInv);
                }
            }
        }

        return new PageImpl<PersonaDto>(listaDeInvitados, pageable, listaDeInvitados.size());
    }
}