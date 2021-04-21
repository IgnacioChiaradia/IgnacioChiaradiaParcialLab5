package com.example.Chiaradia1erparcial.model;

public enum TypeCurrency {

    DOLARES("dolares"),
    EUROS("euros"),
    REALES("reales");

    private String descripcion;

    TypeCurrency(String descripcion)
    {
        this.descripcion = descripcion;
    }

    public static TypeCurrency find(final String value) {
        for (TypeCurrency v : values()) {
            if (v.toString().equalsIgnoreCase(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException(String.format("Invalid TypeCurrency: %s", value));
    }

    public String getDescripcion() {
        return descripcion;
    }
}
