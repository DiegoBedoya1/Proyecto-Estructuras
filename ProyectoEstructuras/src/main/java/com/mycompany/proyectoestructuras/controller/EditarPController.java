/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.proyectoestructuras.controller;

import com.mycompany.proyectoestructuras.Address;
import com.mycompany.proyectoestructuras.Contact;
import com.mycompany.proyectoestructuras.Person;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author flsan
 */
public class EditarPController implements Initializable {

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
    
    private Contact contactoOriginal;
    
    @FXML
    public void setContacto(Contact contacto) {
        this.contactoOriginal = contacto;
        
        tfnom.setText(contacto.getName() != null ? contacto.getName() : "");
        tfape.setText(contacto instanceof Person && ((Person) contacto).getLastName() != null ? ((Person) contacto).getLastName() : "");
        tftel.setText(contacto.getPhoneNumber() != null ? contacto.getPhoneNumber() : "");
        tfdir.setText(contacto.getAddress() != null && contacto.getAddress().getAddress() != null ? contacto.getAddress().getAddress() : "");
        tfurl.setText(contacto.getAddress() != null && contacto.getAddress().getCity() != null ? contacto.getAddress().getCity() : "");
        tfpais.setText(contacto.getCountry() != null ? contacto.getCountry() : "");
        tfemail.setText(contacto.getEmail() != null ? contacto.getEmail() : "");
        
    }
    
    public void guardarContacto() {
        Address direccion = new Address(tfdir.getText());
        Contact contactoEditado = new Person("Person", tfnom.getText(), tfape.getText(), tftel.getText(),direccion, tfemail.getText(), tfpais.getText()); 
    
        //GeneralController.actualizarContacto(contactoOriginal, contactoEditado);

        InfoContactoController.actualizarArchivo();
    
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
    
}
