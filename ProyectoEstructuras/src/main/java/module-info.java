module com.mycompany.proyectoestructuras {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.proyectoestructuras to javafx.fxml;
    exports com.mycompany.proyectoestructuras;
}
