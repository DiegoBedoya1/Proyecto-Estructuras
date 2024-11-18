/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.proyectoestructuras.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

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
    Circle ubi;
    @FXML
    Circle email;
    @FXML
    Circle fecha;
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
        Image img5 = new Image(getClass().getResource("/com/mycompany/proyectoestructuras/images/fecha.png").toExternalForm());
        Image img6 = new Image(getClass().getResource("/com/mycompany/proyectoestructuras/images/ubi.png").toExternalForm());
        Image img7 = new Image(getClass().getResource("/com/mycompany/proyectoestructuras/images/email.png").toExternalForm());
        cancelar.setFill(new ImagePattern(img1));
        añadirTelefono.setFill(new ImagePattern(img2));
        foto.setFill(new ImagePattern(img3));
        fecha.setFill(new ImagePattern(img5));
        ubi.setFill(new ImagePattern(img6));
        email.setFill(new ImagePattern(img7));
        
        añadirTelefono.setOnMouseClicked(event -> {
                añadirTelefonos();
            });
        
        cancelar.setOnMouseClicked(event -> {
                cerrarVentana();
            });
        
        email.setOnMouseClicked(event -> {
                cambiarVentanaEmail();
            });
                
                
        fecha.setOnMouseClicked(event -> {
                cambiarVentanaFecha();
            });
                        
                        
        ubi.setOnMouseClicked(event -> {
                cambiarVentanaUbi();
            });
    }
    
    @FXML
    private void añadirTelefonos() {
        // HBox para el nuevo teléfono
        HBox nuevoTelefono = new HBox();
        nuevoTelefono.setAlignment(Pos.CENTER);
        
        TextField textFieldTelefono = new TextField();
        textFieldTelefono.setPromptText("Teleono");
        textFieldTelefono.setStyle(
            "-fx-background-radius: 20; " +
            "-fx-border-radius: 15; " +
            "-fx-border-color: #1e90ff;" +
            "-fx-border-width: 5"    
        );
        
        Circle eliminar = new Circle(15);
        Image img4 = new Image(getClass().getResource("/com/mycompany/proyectoestructuras/images/-.png").toExternalForm());
        eliminar.setFill(new ImagePattern(img4));
        eliminar.setSmooth(true);
        eliminar.setCursor(javafx.scene.Cursor.HAND);
        eliminar.setOnMouseClicked(event -> contTelefonosExtra.getChildren().remove(nuevoTelefono));
        
        // Agregar los elementos al HBox
        nuevoTelefono.getChildren().addAll(textFieldTelefono, eliminar);
        
        // Añadir el HBox al VBox contenedor
        contTelefonosExtra.setSpacing(10);
        contTelefonosExtra.getChildren().add(nuevoTelefono);
    }
    
    private void cerrarVentana( ) {
        // Obtener la Stage actual y cerrarla
        Stage stage = (Stage) cancelar.getScene().getWindow();
        stage.close();
    }
    
    public void cambiarVentanaUbi(){
        try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/mycompany/proyectoestructuras/ubi.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage detallesStage = new Stage();
        detallesStage.setScene(scene);
        detallesStage.show();
        
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void cambiarVentanaEmail(){
        try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/mycompany/proyectoestructuras/email.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage detallesStage = new Stage();
        detallesStage.setScene(scene);
        detallesStage.show();
        
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void cambiarVentanaFecha(){
        try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/mycompany/proyectoestructuras/fecha.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage detallesStage = new Stage();
        detallesStage.setScene(scene);
        detallesStage.show();
        
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
