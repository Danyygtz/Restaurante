package com.example.restaurante.vistas;

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
import java.util.Stack;

public class Restaurante extends Stage {

    private Scene scene;
    DetalleCategoria detalleCategoria = new DetalleCategoria();
    private Stage stagePadre;
    private BorderPane borderPane;
    private VBox vBoxMenu;
    private HBox hBoxMenu;
    private Button btnCategorias;
    private String[] categorias = {"img1.jpeg","img2.jpeg","img3.jpeg", "img4.jpg", "img5.jpg"};
    private String[] lblcategorias = {"Bebidas","Tacos","Especialidades", "Botanas", "Hamburguesas"};
    private Button[][] arBtnTablilla;
    private final FileComponent fileComponent = new FileComponent();
    private Stack<Scene> historialVentanas = new Stack<>();
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
        historialVentanas.push(scene);
        stagePadre.setTitle("Restaurante");
        stagePadre.setScene(scene);
        stagePadre.show();
    }

    private void CrearUI(double width, double height){
        borderPane = new BorderPane();
        hBoxMenu = new HBox();
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
                    detalleCategoria.mostrar(stagePadre, lblcategorias[fi], historialVentanas, gridPane);
                });

                vBoxMenu = new VBox(btnCategorias,lblNombre);
                vBoxMenu.setAlignment(Pos.CENTER);
                vBoxMenu.setPrefSize(250,250);

                gridPane.add(vBoxMenu, col, row);
            }
        }

        borderPane.setLeft(gridPane);
        TablaTicket tablaTicket = new TablaTicket(width * .3, height);
        vBoxMenu = new VBox(new VBox(tablaTicket.tableView));
        vBoxMenu.setPrefSize(width * .3,height);
        borderPane.setRight(vBoxMenu);
        borderPane.setMaxHeight(height);
    }
}
