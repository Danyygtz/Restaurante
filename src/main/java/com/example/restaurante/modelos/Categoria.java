package com.example.restaurante.modelos;

public class Categoria {
    private Integer id;
    private String category;
    private String img;

    public Categoria(Integer id, String category, String img) {
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
