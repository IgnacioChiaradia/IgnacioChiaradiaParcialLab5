package com.example.Chiaradia1erparcial.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Country {

    private Integer country_id;
    private String name;
    private String country_code;
    private String continent;
}
