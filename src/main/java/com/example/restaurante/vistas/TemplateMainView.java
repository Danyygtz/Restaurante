package com.example.restaurante.vistas;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;

public class TemplateMainView extends Stage {
    private BorderPane borderPane;
    private VBox vBoxMenu;
    private HBox hBoxMenu;
    private Button btnBebidas;
    private String[] categorias = {"exit.jpeg","img1.jpeg","img2.jpeg","img3.jpeg", "img4.jpg", "img5.jpg"};
    private String[] lblcategorias = {"Salir","Bebidas","Tacos","Especialidades", "Botanas", "Hamburguesas"};

    private Button[][] arBtnTablilla;

    public TemplateMainView(String title) {
        CrearUI();
        Scene scene = new Scene(borderPane);
        this.setTitle(title);
        this.setScene(scene);
        this.setFullScreen(true);
        this.show();
    }


    private void CrearUI(){
        borderPane = new BorderPane();
        hBoxMenu = new HBox();

        int i = 0;
        for(String img: categorias){
            Image imgCategorias = new Image(new File("src/main/java/com/example/restaurante/imagenes/"+img).toURI().toString());
            ImageView imv = new ImageView(imgCategorias);
            imv.setFitHeight(200);
            imv.setFitWidth(200);

            Label lblMenu = new Label(lblcategorias[i]);
            btnBebidas = new Button();
            btnBebidas.setGraphic(imv);
            btnBebidas.setPrefSize(110, 110);
            if (img.equals("exit.jpeg")) {
                btnBebidas.setOnAction(actionEvent -> System.exit(0));
            }
            vBoxMenu = new VBox(btnBebidas,lblMenu);
            vBoxMenu.setAlignment(Pos.CENTER);
            hBoxMenu.getChildren().add(vBoxMenu);
            i++;
        }

        borderPane.setTop(hBoxMenu);
    }
}
