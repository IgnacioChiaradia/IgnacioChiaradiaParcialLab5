package com.example.Chiaradia1erparcial.service;

import com.example.Chiaradia1erparcial.api.DolarsiResponse;
import com.example.Chiaradia1erparcial.api.EurosiResponse;
import com.google.gson.Gson;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


@Service
@Slf4j
public class ApiCallService {

    @CircuitBreaker(name = "dolarsiEuro", fallbackMethod = "fallback")
    public EurosiResponse getEuro() throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("www.dolarsi.com/api/api.php?type=genedolar&opc=ta"))
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        return new Gson().fromJson(response.body(), EurosiResponse.class);

    }

    @CircuitBreaker(name = "dolarsiDolar", fallbackMethod = "fallback")
    public DolarsiResponse getDolar() throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("www.dolarsi.com/api/api.php?type=dolar"))
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        return new Gson().fromJson(response.body(), DolarsiResponse.class);

    }


    public DolarsiResponse fallback(final Throwable t) {
        log.info("Fallback cause, {}", t.toString());
        return DolarsiResponse.builder().build();
    }

}
