package com.example.restaurante.vistas;

import com.example.restaurante.controller.CategoriaController;
import com.example.restaurante.modelos.Categoria;
import com.example.restaurante.utils.FileComponent;
import com.example.restaurante.utils.TablaTicket;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
public class Restaurante extends Stage {
    public static final Label lblCosto = new Label("0.00");
    private Scene scene;
    DetalleCategoria detalleCategoria = new DetalleCategoria();
    private Stage stagePadre;
    private BorderPane borderPane;
    private VBox vBoxMenu;
    private Button btnCategorias;
    private CategoriaController cc = new CategoriaController();
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
        ObservableList<Categoria> categorias = cc.getAllCategory();
        borderPane = new BorderPane();
        // Sección Izquierda para Imágenes
        GridPane gridPane = new GridPane();
        int horizontal = (int) (width / 200.0);
        int vertical = (int) (height / 200.0);
        // int horizontal = categorias.size();
        // int vertical = 1;
        gridPane.setHgap(horizontal);
        gridPane.setVgap(vertical);

        gridPane.add(fileComponent.getExitButton(),0,0);

        gridPane.add(fileComponent.crudProductos(stagePadre, gridPane, borderPane),1,0);

        gridPane.add(fileComponent.crudCategorias(stagePadre, gridPane, borderPane),2,0);

        // 1280*720 minimo
        int elemento = 0;
        for (int row = 0; row < vertical && elemento < categorias.size(); row++) {
            for (int col = 0; col < horizontal && elemento < categorias.size(); col++, elemento++) {
                if (row == 0 && col == 0) {
                    col = 3;
                }
                Categoria categoria = categorias.get(elemento);
                ImageView imv = fileComponent.getImageView(categoria.getImg());
                imv.setFitHeight(200);
                imv.setFitWidth(200);

                btnCategorias = new Button();
                btnCategorias.setGraphic(imv);
                btnCategorias.setPrefSize(110, 110);

                Label lblNombre = new Label(categoria.getCategory());

                btnCategorias.setOnAction(actionEvent -> {
                    detalleCategoria.mostrar(stagePadre, categoria.getCategory(), gridPane, categoria.getId());
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

        Button pagar = new Button("PAGAR");
        pagar.setOnAction(e -> {
            if (fileComponent.mostrarDialogoDeConfirmacion()) {
                // Aquí puedes realizar la operación
                FileComponent.mostrarMensaje("Operación realizada con éxito.");
            } else {
                FileComponent.mostrarMensaje("Operación cancelada.");
            }
        });
        pagar.setPrefSize(width*.3, 60);
        pagar.getStyleClass().add("boton-personalizado");

        vBoxMenu = new VBox(tablaTicket.tableView, hbox ,pagar);
        vBoxMenu.setPrefSize(width * .3,height);

        borderPane.setRight(vBoxMenu);
        borderPane.setMaxHeight(height);
    }

}
