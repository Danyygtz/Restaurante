package com.example.restaurante.modelos;

public class Ticket {
    private String nombre;
    private Integer cantidad;
    private float costo;

    public Ticket(String nombre, Integer cantidad, float costo) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.costo = costo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public float getCosto() {
        return costo;
    }

    public void setCosto(float costo) {
        this.costo = costo;
    }
}
