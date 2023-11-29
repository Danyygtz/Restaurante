package com.example.restaurante.vistas;

import com.example.restaurante.modelos.Ticket;
import com.example.restaurante.utils.FileComponent;
import com.example.restaurante.utils.TablaTicket;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.Stack;

public class Restaurante extends Stage {
    public static final Label lblCosto = new Label("0.00");
    private Scene scene;
    DetalleCategoria detalleCategoria = new DetalleCategoria();
    private Stage stagePadre;
    private BorderPane borderPane;
    private VBox vBoxMenu;
    private Button btnCategorias;
    private String[] categorias = {"img1.jpeg","img2.jpeg","img3.jpeg", "img4.jpg", "img5.jpg"};
    private String[] lblcategorias = {"Bebidas","Tacos","Especialidades", "Botanas", "Hamburguesas"};
    private final FileComponent fileComponent = new FileComponent();
    public Restaurante(Stage stagePadre) {
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        stagePadre.setX(bounds.getMinX());
        stagePadre.setY(bounds.getMinY());
        stagePadre.setWidth(bounds.getWidth());
        stagePadre.setHeight(bounds.getHeight());
        this.stagePadre = stagePadre;
        CrearUI(bounds.getWidth(), bounds.getHeight());
        scene = new Scene(borderPane);
        scene.getStylesheets().add(getClass().getResource("/estilos/estilos.css").toExternalForm());
        stagePadre.setTitle("Restaurante");
        stagePadre.setScene(scene);
        stagePadre.show();
    }

    private void CrearUI(double width, double height){
        borderPane = new BorderPane();
        // Sección Izquierda para Imágenes
        GridPane gridPane = new GridPane();
        // int w = (int) (width / 200.0);
        // int h = (int) (height / 200.0);
        int horizontal = categorias.length;
        int vertical = 1;
        gridPane.setHgap(horizontal);
        gridPane.setVgap(vertical);
        gridPane.add(fileComponent.getExitButton(),0,0);
        // 1280*720 minimo
        int elemento = 0;
        for (int row = 0; row < vertical; row++) {
            for (int col = 0; col < horizontal; col++, elemento++) {
                if (row == 0 && col == 0) {
                    col++;
                }
                ImageView imv = fileComponent.getImageView(categorias[elemento]);
                imv.setFitHeight(200);
                imv.setFitWidth(200);

                btnCategorias = new Button();
                btnCategorias.setGraphic(imv);
                btnCategorias.setPrefSize(110, 110);

                Label lblNombre = new Label(lblcategorias[elemento]);

                int fi = elemento;
                btnCategorias.setOnAction(actionEvent -> {
                    detalleCategoria.mostrar(stagePadre, lblcategorias[fi], gridPane);
                });

                vBoxMenu = new VBox(btnCategorias,lblNombre);
                vBoxMenu.setAlignment(Pos.CENTER);
                vBoxMenu.setPrefSize(250,250);

                gridPane.add(vBoxMenu, col, row);
            }
        }

        borderPane.setLeft(gridPane);

        TablaTicket tablaTicket = new TablaTicket(width * .3, height);

        Label lblMessage = new Label("TOTAL");
        lblMessage.getStyleClass().add("texto-personalizado1");

        lblCosto.getStyleClass().add("texto-personalizado1");
        HBox hbox = new HBox(lblMessage, lblCosto);
        hbox.getStyleClass().add("backgroundTotal");
        hbox.setSpacing(width*.15);

        Button finalizar = new Button("FINALIZAR");
        finalizar.setPrefSize(width*.3, 60);
        finalizar.getStyleClass().add("boton-personalizado");

        vBoxMenu = new VBox(tablaTicket.tableView, hbox ,finalizar);
        vBoxMenu.setPrefSize(width * .3,height);

        borderPane.setRight(vBoxMenu);
        borderPane.setMaxHeight(height);
    }
}
