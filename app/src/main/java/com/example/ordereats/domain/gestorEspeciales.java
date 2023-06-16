package com.example.ordereats.domain;

public class gestorEspeciales {

    private String nombre;
    private double precio;
    int id_ingrediente_especial;
    boolean estado;

    public gestorEspeciales(String nombre, double precio, int id_ingrediente_especial, boolean estado) {
        this.nombre = nombre;
        this.precio = precio;
        this.id_ingrediente_especial = id_ingrediente_especial;
        this.estado = estado;
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

    public int getId_ingrediente_especial() {
        return id_ingrediente_especial;
    }

    public void setId_ingrediente_especial(int id_ingrediente_especial) {
        this.id_ingrediente_especial = id_ingrediente_especial;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}
