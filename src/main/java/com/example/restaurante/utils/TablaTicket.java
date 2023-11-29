package com.example.restaurante.utils;

import com.example.restaurante.modelos.Ticket;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TablaTicket {
    public TableView<Item> tableView = new TableView<>();
    public TablaTicket(double width, double height) {
        ObservableList<Ticket> data = FXCollections.observableArrayList();

        // Definir las columnas
        TableColumn<Item, String> columnaString = new TableColumn<>("Comida");
        columnaString.setCellValueFactory(cellData -> cellData.getValue().getStringProperty());
        columnaString.setPrefWidth(width * .33);

        TableColumn<Item, Integer> columnaInteger = new TableColumn<>("Cantidad");
        columnaInteger.setCellValueFactory(cellData -> cellData.getValue().getIntegerProperty().asObject());
        columnaInteger.setPrefWidth(width * .33);

        TableColumn<Item, Float> columnaFloat = new TableColumn<>("Precio");
        columnaFloat.setCellValueFactory(cellData -> cellData.getValue().getFloatProperty().asObject());
        columnaFloat.setPrefWidth(width * .33);

        // Agregar las columnas a la tabla
        tableView.getColumns().addAll(columnaString, columnaInteger, columnaFloat);
        tableView.setPrefHeight(height);

        // Campos de entrada para agregar elementos
        TextField campoString = new TextField();
        TextField campoInteger = new TextField();
        TextField campoFloat = new TextField();

        // Botones para agregar y quitar elementos
        Button botonAgregar = new Button("Agregar");
        botonAgregar.setOnAction(e -> {
            Ticket newItem = new Ticket(
                    campoString.getText(),
                    Integer.parseInt(campoInteger.getText()),
                    Float.parseFloat(campoFloat.getText())
            );
            data.add(newItem);
            limpiarCampos(campoString, campoInteger, campoFloat);
        });

        Button botonQuitar = new Button("Quitar");
        botonQuitar.setOnAction(e -> {
            Item selectedItem = tableView.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                data.remove(selectedItem);
            }
        });

        HBox hbox = new HBox(10, campoString, campoInteger, campoFloat, botonAgregar, botonQuitar);
        VBox vbox = new VBox(10, tableView, hbox);

        /*
        Scene scene = new Scene(vbox, 600, 400);
        stagePadre.setScene(scene);
        stagePadre.setTitle("Tabla de Clase");
        stagePadre.show();
        */
    }

    private void limpiarCampos(TextField... campos) {
        for (TextField campo : campos) {
            campo.clear();
        }
    }

    public static class Item {
        private final SimpleStringProperty stringProperty;
        private final SimpleIntegerProperty integerProperty;
        private final SimpleFloatProperty floatProperty;

        public Item(String stringValue, int intValue, float floatValue) {
            this.stringProperty = new SimpleStringProperty(stringValue);
            this.integerProperty = new SimpleIntegerProperty(intValue);
            this.floatProperty = new SimpleFloatProperty(floatValue);
        }

        public SimpleStringProperty getStringProperty() {
            return stringProperty;
        }

        public SimpleIntegerProperty getIntegerProperty() {
            return integerProperty;
        }

        public SimpleFloatProperty getFloatProperty() {
            return floatProperty;
        }
    }
}
