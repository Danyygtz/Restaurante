package com.example.restaurante;

import com.example.restaurante.modelos.Conexion;
import com.example.restaurante.vistas.Restaurante;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Stack;

public class HelloApplication extends Application {

    private Scene scene;
    private Menu menuInicio;
    private MenuItem mit;
    //private Stack<Scene> historialVentanas = new Stack<>();
    public void start(Stage stage) throws IOException {

        // connectToDB();
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
