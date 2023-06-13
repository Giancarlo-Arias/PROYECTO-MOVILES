package com.example.ordereats.domain;

public class gestorCondimento {

    private int id_Condimento;
    private String nombre;
    private double precio;


    public gestorCondimento(int id_Condimento, String nombre, double precio) {
        this.id_Condimento = id_Condimento;
        this.nombre = nombre;
        this.precio = precio;
    }

    public int getId_Condimento() {
        return id_Condimento;
    }

    public void setId_Condimento(int id_Condimento) {
        this.id_Condimento = id_Condimento;
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
