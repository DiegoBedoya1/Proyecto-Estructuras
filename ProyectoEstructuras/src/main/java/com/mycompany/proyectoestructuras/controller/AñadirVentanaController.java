/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.proyectoestructuras.controller;

import com.mycompany.proyectoestructuras.Address;
import com.mycompany.proyectoestructuras.App;
import com.mycompany.proyectoestructuras.Company;
import com.mycompany.proyectoestructuras.Contact;
import com.mycompany.proyectoestructuras.Person;
import java.io.BufferedWriter;
import java.io.FileWriter;
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
import javafx.scene.control.Label;
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
    Label btListo;
    @FXML
    Label btCancelar;
    @FXML
     TextField tfnom ;
    @FXML
     TextField tfape ;
    @FXML
     TextField tftel ;
    @FXML
     TextField tfdir ;
    @FXML
     TextField tfurl ;
    @FXML
     TextField tfpais ;
    @FXML
     TextField tfemail;
    private static AñadirVentanaController instance;

     public AñadirVentanaController() {
        instance = this;
    }

    public static AñadirVentanaController getInstance() {
        return instance;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btListo.setOnMouseClicked(event -> {
                guardarContacto();
                volverVentana();
                cerrarVentana();
            });
        
        btCancelar.setOnMouseClicked(event -> {
                volverVentana();
            });
    }
    
    
    @FXML
    public void guardarContacto(){
       
         String linea="person"+","+tfnom.getText()+","+tfape.getText()+","+tftel.getText()+","+tfdir.getText()+","+tfurl.getText()+","+tfemail.getText()+","+tfpais.getText();
         Address direccion = new Address(tfdir.getText());
         Person persona = new Person("person",tfnom.getText(),tfape.getText(),tftel.getText(),direccion,tfemail.getText(),tfpais.getText());
        GeneralController.contactos.add(persona);
        InfoContactoController.contactList.add(persona);
        Contact.guardarContactos(linea);
        
    }
    
    public void volverVentana(){
        try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/mycompany/proyectoestructuras/general.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage detallesStage = new Stage();
        detallesStage.setScene(scene);
        detallesStage.show();
        
        } catch (IOException e) {
        }
        Stage stage = (Stage) btCancelar.getScene().getWindow();
        stage.close(); 
        
    }
    
    public void cerrarVentana() {
        Stage stage = (Stage) btListo.getScene().getWindow();
        stage.close(); 
    }
    
    public void setNom(String texto){
        tfnom.setText(texto);
    }
    
    public void setAp(String texto){
        tfape.setText(texto);
    }
     public void setTel(String texto){
        tftel.setText(texto);
    }
      public void setDir(String texto){
        tfdir.setText(texto);
    }
       public void setPais(String texto){
        tfpais.setText(texto);
    }
        public void setEmail(String texto){
        tfemail.setText(texto);
    }
        
    
}
