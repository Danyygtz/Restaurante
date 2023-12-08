package com.example.restaurante.modelos;

import java.util.ArrayList;

public class FoodItem {
    private Integer id;
    private String food;
    private Double price;
    private String img;
    private Integer categoria;

    public FoodItem(String food, Double price, String img, Integer categoria) {
        this.food = food;
        this.price = price;
        this.img = img;
        this.categoria = categoria;
    }

    public FoodItem(Integer id, String food, Double price, String img, Integer categoria) {
        this.id = id;
        this.food = food;
        this.price = price;
        this.img = img;
        this.categoria = categoria;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Integer getCategoria() {
        return categoria;
    }

    public void setCategoria(Integer categoria) {
        this.categoria = categoria;
    }
}
