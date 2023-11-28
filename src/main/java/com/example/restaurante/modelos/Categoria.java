package com.example.restaurante.modelos;

public class Categoria {
    private Integer id;
    private Integer category;
    private String img;

    public Categoria(Integer id, Integer category, String img) {
        this.id = id;
        this.category = category;
        this.img = img;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
