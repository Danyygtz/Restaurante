package com.example.restaurante.vistas;

import com.example.restaurante.controller.CategoriaController;
import com.example.restaurante.modelos.Categoria;
import com.example.restaurante.utils.FileComponent;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class AdminCategorias extends Stage {
    private final Stage stagePadre;
    private final GridPane gridPane;
    private final BorderPane borderPane;
    private final FileComponent fileComponent = new FileComponent();
    private final CategoriaController categoriaController  = new CategoriaController();
    public AdminCategorias(Stage stagePadre, GridPane gridPane, BorderPane borderPane) {
        this.stagePadre = stagePadre;
        this.borderPane = borderPane;
        this.gridPane = gridPane;
        this.gridPane.getChildren().clear();
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        stagePadre.setX(bounds.getMinX());
        stagePadre.setY(bounds.getMinY());
        stagePadre.setWidth(bounds.getWidth());
        stagePadre.setHeight(bounds.getHeight());
        crearUI(bounds.getWidth(), bounds.getHeight());

        stagePadre.setTitle("Categorias");
    }

    private void crearUI(double width, double height) {
        ObservableList<Categoria> foodItems = categoriaController.getAllCategory();
        int horizontal = (int) (width / 200);
        int vertical = (int) (height / 200);

        this.gridPane.setHgap(horizontal);
        this.gridPane.setVgap(vertical);

        VBox vBox = fileComponent.getReturnButton(stagePadre);
        GridPane.setConstraints(vBox, 0, 0);
        gridPane.getChildren().add(vBox);

        int elemento = 0;
        for (int row = 0; row < vertical && elemento < foodItems.size(); row++) {
            for (int col = 0; col < horizontal && elemento < foodItems.size(); col++, elemento++) {
                if (row == 0 && col == 0) {
                    col ++;
                }
                Categoria categoria = foodItems.get(elemento);
                ImageView imv = fileComponent.getImageView(categoria.getImg());
                imv.setFitHeight(200);
                imv.setFitWidth(200);

                Button btn = new Button();
                btn.setGraphic(imv);
                btn.setPrefSize(110, 110);

                Label lblNombre = new Label(categoria.getCategory());

                Button btnEliminar = new Button();
                imv = fileComponent.getImageView("eliminar.png");
                imv.setFitHeight(16);
                imv.setFitWidth(16);
                btnEliminar.setGraphic(imv);
                btnEliminar.setOnAction(e -> {
                    System.out.println("Eliminado el archivo");
                    categoriaController.deleteCategoryByID(categoria.getId());
                });

                Button btnEditar = new Button();
                imv = fileComponent.getImageView("editar.png");
                imv.setFitHeight(16);
                imv.setFitWidth(16);
                btnEditar.setGraphic(imv);
                btnEditar.setOnAction(e -> {
                    System.out.println("Editando el producto");
                });

                HBox hbox = new HBox(btnEditar, btnEliminar);

                hbox.setAlignment(Pos.CENTER);
                hbox.setSpacing(20);

                VBox vbox = new VBox(btn,lblNombre, hbox);
                vbox.setAlignment(Pos.CENTER);
                vbox.setPrefSize(250,250);
                gridPane.add(vbox, col, row);
            }
        }

        // creando bottom

        HBox hbox = new HBox();
        TextField category = new TextField("category");
        hbox.getChildren().add(category);
        borderPane.setBottom(hbox);

    }
}
