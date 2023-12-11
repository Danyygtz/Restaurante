package com.example.restaurante.controller;

import com.example.restaurante.modelos.DetalleOrdenes;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.example.restaurante.controller.Conexion.conexion;

public class DetalleOrdenesController {
    public boolean crearDetalleOrden(DetalleOrdenes detalleOrdenes) {
        // Consulta SQL
        String consulta = "insert into orders_details(order_id, food_item_id, quantity, price) values (?, ?, ?, ?)";
        try {
            assert conexion != null;
            try (PreparedStatement statement = conexion.prepareStatement(consulta)) {
                statement.setInt(1, detalleOrdenes.getId_orden());
                statement.setInt(2, detalleOrdenes.getId_foodItem());
                statement.setInt(3, detalleOrdenes.getQuantity());
                statement.setDouble(4, detalleOrdenes.getPrice());
                return statement.execute();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}
