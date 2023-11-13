module com.example.restaurante {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.restaurante to javafx.fxml;
    exports com.example.restaurante;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires mysql.connector.j;
    opens com.example.restaurante.modelos;

}