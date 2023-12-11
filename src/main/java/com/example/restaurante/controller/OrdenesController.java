package com.example.restaurante.controller;

import com.example.restaurante.modelos.DetalleOrdenes;
import com.example.restaurante.modelos.Ordenes;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.*;

import java.util.Date;

import static com.example.restaurante.controller.Conexion.conexion;

public class OrdenesController {
    public int crearOrden(Ordenes ordenes) {
        // Consulta SQL
        String consulta = "insert into orders(date_order, amount, id_payment) values (?, ?, 1)";
        try {
            assert conexion != null;
            try (PreparedStatement statement = conexion.prepareStatement(consulta, Statement.RETURN_GENERATED_KEYS)) {

                statement.setDate(1, ordenes.getDate());
                statement.setFloat(2, ordenes.getAmount());
                int filas = statement.executeUpdate();
                if (filas > 0) {
                    ResultSet resultSet = statement.getGeneratedKeys();
                    if (resultSet.next()) {
                        int idGenerado = resultSet.getInt(1);
                        return idGenerado;
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return -1;
    }
}
