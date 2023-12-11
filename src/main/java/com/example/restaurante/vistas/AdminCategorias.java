package com.example.restaurante.vistas;

import com.example.restaurante.controller.CategoriaController;
import com.example.restaurante.modelos.Categoria;
import com.example.restaurante.utils.FileComponent;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

public class AdminCategorias extends Stage {
    private Categoria categoriaTemp;
    private String pathImage = "";
    private int row = 0;
    private int col = 0;
    private ImageView imageView = new ImageView();
    private final Label labelMessage = new Label();
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

    private void crearTarjeta(Categoria categoria) {
        ImageView imv = fileComponent.getImageView(categoria.getImg());
        imv.setFitHeight(200);
        imv.setFitWidth(200);

        Button btn = new Button();
        btn.setGraphic(imv);
        btn.setPrefSize(110, 110);

        Label lblNombre = new Label(categoria.getCategory());

        Button btnEliminar = new Button();
        imv = fileComponent.getImageView("eliminar.png");
        imv.setFitHeight(30);
        imv.setFitWidth(60);
        btnEliminar.setGraphic(imv);
        btnEliminar.setOnAction(e -> {
            System.out.println("Eliminado el archivo");
            if(fileComponent.mostrarDialogoDeConfirmacion()) {
                categoriaController.deleteCategoryByID(categoria.getId());
                new AdminCategorias(stagePadre, gridPane, borderPane);
            }
        });

        Button btnEditar = new Button();
        imv = fileComponent.getImageView("editar.png");
        imv.setFitHeight(30);
        imv.setFitWidth(60);
        btnEditar.setGraphic(imv);
        btnEditar.setOnAction(e -> {
            System.out.println("Editando el producto");
            abrirDialog(stagePadre, categoria.getCategory(), categoria.getImg(), categoria.getId());
            new AdminCategorias(stagePadre, gridPane, borderPane);
        });

        HBox hbox = new HBox(btnEditar, btnEliminar);

        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(20);

        VBox vbox = new VBox(btn,lblNombre, hbox);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPrefSize(250,250);
        gridPane.add(vbox, col, row);
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
        for (row = 0; row < vertical && elemento < foodItems.size(); row++) {
            for (col = 0; col < horizontal && elemento < foodItems.size(); col++, elemento++) {
                if (row == 0 && col == 0) {
                    col ++;
                }
                this.categoriaTemp = foodItems.get(elemento);
                crearTarjeta(categoriaTemp);
            }
        }

        // creando bottom

        HBox hbox = new HBox();
        hbox.setAlignment(Pos.CENTER_LEFT);
        if (height < 1000) {
            hbox.setPrefSize(width*.6, 100);
        } else {
            hbox.setPrefSize(width*.6, height*.1);
        }
        hbox.setPadding(new Insets(30));
        hbox.setSpacing(40);

        Label lbl = new Label("Categoria");
        lbl.getStyleClass().add("campo-texto");

        VBox vbox = new VBox();
        vbox.setSpacing(12);

        TextField category = new TextField();
        category.getStyleClass().add("campo-texto");
        //vbox.getChildren().addAll(lbl, category);
        //hbox.getChildren().add(vbox);
        hbox.getChildren().addAll(lbl, category);

        lbl = new Label("Imagen");
        lbl.getStyleClass().add("campo-texto");

        Button btnCargar = new Button("CARGAR IMAGEN");
        btnCargar.getStyleClass().add("campo-texto");
        btnCargar.setOnAction(e -> {
            pathImage = fileComponent.cargarImagen(stagePadre);
            imageView.setImage(fileComponent.getImage(pathImage));
            imageView.setFitWidth(50);
            imageView.setFitHeight(50);
            labelMessage.setText(FileComponent.obtenerNombreArchivo(pathImage));
        });

        vbox = new VBox(lbl, labelMessage,imageView, btnCargar);
        vbox.setSpacing(12);

        // hbox.getChildren().add(vbox);
        hbox.getChildren().addAll(lbl, labelMessage, imageView, btnCargar);

        Button btnCrear = new Button("CREAR CATEGORIA");
        btnCrear.getStyleClass().add("campo-texto");
        btnCrear.setOnAction(e -> {
            if (category.getText().isBlank() || labelMessage.getText().isBlank()) {
                FileComponent.mostrarMensaje("NO SE PUEDE AGREGAR EL PRODUCTO, VUELVA A INTENTAR...");
            } else if (fileComponent.mostrarDialogoDeConfirmacion()){
                try {
                    categoriaController.createCategory(category.getText(), labelMessage.getText());
                    fileComponent.subirImagen(pathImage, labelMessage.getText());
                    System.out.println("Categoria creada");
                    new AdminCategorias(stagePadre, gridPane, borderPane);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        vbox = new VBox(btnCrear);
        vbox.setAlignment(Pos.CENTER);
        //hbox.getChildren().add(vbox);
        hbox.getChildren().add(btnCrear);
        hbox.getStyleClass().add("borde-bloque");

        borderPane.setBottom(hbox);
    }

    private void abrirDialog(Stage primaryStage, String cateNombre, String cateImg, Integer id) {

        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        AtomicReference<String> imgFile = new AtomicReference<>("");

        // Calcular las coordenadas para centrar el diálogo en la ventana principal
        double centerX = bounds.getMinX() + bounds.getWidth() / 2;
        double centerY = bounds.getMinY() + bounds.getHeight() / 2;

        Dialog<String> dialog = new Dialog<>();
        // Configurar las coordenadas del diálogo
        dialog.setX(centerX - dialog.getWidth() / 2);
        dialog.setY(centerY - dialog.getHeight() / 2);
        dialog.setResizable(false);
        dialog.setHeight(350);
        dialog.setWidth(100);
        dialog.setTitle("AGREGAR CATEGORIA");
        categoriaTemp.setImg(cateImg);
        ImageView cImage = fileComponent.getImageView(cateImg);
        cImage.setFitWidth(50);
        cImage.setFitHeight(50);

        // Crear campos y controles del diálogo
        GridPane grid = new GridPane();
        TextField campoNombre = new TextField(cateNombre);
        Button botonElegirImagen = new Button("Elegir Imagen");

        grid.add(new Label("Nombre:"), 0, 0);
        grid.add(campoNombre, 1, 0);
        grid.add(botonElegirImagen, 1, 1);
        grid.add(cImage, 1, 2);

        // Configurar el diálogo
        dialog.getDialogPane().setContent(grid);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        // Lógica para elegir una imagen
        botonElegirImagen.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Seleccionar Imagen");
            File selectedFile = fileChooser.showOpenDialog(primaryStage);

            if (selectedFile != null) {
                // Puedes realizar acciones adicionales con la ruta de la imagen
                cImage.setImage(fileComponent.getImage(selectedFile.getAbsolutePath()));
                cImage.setFitWidth(50);
                cImage.setFitHeight(50);
                imgFile.set(selectedFile.getName());
                this.categoriaTemp.setImg(selectedFile.getName());
            }
        });

        // Lógica para finalizar y procesar los datos
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.OK) {
                // Obtener datos ingresados
                String nombre = campoNombre.getText();
                this.categoriaTemp.setCategory(campoNombre.getText());
                this.categoriaTemp.setId(id);
                // Puedes realizar acciones adicionales con los datos ingresados
                if (nombre.isBlank() || nombre.isEmpty()) {
                    return null;
                }
                return "ok";
            }
            return null;
        });

        // Mostrar el diálogo y esperar hasta que se cierre
        dialog.showAndWait().ifPresent(resultado -> {
            // Puedes realizar acciones adicionales con el resultado si es necesario
            if (resultado.equals("ok")) {
                System.out.println("DATOS DE SALIDA");
                System.out.println(categoriaTemp.getImg());
                System.out.println(categoriaTemp.getCategory());
                System.out.println(categoriaTemp.getId());
                System.out.println("===============");
                categoriaController.updateCategory(this.categoriaTemp);
                new AdminCategorias(stagePadre, gridPane, borderPane);
            }
        });
    }
}
