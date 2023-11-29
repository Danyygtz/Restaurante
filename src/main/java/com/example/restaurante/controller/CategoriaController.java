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

    public CategoriaController() {

    }

    public ObservableList<Categoria> getAllCategory() {
        categorias.clear();
        Connection conexion = Conexion.getConnection();
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
}
