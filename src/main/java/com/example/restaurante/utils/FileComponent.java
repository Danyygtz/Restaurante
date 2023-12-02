package com.example.restaurante.utils;

import com.example.restaurante.vistas.AdminCategorias;
import com.example.restaurante.vistas.Restaurante;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class FileComponent {
    public ImageView getImageView(String img) {
        Image imagenCargada = new Image(new File("src/main/java/com/example/restaurante/imagenes/" + img).toURI().toString());
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
        exit.setFitHeight(240);
        exit.setFitWidth(200);

        Button btn = new Button();
        btn.setGraphic(exit);
        btn.setPrefSize(110, 110);
        btn.setOnAction(actionEvent -> {
            new Restaurante(stagePadre);
        });

        Label lblNombre = new Label("Regresar");
        VBox vbox = new VBox(btn, lblNombre);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPrefSize(250,300);
        return vbox;
    }

    public VBox crudProductos(Stage stagePadre) {
        ImageView exit = getImageView("exit.jpeg");
        exit.setFitHeight(200);
        exit.setFitWidth(200);

        Button btn = new Button();
        btn.setGraphic(exit);
        btn.setPrefSize(110, 110);
        btn.setOnAction(actionEvent -> System.exit(0));
        Label lblNombre = new Label("CRUD PRODUCTOS");
        VBox vbox = new VBox(btn, lblNombre);
        vbox.setAlignment(Pos.CENTER);
        return vbox;
    }

    public VBox crudCategorias(Stage stagePadre, GridPane gridPane, BorderPane borderPane) {
        ImageView exit = getImageView("exit.jpeg");
        exit.setFitHeight(200);
        exit.setFitWidth(200);

        Button btn = new Button();
        btn.setGraphic(exit);
        btn.setPrefSize(110, 110);
        btn.setOnAction(actionEvent -> {
            new AdminCategorias(stagePadre, gridPane, borderPane);
        });

        Label lblNombre = new Label("CRUD CATEGORIAS");
        VBox vbox = new VBox(btn, lblNombre);
        vbox.setAlignment(Pos.CENTER);
        return vbox;
    }

    public Button getDeleteIcon() {
        ImageView exit = getImageView("eliminar.ico");
        exit.setFitHeight(16);
        exit.setFitWidth(16);

        Button btn = new Button();
        btn.setGraphic(exit);
        btn.setPrefSize(110, 110);
        btn.setOnAction(actionEvent -> System.exit(0));
        return btn;
    }

    private static void abrirTecladoVirtual() {
        String osName = System.getProperty("os.name").toLowerCase();

        try {
            if (osName.contains("win")) {
                // Si el sistema operativo es Windows
                ProcessBuilder processBuilder = new ProcessBuilder("cmd", "/c", "osk.exe");
                processBuilder.start();
            } else {
                System.out.println("La apertura del teclado virtual solo es compatible con Windows.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
