package com.example.restaurante.modelos;

public class DetalleOrdenes {
    private Integer id_orden;
    private Integer id_foodItem;
    private Integer quantity;
    private float price;

    public DetalleOrdenes(Integer id_orden, Integer id_foodItem, Integer quantity, float price) {
        this.id_orden = id_orden;
        this.id_foodItem = id_foodItem;
        this.quantity = quantity;
        this.price = price;
    }

    public Integer getId_orden() {
        return id_orden;
    }

    public void setId_orden(Integer id_orden) {
        this.id_orden = id_orden;
    }

    public Integer getId_foodItem() {
        return id_foodItem;
    }

    public void setId_foodItem(Integer id_foodItem) {
        this.id_foodItem = id_foodItem;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
