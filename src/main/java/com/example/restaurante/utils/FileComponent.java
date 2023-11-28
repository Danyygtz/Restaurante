package com.example.restaurante.utils;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.File;

public class FileComponent {
    public ImageView getImageView(String img) {
        Image imagenCargada = new Image(new File("src/main/java/com/example/restaurante/imagenes/"+img).toURI().toString());
        return new ImageView(imagenCargada);
    }

    public VBox getExitButton() {

        ImageView exit = getImageView("exit.jpeg");
        exit.setFitHeight(200);
        exit.setFitWidth(200);

        Button btn = new Button();
        btn.setGraphic(exit);
        btn.setPrefSize(110, 110);
        btn.setOnAction(actionEvent -> System.exit(0));
        Label lblNombre = new Label("Salir");
        VBox vbox = new VBox(btn, lblNombre);
        vbox.setAlignment(Pos.CENTER);
        return vbox;
    }

    public VBox getReturnButton(Stage stagePadre) {

        ImageView exit = getImageView("exit.jpeg");
        exit.setFitHeight(200);
        exit.setFitWidth(200);

        Button btn = new Button();
        btn.setGraphic(exit);
        btn.setPrefSize(110, 110);
        btn.setOnAction(actionEvent -> {

        });
        Label lblNombre = new Label("Salir");
        VBox vbox = new VBox(btn, lblNombre);
        vbox.setAlignment(Pos.CENTER);
        return vbox;
    }
}
