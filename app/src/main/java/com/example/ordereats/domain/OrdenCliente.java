package com.example.ordereats.domain;

public class OrdenCliente {


    private int id_user;
    private int id_orden_cliente;
    private int id_plato;
    private int id_tamano_porcion;
    private int id_grado_coccion;
    private int id_tipo_proteina;
    private int id_guarnicion;
    private int id_condimento;
    private int id_nivel_picante;
    private int id_ingrediente_especial;

    public OrdenCliente(int id_user, int id_orden_cliente, int id_plato, int id_tamano_porcion, int id_grado_coccion, int id_tipo_proteina, int id_guarnicion, int id_condimento, int id_nivel_picante, int id_ingrediente_especial) {
        this.id_user = id_user;
        this.id_orden_cliente = id_orden_cliente;
        this.id_plato = id_plato;
        this.id_tamano_porcion = id_tamano_porcion;
        this.id_grado_coccion = id_grado_coccion;
        this.id_tipo_proteina = id_tipo_proteina;
        this.id_guarnicion = id_guarnicion;
        this.id_condimento = id_condimento;
        this.id_nivel_picante = id_nivel_picante;
        this.id_ingrediente_especial = id_ingrediente_especial;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_orden_cliente() {
        return id_orden_cliente;
    }

    public void setId_orden_cliente(int id_orden_cliente) {
        this.id_orden_cliente = id_orden_cliente;
    }

    public int getId_plato() {
        return id_plato;
    }

    public void setId_plato(int id_plato) {
        this.id_plato = id_plato;
    }

    public int getId_tamano_porcion() {
        return id_tamano_porcion;
    }

    public void setId_tamano_porcion(int id_tamano_porcion) {
        this.id_tamano_porcion = id_tamano_porcion;
    }

    public int getId_grado_coccion() {
        return id_grado_coccion;
    }

    public void setId_grado_coccion(int id_grado_coccion) {
        this.id_grado_coccion = id_grado_coccion;
    }

    public int getId_tipo_proteina() {
        return id_tipo_proteina;
    }

    public void setId_tipo_proteina(int id_tipo_proteina) {
        this.id_tipo_proteina = id_tipo_proteina;
    }

    public int getId_guarnicion() {
        return id_guarnicion;
    }

    public void setId_guarnicion(int id_guarnicion) {
        this.id_guarnicion = id_guarnicion;
    }

    public int getId_condimento() {
        return id_condimento;
    }

    public void setId_condimento(int id_condimento) {
        this.id_condimento = id_condimento;
    }

    public int getId_nivel_picante() {
        return id_nivel_picante;
    }

    public void setId_nivel_picante(int id_nivel_picante) {
        this.id_nivel_picante = id_nivel_picante;
    }

    public int getId_ingrediente_especial() {
        return id_ingrediente_especial;
    }

    public void setId_ingrediente_especial(int id_ingrediente_especial) {
        this.id_ingrediente_especial = id_ingrediente_especial;
    }
}
