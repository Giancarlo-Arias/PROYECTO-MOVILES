package com.example.ordereats.domain;

public class gestorPicante {
    private  int id_nivel_picante;
    private String nombre;

    public gestorPicante(int id_nivel_picante, String nombre) {
        this.id_nivel_picante = id_nivel_picante;
        this.nombre = nombre;
    }

    public int getId_nivel_picante() {
        return id_nivel_picante;
    }

    public void setId_nivel_picante(int id_nivel_picante) {
        this.id_nivel_picante = id_nivel_picante;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
