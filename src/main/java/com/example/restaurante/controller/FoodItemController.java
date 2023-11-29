package com.example.restaurante.controller;

import com.example.restaurante.modelos.Categoria;
import com.example.restaurante.modelos.FoodItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FoodItemController {
    ObservableList<FoodItem> foodItems = FXCollections.observableArrayList();
    public FoodItemController() {

    }
    public ObservableList<FoodItem> getAllFood() {
        foodItems.clear();
        Connection conexion = Conexion.getConnection();
        // Consulta SQL
        String consulta = "SELECT id, food, price, img FROM food_items";

        // Preparar la declaración SQL con un parámetro
        try {
            assert conexion != null;
            try (PreparedStatement statement = conexion.prepareStatement(consulta)) {
                // Ejecutar la consulta
                duplicated(statement);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return foodItems;
    }

    public ObservableList<FoodItem> getAllFoodFromCategory(int id_category) {
        foodItems.clear();
        Connection conexion = Conexion.getConnection();
        // Consulta SQL
        String consulta = "SELECT id, food, price, img, id_category FROM food_items where id_category=?";
        // Preparar la declaración SQL con un parámetro
        try {
            assert conexion != null;
            try (PreparedStatement statement = conexion.prepareStatement(consulta)) {
                statement.setInt(1, id_category);
                // Ejecutar la consulta
                duplicated(statement);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return foodItems;
    }

    private void duplicated(PreparedStatement statement) throws SQLException {
        try (ResultSet resultSet = statement.executeQuery()) {
            // Verificar si se encontraron resultados
            while (resultSet.next()) {
                // Crear una instancia de Persona con los resultados
                int id = resultSet.getInt("id");
                String foodName = resultSet.getString("food");
                float price = resultSet.getFloat("price");
                String img = resultSet.getString("img");
                int idCategoria = resultSet.getInt("id_category");
                foodItems.add(new FoodItem(id, foodName, price, img, idCategoria));
            }
        }
    }
}
