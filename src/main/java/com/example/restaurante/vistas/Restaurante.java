package com.example.restaurante.vistas;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;

public class Restaurante extends Stage {

    private Scene scene;
    private GridPane grdTablilla;
    private BorderPane borderPane;
    private VBox vBoxMenu;
    private HBox hBoxMenu;
    private Button btnBebidas;
    private String[] categorias = {"exit","img1","img2","img3"};
    private String[] lblcategorias = {"Salir","Bebidas","Tacos","Especialidades"};

    private Button[][] arBtnTablilla;
    private String[][] template =
            {
                    {

                    }
            };


    public Restaurante() {
    CrearUI();
    scene = new Scene(borderPane, 900,500);
    this.setTitle("Restaurante");
    this.setScene(scene);
    this.show();
    }


    private void CrearUI(){
        borderPane = new BorderPane();
        hBoxMenu = new HBox();

        int i = 0,j = 0;
        for(String img:categorias){
            Image imgCategorias = new Image(new File("src/main/java/com/example/restaurante/imagenes/"+img+".jpeg").toURI().toString());
            ImageView imv = new ImageView(imgCategorias);
            imv.setFitHeight(200);
            imv.setFitWidth(200);

            Label lblMenu = new Label(lblcategorias[i]);
            btnBebidas = new Button();
            btnBebidas.setGraphic(imv);
            btnBebidas.setPrefSize(110, 110);
            vBoxMenu = new VBox(btnBebidas,lblMenu);
            vBoxMenu.setAlignment(Pos.CENTER);
            hBoxMenu.getChildren().add(vBoxMenu);
            j++;
            i++;
        }

        borderPane.setTop(hBoxMenu);
    }
}
