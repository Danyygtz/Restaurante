package com.example.restaurante.vistas;

import com.example.restaurante.utils.FileComponent;
import javafx.event.ActionEvent;
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
    private BorderPane borderPane;
    private VBox vBoxMenu;
    private HBox hBoxMenu;
    private Button btnCategorias;
    private String[] categorias = {"img1.jpeg","img2.jpeg","img3.jpeg", "img4.jpg", "img5.jpg"};
    private String[] lblcategorias = {"Bebidas","Tacos","Especialidades", "Botanas", "Hamburguesas"};

    private Button[][] arBtnTablilla;
    private FileComponent fileComponent = new FileComponent();
    private Stack<Scene> =
    public Restaurante(Stage stagePadre) {
    CrearUI(stagePadre);
    scene = new Scene(borderPane);
    /*
    * this.setTitle("Restaurante");
    this.setScene(scene);
    this.setFullScreen(true);
    * */
    stagePadre.setScene(scene);
    stagePadre.show();
    }


    private void CrearUI(Stage stagePadre){
        borderPane = new BorderPane();
        hBoxMenu = new HBox();
        hBoxMenu.getChildren().add(fileComponent.getExitButton());
        int i = 0;
        for(String img: categorias){
            ImageView imv = fileComponent.getImageView(img);
            imv.setFitHeight(200);
            imv.setFitWidth(200);

            Label lblNombre = new Label(lblcategorias[i]);
            btnCategorias = new Button();
            btnCategorias.setGraphic(imv);
            btnCategorias.setPrefSize(110, 110);

                int fi = i;
            btnCategorias.setOnAction(actionEvent -> {
                    new DetalleCategoria(stagePadre, lblcategorias[fi]);
                });

            vBoxMenu = new VBox(btnCategorias,lblNombre);
            vBoxMenu.setAlignment(Pos.CENTER);
            hBoxMenu.getChildren().add(vBoxMenu);
            i++;
        }

        borderPane.setTop(hBoxMenu);
    }
}
