package com.example.ordereats.domain;

public class gestorCoccion {

 private int id_grado_coccion;
 private String nombre;

 public gestorCoccion(int id_grado_coccion, String nombre) {
  this.id_grado_coccion = id_grado_coccion;
  this.nombre = nombre;
 }

 public int getId_grado_coccion() {
  return id_grado_coccion;
 }

 public void setId_grado_coccion(int id_grado_coccion) {
  this.id_grado_coccion = id_grado_coccion;
 }

 public String getNombre() {
  return nombre;
 }

 public void setNombre(String nombre) {
  this.nombre = nombre;
 }


}
