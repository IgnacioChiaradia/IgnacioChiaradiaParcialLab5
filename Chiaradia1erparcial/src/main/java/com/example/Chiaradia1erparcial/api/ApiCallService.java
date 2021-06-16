package com.example.Chiaradia1erparcial.api;

import com.google.gson.JsonParser;
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

    @CircuitBreaker(name = "eurosiDolar", fallbackMethod = "fallback")
    public float getEuro() throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://www.dolarsi.com/api/api.php?type=genedolar&opc=ta"))
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        //System.out.println(JsonParser.parseString(response.body()).getAsJsonArray());
        // pasar lo que trae la api a arreglo para recorrerlo y no usar el get(0)

        String precioEuro = JsonParser.parseString(response.body()).getAsJsonArray().get(0).getAsJsonObject().get("dolar").getAsJsonObject().get("compra").getAsString();

        return Float.parseFloat(precioEuro.replace(",","."));
    }


    @CircuitBreaker(name = "dolarsiDolar", fallbackMethod = "fallback2")
    public float getDolar() throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://www.dolarsi.com/api/api.php?type=dolar"))
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        String precioDolar = JsonParser.parseString(response.body()).getAsJsonArray().get(0).getAsJsonObject().get("casa").getAsJsonObject().get("compra").getAsString();

        return Float.parseFloat(precioDolar.replace(",","."));
    }


    public float fallback(final Throwable t) {
        log.info("Fallback cause, {}", t.toString());
        return 0F;
    }

    public float fallback2(final Throwable t) {
        log.info("Fallback cause, {}", t.toString());
        return 0F;
    }

}
