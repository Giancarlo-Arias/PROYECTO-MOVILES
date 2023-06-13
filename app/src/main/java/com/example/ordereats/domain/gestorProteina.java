package com.example.ordereats.domain;

public class gestorProteina {
    private int idProteina;
    private String nombre;

    public gestorProteina(int idProteina, String nombre) {
        this.idProteina = idProteina;
        this.nombre = nombre;
    }

    public int getIdProteina() {
        return idProteina;
    }

    public void setIdProteina(int idProteina) {
        this.idProteina = idProteina;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
