package com.example.restaurante.vistas;

import com.example.restaurante.controller.CategoriaController;
import com.example.restaurante.controller.FoodItemController;
import com.example.restaurante.modelos.Categoria;
import com.example.restaurante.modelos.FoodItem;
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
import org.w3c.dom.Text;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

public class AdminProductos extends Stage {
    private ComboBox<String> categoriasBox;
    private FoodItem itemTemp;
    private String pathImage = "";
    private int row = 0;
    private int col = 0;
    private ImageView imageView = new ImageView();
    private final Label labelMessage = new Label();
    private TextField precio;
    private final Stage stagePadre;
    private final GridPane gridPane;
    private final BorderPane borderPane;
    private final FileComponent fileComponent = new FileComponent();
    private final FoodItemController foodItemController  = new FoodItemController();
    private final CategoriaController categoriaController = new CategoriaController();
    public AdminProductos(Stage stagePadre, GridPane gridPane, BorderPane borderPane) {
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

        stagePadre.setTitle("Productos");
    }

    private void crearTarjeta(FoodItem producto) {
        ImageView imv = fileComponent.getImageView(producto.getImg());
        imv.setFitHeight(200);
        imv.setFitWidth(200);

        Button btn = new Button();
        btn.setGraphic(imv);
        btn.setPrefSize(110, 110);

        Label lblNombre = new Label(producto.getFood());

        Button btnEliminar = new Button();
        imv = fileComponent.getImageView("eliminar.png");
        imv.setFitHeight(25);
        imv.setFitWidth(30);
        btnEliminar.setGraphic(imv);
        btnEliminar.setOnAction(e -> {
            System.out.println("Eliminado el archivo");
            if(fileComponent.mostrarDialogoDeConfirmacion()) {
                foodItemController.deleteFoodItemByID(producto.getId());
                new AdminProductos(stagePadre, gridPane, borderPane);
            }
        });

        Button btnEditar = new Button();
        imv = fileComponent.getImageView("editar.png");
        imv.setFitHeight(25);
        imv.setFitWidth(30);
        btnEditar.setGraphic(imv);
        btnEditar.setOnAction(e -> {
            System.out.println("Editando el producto");
            try {
                System.out.println("IDIDIDIDIDIDI");
                System.out.println(producto.getFood());
                System.out.println(producto.getId());
                System.out.println("IDIDIDIDIDIDI");
                this.itemTemp.setImg(producto.getImg());
                abrirDialog(stagePadre, producto.getFood(), producto.getImg(), producto.getPrice(), categoriaController.getCategoryNameByID(producto.getCategoria()), producto.getId());
                new AdminProductos(stagePadre, gridPane, borderPane);
            } catch (Exception err) {
                System.out.println(err.getMessage());
            }
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
        ObservableList<FoodItem> foodItems = foodItemController.getAllFood();
        String estilo = "";
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
                this.itemTemp = foodItems.get(elemento);
                crearTarjeta(itemTemp);
            }
        }

        // creando bottom

        HBox hbox = new HBox();
        hbox.setAlignment(Pos.CENTER_LEFT);
        if (height < 1000) {
            hbox.setPrefSize(width, 100);
            estilo = "campo-texto-producto";
            hbox.setSpacing(20);
        } else {
            estilo = "campo-texto";
            hbox.setPrefSize(width, height*.1);
            hbox.setSpacing(40);
        }
        hbox.setPadding(new Insets(30));


        Label lbl = new Label("Producto");
        lbl.getStyleClass().add(estilo);

        TextField itemField = new TextField();

        itemField.setOnMouseClicked(mouseEvent ->
        {
            FileComponent.abrirTecladoVirtual();
        });
        itemField.getStyleClass().add(estilo);
        hbox.getChildren().addAll(lbl, itemField);

        lbl = new Label("Imagen");
        lbl.getStyleClass().add(estilo);

        Button btnCargar = new Button("CARGAR IMAGEN");
        btnCargar.getStyleClass().add(estilo);
        btnCargar.setOnAction(e -> {
            pathImage = fileComponent.cargarImagen(stagePadre);
            imageView.setImage(fileComponent.getImage(pathImage));
            imageView.setFitWidth(50);
            imageView.setFitHeight(50);
            labelMessage.setText(FileComponent.obtenerNombreArchivo(pathImage));
        });

        hbox.getChildren().addAll(lbl, labelMessage, imageView, btnCargar);

        lbl = new Label("Precio");
        lbl.getStyleClass().add(estilo);

        precio = new TextField();
        precio.getStyleClass().add(estilo);
        precio.setOnMouseClicked(mouseEvent ->
        {
            FileComponent.abrirTecladoVirtual();
        });
        itemField.getStyleClass().add(estilo);

        hbox.getChildren().addAll(lbl, precio);

        lbl = new Label("Selecciona la categoria");
        lbl.getStyleClass().add(estilo);

        /*
        * Agregando checkbox para categorias
        *
        * */

        categoriasBox = new ComboBox<>();
        categoriasBox.getStyleClass().add(estilo);

        for(Categoria c : categoriaController.getAllCategory())
        categoriasBox.getItems().add(c.getCategory());

        hbox.getChildren().addAll(lbl, categoriasBox);

        Button btnCrear = new Button("CREAR PRODUCTO");
        btnCrear.getStyleClass().add(estilo);
        btnCrear.setOnAction(e -> {
            if (itemField.getText().isBlank() || labelMessage.getText().isBlank() || precio.getText().isBlank() || categoriasBox.getSelectionModel().getSelectedItem().toString().isBlank()) {
                FileComponent.mostrarMensaje("NO SE PUEDE AGREGAR EL PRODUCTO, VUELVA A INTENTAR...");
            } else if (fileComponent.mostrarDialogoDeConfirmacion()){
                try {
                    Double p = Double.parseDouble(precio.getText());
                    Integer idCategoria = categoriaController.getCategoryIDbyName(categoriasBox.getSelectionModel().getSelectedItem());
                    foodItemController.createFoodItem(new FoodItem(itemField.getText(), p, labelMessage.getText(),  idCategoria));
                    fileComponent.subirImagen(pathImage, labelMessage.getText());
                    System.out.println("PRODUCTO creado");
                    new AdminProductos(stagePadre, gridPane, borderPane);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        hbox.getChildren().add(btnCrear);
        hbox.getStyleClass().add("borde-bloque");

        borderPane.setBottom(hbox);
    }

    private void abrirDialog(Stage primaryStage, String cateNombre, String cateImg, Double catePrecio, String categoria, Integer id) {
        System.out.println(id);
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
        dialog.setTitle("EDITAR PRODUCTO");
        ImageView cImage = fileComponent.getImageView(cateImg);
        cImage.setFitWidth(50);
        cImage.setFitHeight(50);

        // Crear campos y controles del diálogo
        GridPane grid = new GridPane();
        TextField campoNombre = new TextField(cateNombre);
        campoNombre.setOnMouseClicked(mouseEvent ->
        {
            FileComponent.abrirTecladoVirtual();
        });
        TextField campoPrecio = new TextField(catePrecio+"");
        campoPrecio.setOnMouseClicked(mouseEvent ->
        {
            FileComponent.abrirTecladoVirtual();
        });
        Button botonElegirImagen = new Button("Elegir Imagen");

        grid.add(new Label("Nombre:"), 0, 0);
        grid.add(campoNombre, 1, 0);
        grid.add(botonElegirImagen, 1, 1);
        grid.add(cImage, 1, 2);
        grid.add(new Label("Precio"), 0, 3);
        grid.add(campoPrecio, 1, 3);
        grid.add(new Label("Categorias"), 0, 4);
        ComboBox<String> comboDialog = categoriasBox;
        comboDialog.getSelectionModel().select(categoria);
        grid.add(comboDialog, 1, 4);


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
                this.itemTemp.setImg(selectedFile.getName());
            }
        });

        // Lógica para finalizar y procesar los datos
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.OK) {
                // Obtener datos ingresados
                Double p = Double.parseDouble(campoPrecio.getText());
                this.itemTemp.setFood(campoNombre.getText());
                this.itemTemp.setPrice(p);
                this.itemTemp.setCategoria(categoriaController.getCategoryIDbyName(categoriasBox.getSelectionModel().getSelectedItem()));
                this.itemTemp.setId(id);
                // Puedes realizar acciones adicionales con los datos ingresados
                if (itemTemp.getFood().isBlank() || itemTemp.getImg().isBlank()) {
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
                System.out.println(itemTemp.getImg());
                System.out.println(itemTemp.getPrice());
                System.out.println(itemTemp.getId());
                System.out.println(itemTemp.getCategoria());
                System.out.println(itemTemp.getFood());
                System.out.println("===============");
                foodItemController.updateFoodItem(this.itemTemp, categoriasBox.getSelectionModel().getSelectedItem());
                new AdminProductos(stagePadre, gridPane, borderPane);
            }
        });
    }
}
