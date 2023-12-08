package com.example.restaurante.controller;

import com.example.restaurante.modelos.Categoria;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoriaController {
    private ObservableList<Categoria> categorias = FXCollections.observableArrayList();
    private Connection conexion = Conexion.getConnection();
    public CategoriaController() {

    }

    public boolean createCategory(String category, String img) {
        // Consulta SQL
        String consulta = "insert into categories (category, img) values (?, ?)";
        try {
            assert conexion != null;
            try (PreparedStatement statement = conexion.prepareStatement(consulta)) {
                statement.setString(1, category);
                statement.setString(2, img);
                return statement.execute();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean updateCategory(Categoria categoria) {
        // Consulta SQL
        String consulta = "update categories set category=?, img=? where id=?";
        try {
            assert conexion != null;
            try (PreparedStatement statement = conexion.prepareStatement(consulta)) {
                statement.setString(1, categoria.getCategory());
                statement.setString(2, categoria.getImg());
                statement.setInt(3, categoria.getId());
                return statement.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            System.out.println("La consulta3");
        }
        return false;
    }

    public boolean deleteCategoryByID(int id) {
        // Consulta SQL
        String consulta = "Delete from categories where id=?";
        try {
            assert conexion != null;
            try (PreparedStatement statement = conexion.prepareStatement(consulta)) {
                statement.setInt(1, id);
                // Ejecutar la consulta
                return statement.execute();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public ObservableList<Categoria> getAllCategory() {
        categorias.clear();
        // Consulta SQL
        String consulta = "SELECT id, category, img FROM categories";

        // Preparar la declaración SQL con un parámetro
        try {
            assert conexion != null;
            try (PreparedStatement statement = conexion.prepareStatement(consulta)) {
                // Ejecutar la consulta
                try (ResultSet resultSet = statement.executeQuery()) {
                    // Verificar si se encontraron resultados
                    while (resultSet.next()) {
                        // Crear una instancia de Persona con los resultados
                        int id = resultSet.getInt("id");
                        String categoria = resultSet.getString("category");
                        String img = resultSet.getString("img");
                        categorias.add(new Categoria(id, categoria, img));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categorias;
    }

    public ObservableList<String> getAllCategoryName() {
        categorias.clear();
        ObservableList<String> names = FXCollections.observableArrayList();
        // consulta SQL
        String consulta = "Select category from categories";
        try {
            assert conexion != null;
            try (PreparedStatement statement = conexion.prepareStatement(consulta)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while(resultSet.next()) {
                        names.add(resultSet.getString("category"));
                    }
                }
            }
        } catch (SQLException err) {
            System.out.println(err.getMessage());
        }
        return null;
    }

    public String getCategoryNameByID(int category) {
        // Consulta SQL
        String consulta = "select category from categories where id=?";
        try {
            assert conexion != null;
            try (PreparedStatement statement = conexion.prepareStatement(consulta)) {
                statement.setInt(1, category);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getString("category");
                    }
                }
            }
        } catch (SQLException sql) {
            System.out.println(sql.getMessage());
        }
        return "";
    }

    public Integer getCategoryIDbyName(String category) {
        String consulta = "select id from categories where category=?";
        try {
            assert conexion != null;
            try (PreparedStatement statement = conexion.prepareStatement(consulta)) {
                statement.setString(1, category);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getInt("id");
                    }
                }
            }
        } catch (SQLException err) {
            System.out.println(err.getMessage());
        }
        return -1;
    }
}
