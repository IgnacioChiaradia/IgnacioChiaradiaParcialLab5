package com.example.Chiaradia1erparcial.model.dto;

import com.example.Chiaradia1erparcial.model.Currency;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PersonaDto {

    private String name;
    private Currency currency;
    private Float amount;
}
