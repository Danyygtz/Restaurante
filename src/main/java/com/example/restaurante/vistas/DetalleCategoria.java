package com.example.restaurante.vistas;

import com.example.restaurante.modelos.FoodItem;
import com.example.restaurante.utils.FileComponent;
import javafx.geometry.Insets;
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
import java.util.ArrayList;

public class DetalleCategoria extends Stage {
    private BorderPane borderPane;
    private VBox vBoxMenu;
    private HBox hBoxMenu;
    private Button btnProducto;
    private ArrayList<FoodItem> productos = new ArrayList<>();
    private Button[][] arBtnTablilla;
    String numbers[] = {"0"};
    private FileComponent fileComponent = new FileComponent();

    public DetalleCategoria(Stage stagePadre, String title) {
        productos.add(new FoodItem(1, "maruchan", 10.0f, "img1.jpeg", 1));
        CrearUI();
        Scene scene = new Scene(borderPane);
        /*
        * this.setTitle(title);

        this.setFullScreen(true);
        * */
        //this.show();
        stagePadre.setTitle(title);
        stagePadre.setScene(scene);
        stagePadre.setFullScreen(true);
        stagePadre.show();
    }

    private void CrearUI(){
        borderPane = new BorderPane();
        hBoxMenu = new HBox();
        hBoxMenu.getChildren().add(fileComponent.getExitButton());
        int i = 0;
        for(FoodItem producto: productos){
            ImageView imv = fileComponent.getImageView(producto.getImg());
            imv.setFitHeight(200);
            imv.setFitWidth(200);
            Label img = new Label();
            img.setGraphic(imv);
            Label lblProductName = new Label(producto.getFood());

            Label contador = new Label("0");

            Button btnMas = new Button();
            imv = fileComponent.getImageView("mas.png");
            imv.setFitHeight(40);
            imv.setFitWidth(40);
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
            imv.setFitHeight(40);
            imv.setFitWidth(40);
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

            HBox hboxProducto = new HBox(btnMenos, btnMas, contador);
            vBoxMenu = new VBox(img, lblProductName,hboxProducto);
            vBoxMenu.setAlignment(Pos.CENTER);
            hBoxMenu.getChildren().add(vBoxMenu);
            hBoxMenu.setPadding(new Insets(20));
            i++;
        }

        borderPane.setTop(hBoxMenu);
    }
}
