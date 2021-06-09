package com.example.Chiaradia1erparcial.controller;

import com.example.Chiaradia1erparcial.api.EurosiResponse;
import com.example.Chiaradia1erparcial.service.ApiCallService;
import com.example.Chiaradia1erparcial.api.DolarsiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class ApiController {
    @Autowired
    private ApiCallService apiCallService;

    @GetMapping("/euro")
    public EurosiResponse getEuro() throws IOException, InterruptedException {
        return apiCallService.getEuro();
    }

    @GetMapping("/dolar")
    public DolarsiResponse getDolar() throws IOException, InterruptedException {
        return apiCallService.getDolar();
    }
}
