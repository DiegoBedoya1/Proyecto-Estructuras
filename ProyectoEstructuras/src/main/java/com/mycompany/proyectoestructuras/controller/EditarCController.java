/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.proyectoestructuras.controller;

import com.mycompany.proyectoestructuras.Address;
import com.mycompany.proyectoestructuras.Company;
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
public class EditarCController implements Initializable {
    
    @FXML
    Label btListo;
    @FXML
    Label btCancelar;
    @FXML
    TextField tfnom;
    @FXML
    TextField tftel;
    @FXML
    TextField tfdir;
    @FXML
    TextField tfurl;
    @FXML
    TextField tfpais;
    @FXML
    TextField tfemail;
    @FXML
    TextField tfruc;
    @FXML
    TextField tfweb;
    
    private Contact contactoOriginal;

    public void setContacto(Contact contacto) {
        this.contactoOriginal = contacto;

        tfnom.setText(contacto.getName() != null ? contacto.getName() : "");
        tftel.setText(contacto.getPhoneNumber() != null ? contacto.getPhoneNumber() : "");
        tfdir.setText(contacto.getAddress() != null && contacto.getAddress().getAddress() != null ? contacto.getAddress().getAddress() : "");
        tfurl.setText(contacto.getAddress() != null && contacto.getAddress().getCity() != null ? contacto.getAddress().getCity() : "");
        tfpais.setText(contacto.getCountry() != null ? contacto.getCountry() : "");
        tfemail.setText(contacto.getEmail() != null ? contacto.getEmail() : "");
        tfruc.setText(contacto instanceof Company && ((Company) contacto).getRUC() != null ? ((Company) contacto).getRUC() : "");
        tfweb.setText(contacto instanceof Company && ((Company) contacto).getWebPage() != null ? ((Company) contacto).getWebPage() : "");
    }

    public void guardarContacto() {
        Address direccion = new Address(tfdir.getText());
        Contact contactoEditado = new Company(
            "Compania", 
            tfnom.getText(), 
            tftel.getText(), 
            tfruc.getText(), 
            direccion, 
            tfemail.getText(), 
            tfpais.getText(), 
            tfweb.getText()
        );

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
