package com.example.restaurante.modelos;

import java.util.Date;

public class Ordenes {
    private Integer id;
    private Date date;
    private float amount;
    private Pago pago;

    public Ordenes(Integer id, Date date, float amount, Pago pago) {
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.pago = pago;
    }

    public Ordenes(Date date, float amount, Pago pago) {
        this.date = date;
        this.amount = amount;
        this.pago = pago;
    }

    public Ordenes(Date date, float amount) {
        this.date = date;
        this.amount = amount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public java.sql.Date getDate() {
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        return sqlDate;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public Pago getPago() {
        return pago;
    }

    public void setPago(Pago pago) {
        this.pago = pago;
    }
}
