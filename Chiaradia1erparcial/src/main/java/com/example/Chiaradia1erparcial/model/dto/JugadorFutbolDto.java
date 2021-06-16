package com.example.Chiaradia1erparcial.model.dto;

import com.example.Chiaradia1erparcial.model.Country;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JugadorFutbolDto {

    @SerializedName("player_id")
    private Integer Id;

    @SerializedName("firstname")
    private String name;

    @SerializedName("lastname")
    private String lastName;

    @SerializedName("age")
    private String age;

    @SerializedName("height")
    private Integer altura;

    private Country country;


}
