package com.example.ordereats.domain;

public class gestorPorcion {
    private int id_tamano_pocion;
    private String nombre ;
        private double precio;

    public gestorPorcion(int id_tamano_pocion, String nombre, double precio) {
        this.id_tamano_pocion = id_tamano_pocion;
        this.nombre = nombre;
        this.precio = precio;
    }

    public int getId_tamano_pocion() {
        return id_tamano_pocion;
    }

    public void setId_tamano_pocion(int id_tamano_pocion) {
        this.id_tamano_pocion = id_tamano_pocion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
