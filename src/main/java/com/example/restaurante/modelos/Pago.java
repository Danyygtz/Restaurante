package com.example.restaurante.modelos;

public class Pago {
    private Integer id;
    private String payment;
    private boolean status;

    public Pago(Integer id, String payment, boolean status) {
        this.id = id;
        this.payment = payment;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
