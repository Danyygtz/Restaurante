package com.example.restaurante.vistas;

import com.example.restaurante.modelos.FoodItem;
import com.example.restaurante.utils.FileComponent;
import com.example.restaurante.utils.TablaTicket;
import javafx.geometry.Insets;
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

public class DetalleCategoria extends Stage {
    private Stage stagePadre;
    private Scene scene;
    private Stack<Scene> historialVentanas;
    private VBox vBoxMenu;
    private HBox hBoxMenu;
    private Button btnProducto;
    private ArrayList<FoodItem> productos = new ArrayList<>();
    private Button[][] arBtnTablilla;
    String numbers[] = {"0"};
    GridPane gridPane;
    private FileComponent fileComponent = new FileComponent();

    public void mostrar(Stage stagePadre, String title, Stack<Scene> historialVentanas, GridPane gridPane) {
        productos.add(new FoodItem(1, "maruchxan", 10.0f, "img1.jpeg", 1));
        productos.add(new FoodItem(1, "maruchxan", 10.0f, "img2.jpeg", 1));
        productos.add(new FoodItem(1, "maruchxan", 10.0f, "img3.jpeg", 1));
        productos.add(new FoodItem(1, "maruchxan", 10.0f, "img4.jpeg", 1));
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        stagePadre.setX(bounds.getMinX());
        stagePadre.setY(bounds.getMinY());
        stagePadre.setWidth(bounds.getWidth());
        stagePadre.setHeight(bounds.getHeight());
        this.stagePadre = stagePadre;

        this.historialVentanas = historialVentanas;
        this.gridPane = gridPane;
        this.gridPane.getChildren().clear();
        CrearUI(1,2);
        stagePadre.setTitle(title);
    }

    private void CrearUI(){
        // int w = (int) (width / 200.0);
        // int h = (int) (height / 200.0);
        int horizontal = productos.size();
        int vertical = 1;
        gridPane.setHgap(horizontal);
        gridPane.setVgap(vertical);
        VBox vBox = fileComponent.getReturnButton(stagePadre);
        GridPane.setConstraints(vBox, 0, 0);
        gridPane.getChildren().add(vBox);
        // 1280*720 minimo
        int elemento = 0;
        for (int row = 0; row < vertical; row++) {
            for (int col = 0; col < horizontal; col++, elemento++) {
                if (row == 0 && col == 0) {
                    col++;
                }
                FoodItem product = productos.get(elemento);
                ImageView imv = fileComponent.getImageView(product.getImg());
                imv.setFitHeight(200);
                imv.setFitWidth(200);

                btnProducto = new Button();
                btnProducto.setGraphic(imv);
                btnProducto.setPrefSize(110, 110);

                Label lblNombre = new Label(product.getFood());

                Label contador = new Label("0");

                Button btnMas = new Button();
                imv = fileComponent.getImageView("mas.png");
                imv.setFitHeight(30);
                imv.setFitWidth(60);
                btnMas.setGraphic(imv);
                btnMas.setOnAction(actionEvent -> {
                    try {
                        int c = Integer.parseInt(contador.getText());
                        c++;
                        contador.setText(c+"");
                        System.out.println(c);
                    } catch (Exception err) {
                        System.out.println(err.getMessage());
                    }
                });

                Button btnMenos = new Button();
                imv = fileComponent.getImageView("menos.png");
                imv.setFitHeight(30);
                imv.setFitWidth(60);
                btnMenos.setGraphic(imv);
                btnMenos.setOnAction(actionEvent -> {
                    try {
                        int c = Integer.parseInt(contador.getText());
                        if (c == 0) {
                            return;
                        }
                        c--;
                        contador.setText(c+"");
                        System.out.println(c);
                    } catch (Exception err) {
                        System.out.println(err.getMessage());
                    }
                });

                hBoxMenu = new HBox(btnMenos, btnMas, contador);

                vBoxMenu = new VBox(btnProducto, lblNombre);
                vBoxMenu.setAlignment(Pos.CENTER);
                vBoxMenu.setPrefSize(250,350);

                gridPane.add(vBoxMenu, col, row);
            }
        }
    }

    private void CrearUI(double width, double height){
        int horizontal = productos.size();
        int vertical = 1;
        gridPane.setHgap(horizontal);
        gridPane.setVgap(vertical);
        VBox vBox = fileComponent.getReturnButton(stagePadre);
        GridPane.setConstraints(vBox, 0, 0);
        gridPane.getChildren().add(vBox);
        // 1280*720 minimo
        int elemento = 0;
        for (int row = 0; row < vertical; row++) {
            for (int col = 0; col < horizontal; col++, elemento++) {
                if (row == 0 && col == 0) {
                    col++;
                }
                FoodItem product = productos.get(elemento);
                ImageView imv = fileComponent.getImageView(product.getImg());
                imv.setFitHeight(200);
                imv.setFitWidth(200);

                btnProducto = new Button();
                btnProducto.setGraphic(imv);
                btnProducto.setPrefSize(110, 110);

                Label lblNombre = new Label(product.getFood());

                Label contador = new Label("0");
                contador.setPrefSize(25,30);

                Button btnMas = new Button();
                imv = fileComponent.getImageView("mas.png");
                imv.setFitHeight(30);
                imv.setFitWidth(60);
                btnMas.setGraphic(imv);
                btnMas.setOnAction(actionEvent -> {
                    try {
                        int c = Integer.parseInt(contador.getText());
                        c++;
                        contador.setText(c+"");
                        System.out.println(c);
                    } catch (Exception err) {
                        System.out.println(err.getMessage());
                    }
                });

                Button btnMenos = new Button();
                imv = fileComponent.getImageView("menos.png");
                imv.setFitHeight(30);
                imv.setFitWidth(60);
                btnMenos.setGraphic(imv);
                btnMenos.setOnAction(actionEvent -> {
                    try {
                        int c = Integer.parseInt(contador.getText());
                        if (c == 0) {
                            return;
                        }
                        c--;
                        contador.setText(c+"");
                        System.out.println(c);
                    } catch (Exception err) {
                        System.out.println(err.getMessage());
                    }
                });

                hBoxMenu = new HBox(btnMenos, btnMas, contador);

                hBoxMenu.setAlignment(Pos.CENTER);
                hBoxMenu.setSpacing(20);

                vBoxMenu = new VBox(btnProducto,lblNombre, hBoxMenu);
                vBoxMenu.setAlignment(Pos.CENTER);
                vBoxMenu.setPrefSize(250,250);
                gridPane.add(vBoxMenu, col, row);
            }
        }
    }
}
