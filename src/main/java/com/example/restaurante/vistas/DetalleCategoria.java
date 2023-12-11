package com.example.restaurante.vistas;

import com.example.restaurante.controller.FoodItemController;
import com.example.restaurante.modelos.FoodItem;
import com.example.restaurante.utils.FileComponent;
import com.example.restaurante.utils.TablaTicket;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.ArrayList;

public class DetalleCategoria extends Stage {
    private Stage stagePadre;
    private VBox vBoxMenu;
    private HBox hBoxMenu;
    private Button btnProducto;
    private GridPane gridPane;
    private FileComponent fileComponent = new FileComponent();
    private FoodItemController foodItemController = new FoodItemController();
    public void mostrar(Stage stagePadre, String title, GridPane gridPane, int idCategory) {
        System.out.println(foodItemController.getAllFoodFromCategory(idCategory).size());
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        stagePadre.setX(bounds.getMinX());
        stagePadre.setY(bounds.getMinY());
        stagePadre.setWidth(bounds.getWidth());
        stagePadre.setHeight(bounds.getHeight());
        this.stagePadre = stagePadre;
        this.gridPane = gridPane;
        this.gridPane.getChildren().clear();
        CrearUI(bounds.getWidth(), bounds.getHeight(), idCategory);
        stagePadre.setTitle(title);
    }

    private void CrearUI(double width, double height, int idCategory){
        ObservableList<FoodItem> foodItems = foodItemController.getAllFoodFromCategory(idCategory);
        int horizontal = (int) ((width - width*.3) / 200.0);
        int vertical = (int) (height / 200.0);

        gridPane.setHgap(horizontal);
        gridPane.setVgap(vertical);

        VBox vBox = fileComponent.getReturnButton(stagePadre);
        GridPane.setConstraints(vBox, 0, 0);
        gridPane.getChildren().add(vBox);

        // 1280*720 minimo
        int elemento = 0;
        for (int row = 0; row < vertical && elemento < foodItems.size(); row++) {
            for (int col = 0; col < horizontal && elemento < foodItems.size(); col++, elemento++) {
                if (row == 0 && col == 0) {
                    col++;
                }
                FoodItem product = foodItems.get(elemento);
                ImageView imv = fileComponent.getImageView(product.getImg());
                imv.setFitHeight(200);
                imv.setFitWidth(200);

                btnProducto = new Button();
                btnProducto.setGraphic(imv);
                btnProducto.setPrefSize(110, 110);

                Label lblNombre = new Label(product.getFood());

                Label contador = new Label("0");

                if (TablaTicket.dataProductos.containsKey(product.getId())) {
                    contador.setText(TablaTicket.dataProductos.get(product.getId()).size()+"");
                }

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
                        TablaTicket.Item newItem = new TablaTicket.Item(
                                product.getFood(),
                                1,
                                Float.parseFloat(""+product.getPrice())
                        );
                        TablaTicket.data.add(newItem);
                        if (!TablaTicket.dataProductos.containsKey(product.getId())) {
                            ObservableList<TablaTicket.Item> cateProducto = FXCollections.observableArrayList();
                            cateProducto.add(newItem);
                            TablaTicket.dataProductos.put(product.getId(), cateProducto);
                            System.out.println("Producto nuevo agregado");
                        } else {
                            TablaTicket.dataProductos.get(product.getId()).add(newItem);
                            System.out.println("Producto agregado");
                        }
                        Double nuevo_valor = Double.parseDouble(Restaurante.lblCosto.getText()) + product.getPrice();
                        Restaurante.lblCosto.setText(nuevo_valor+"");
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
                        if (TablaTicket.dataProductos.containsKey(product.getId())) {
                            TablaTicket.data.remove(TablaTicket.dataProductos.get(product.getId()).remove(0));
                            System.out.println("Producto eliminado");
                            Double nuevo_valor = Double.parseDouble(Restaurante.lblCosto.getText()) - product.getPrice();
                            Restaurante.lblCosto.setText(nuevo_valor+"");
                        }
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
