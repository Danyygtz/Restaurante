package com.example.restaurante;

import com.example.restaurante.controller.Conexion;
import com.example.restaurante.vistas.Restaurante;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class HelloApplication extends Application {
    public void start(Stage stage) {

        connectToDB();
        stage.initStyle(StageStyle.UNDECORATED);
        //historialVentanas.push(scene);
        new Restaurante(stage);
        //stage.show();
        /*
        scene = new Scene(borderPane, 500, 500);
        scene.getStylesheets()
                        .add(getClass().getResource("/estilos/estilos.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
        */
    }

    public void connectToDB(){
        Conexion.createConnection();
        System.out.println("Conexion establecida");
    }

    public static void main(String[] args) {
        launch();
    }
}
