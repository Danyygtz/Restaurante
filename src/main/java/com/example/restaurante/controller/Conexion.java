package com.example.restaurante.controller;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    private static String server = "localhost";
    private static  String user = "topicos";
    private static String pass = "mysecret";
    private static String db = "restaurante";
    public static Connection conexion = null;

    public static void createConnection(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection("jdbc:mysql://" + server + ":3306/" + db,user,pass);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://" + server + ":3306/" + db,user,pass);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
