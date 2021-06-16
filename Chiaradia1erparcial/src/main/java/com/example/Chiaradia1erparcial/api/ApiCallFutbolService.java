package com.example.Chiaradia1erparcial.api;

import com.example.Chiaradia1erparcial.model.Jugador;
import com.example.Chiaradia1erparcial.model.dto.JugadorFutbolDto;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ApiCallFutbolService {

        @CircuitBreaker(name = "sportDataApi", fallbackMethod = "fallback")
        public JsonArray getJugadores() throws IOException, InterruptedException {

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://app.sportdataapi.com/api/v1/soccer/players?apikey=67d66c50-cee6-11eb-acc1-c5e908069303&country_id=13"))
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();

            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

            List<JugadorFutbolDto> listaJugadores = new ArrayList<JugadorFutbolDto>();

            JsonArray jugadores = JsonParser.parseString(response.body()).getAsJsonObject().get("data").getAsJsonArray();

            JsonElement juga = jugadores.get(0);
            juga.getAsJsonObject().get("firstname");
            System.out.println(juga.getAsJsonObject().get("firstname"));
            System.out.println(jugadores.get(0));

            return jugadores;
        }

        public JsonArray fallback(final Throwable t) {
            log.info("Fallback cause, {}", t.toString());
            return new JsonArray();

        }

    }

