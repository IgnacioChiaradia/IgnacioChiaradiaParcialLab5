package com.example.Chiaradia1erparcial.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.AccessType;

import javax.persistence.*;

@Data
@NoArgsConstructor
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "typePersona", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Representante.class, name = "REPRESENTANTE"),
        @JsonSubTypes.Type(value = Jugador.class, name = "JUGADOR")
})

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String lastName;

    /*@AccessType(AccesType.Type.PROPERTY)
    public abstract TypePersona typePersona();*/

    /*--------- NO logre utilizar la java annotation ya que no la habia
    importado y no encontre la dependencia ----------------*/

    /*@AccessType()
    public abstract TypePersona typePersona();*/

    public abstract TypePersona typePersona();
}
