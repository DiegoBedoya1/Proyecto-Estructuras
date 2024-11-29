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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
    Button btListo;
    @FXML
    Button btCancelar;
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
        GeneralController.actualizarContacto(contactoOriginal, contactoEditado);
        InfoContactoController.actualizarArchivo();
    
    }
    
    
    public void volverVentana(){
        try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/mycompany/proyectoestructuras/infoContacto.fxml"));
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
    
   
    private static EditarPController instance;

     public EditarPController() {
        instance = this;
    }

    public static EditarPController getInstance() {
        return instance;
    }
    
    public void setOnSaveAction(EventHandler<ActionEvent> event) {
        btListo.setOnAction(event);
    }

    public TextField getTfnom() {
        return tfnom;
    }

    public void setTfnom(TextField tfnom) {
        this.tfnom = tfnom;
    }

    public TextField getTfape() {
        return tfape;
    }

    public void setTfape(TextField tfape) {
        this.tfape = tfape;
    }

    public TextField getTftel() {
        return tftel;
    }

    public void setTftel(TextField tftel) {
        this.tftel = tftel;
    }

    public TextField getTfdir() {
        return tfdir;
    }

    public void setTfdir(TextField tfdir) {
        this.tfdir = tfdir;
    }

    public TextField getTfurl() {
        return tfurl;
    }

    public void setTfurl(TextField tfurl) {
        this.tfurl = tfurl;
    }

    public TextField getTfpais() {
        return tfpais;
    }

    public void setTfpais(TextField tfpais) {
        this.tfpais = tfpais;
    }

    public TextField getTfemail() {
        return tfemail;
    }

    public void setTfemail(TextField tfemail) {
        this.tfemail = tfemail;
    }

   
    
}
