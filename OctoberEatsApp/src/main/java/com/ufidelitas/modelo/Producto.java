/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufidelitas.modelo;

/**
 *
 * @author isaac
 */
public class Producto {

    private int id;
    private int idRestaurante;
    private String nombre;
    private String descripcion;
    private double precio;
    private int cantidadAcomprar;
    private double subTotal;

    public Producto(int id, int idRestaurante, String nombre, String descripcion, double precio) {
        this.id = id;
        this.idRestaurante = idRestaurante;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
    }
    
    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdRestaurante() {
        return idRestaurante;
    }

    public void setIdRestaurante(int idRestaurante) {
        this.idRestaurante = idRestaurante;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidadAcomprar() {
        return cantidadAcomprar;
    }

    public void setCantidadAcomprar(int cantidadAcomprar) {
        this.cantidadAcomprar = cantidadAcomprar;
    }

    @Override
    public String toString() {
        return nombre + " -> " + cantidadAcomprar;
    }
}
