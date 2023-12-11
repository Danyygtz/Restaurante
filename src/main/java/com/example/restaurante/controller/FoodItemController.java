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
    Connection conexion = Conexion.getConnection();
    public FoodItemController() {
        conexion = Conexion.getConnection();
    }
    public ObservableList<FoodItem> getAllFood() {
        foodItems.clear();
        // Consulta SQL
        String consulta = "SELECT id, food, price, img, id_category FROM food_items";

        // Preparar la declaración SQL con un parámetro
        try {
            assert conexion != null;
            try (PreparedStatement statement = conexion.prepareStatement(consulta)) {
                // Ejecutar la consulta
                executeQuery(statement);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return foodItems;
    }

    public ObservableList<FoodItem> getAllFoodFromCategory(int id_category) {
        foodItems.clear();
        // Consulta SQL
        String consulta = "SELECT id, food, price, img, id_category FROM food_items where id_category=?";
        // Preparar la declaración SQL con un parámetro
        try {
            assert conexion != null;
            try (PreparedStatement statement = conexion.prepareStatement(consulta)) {
                statement.setInt(1, id_category);
                // Ejecutar la consulta
                executeQuery(statement);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return foodItems;
    }

    public boolean deleteFoodItemByID(int id) {
        // Consulta SQL
        String consulta = "Delete from food_items where id=?";
        try {
            assert conexion != null;
            try (PreparedStatement statement = conexion.prepareStatement(consulta)) {
                statement.setInt(1, id);
                return statement.execute();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean createFoodItem(FoodItem item) {
        String consulta = "insert into food_items(food, price, img, id_category) values(?,?,?,?)";
        try {
            assert conexion != null;
            try (PreparedStatement statement = conexion.prepareStatement(consulta)) {
                statement.setString(1,item.getFood());
                statement.setDouble(2,item.getPrice());
                statement.setString(3,item.getImg());
                statement.setInt(4,item.getCategoria());
                return statement.execute();
            }
        } catch (SQLException err) {
            System.out.println(err.getMessage());
        }
        return false;
    }

    public boolean updateFoodItem(FoodItem item, String categoryName) {
        // Consulta SQL
        String consulta = "update food_items fd set fd.food=?, fd.price=?, fd.img=?, fd.id_category=(select c.id from categories c where c.category=?) where fd.id=?";
        try {
            assert conexion != null;
            try (PreparedStatement statement = conexion.prepareStatement(consulta)) {
                statement.setString(1, item.getFood());
                statement.setDouble(2, item.getPrice());
                statement.setString(3, item.getImg());
                statement.setString(4, categoryName);
                statement.setInt(5, item.getId());
                return statement.execute();
            }
        } catch (SQLException err) {
            System.out.println(err.getMessage());
        }
        return false;
    }

    public void executeQuery(PreparedStatement statement) throws SQLException {
        try (ResultSet resultSet = statement.executeQuery()) {
            // Verificar si se encontraron resultados
            while (resultSet.next()) {
                // Crear una instancia de Persona con los resultados
                int id = resultSet.getInt("id");
                System.out.println("ID FROM DATABASE"+id);
                String foodName = resultSet.getString("food");
                Double price = resultSet.getDouble("price");
                String img = resultSet.getString("img");
                int idCategoria = resultSet.getInt("id_category");
                foodItems.add(new FoodItem(id, foodName, price, img, idCategoria));
            }
        }
    }
}
