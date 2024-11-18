/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.proyectoestructuras.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

/**
 * FXML Controller class
 *
 * @author flsan
 */
public class AñadirVentanaController implements Initializable {
    
    
    
    @FXML
    Circle añadirTelefono;
    @FXML
    Circle foto;
    @FXML
    Circle cancelar;
    @FXML
    Button guardar;
    @FXML
    TextField TfNombre;
    @FXML
    TextField TfApellido;
    @FXML
    TextField TfEmpresa;
    @FXML
    TextField TfTelefono;
    @FXML
    VBox contTelefonosExtra;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image img1 = new Image(getClass().getResource("/com/mycompany/proyectoestructuras/images/cancelar.png").toExternalForm());
        Image img2 = new Image(getClass().getResource("/com/mycompany/proyectoestructuras/images/añadir.png").toExternalForm());
        Image img3 = new Image(getClass().getResource("/com/mycompany/proyectoestructuras/images/añadirFoto.png").toExternalForm());
        cancelar.setFill(new ImagePattern(img1));
        añadirTelefono.setFill(new ImagePattern(img2));
        foto.setFill(new ImagePattern(img3));
        
        añadirTelefono.setOnMouseClicked(event -> {
                añadirTelefonos();
            });
    }
    
    @FXML
    private void añadirTelefonos() {
        // Crear un nuevo HBox para el nuevo número de teléfono
        HBox nuevoTelefono = new HBox();
        nuevoTelefono.setAlignment(Pos.CENTER);
        
        
        // Crear el texto y el campo de texto
        TextField textFieldTelefono = new TextField();
        textFieldTelefono.setPromptText("Teleono");
        textFieldTelefono.setStyle(
            "-fx-background-radius: 15; " +
            "-fx-border-radius: 15; " +
            "-fx-border-color: #1e90ff;"
        );
        
        // Agregar los elementos al HBox
        nuevoTelefono.getChildren().addAll(textFieldTelefono);
        
        // Añadir el HBox al VBox contenedor
        contTelefonosExtra.setSpacing(10);
        contTelefonosExtra.getChildren().add(nuevoTelefono);
    }
    
    
}
