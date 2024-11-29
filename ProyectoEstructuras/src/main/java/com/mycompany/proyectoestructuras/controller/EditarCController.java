/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.proyectoestructuras.controller;

import com.mycompany.proyectoestructuras.Address;
import com.mycompany.proyectoestructuras.Company;
import com.mycompany.proyectoestructuras.Contact;
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
public class EditarCController implements Initializable {
    
    @FXML
    Button btListo;
    @FXML
    Button btCancelar;
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
    TextField truc;
    @FXML
    TextField tweb;
    
    private Contact contactoOriginal;

    public void setContacto(Contact contacto) {
        this.contactoOriginal = contacto;

        tfnom.setText(contacto.getName() != null ? contacto.getName() : "");
        tftel.setText(contacto.getPhoneNumber() != null ? contacto.getPhoneNumber() : "");
        tfdir.setText(contacto.getAddress() != null && contacto.getAddress().getAddress() != null ? contacto.getAddress().getAddress() : "");
        tfurl.setText(contacto.getAddress() != null && contacto.getAddress().getCity() != null ? contacto.getAddress().getCity() : "");
        tfpais.setText(contacto.getCountry() != null ? contacto.getCountry() : "");
        tfemail.setText(contacto.getEmail() != null ? contacto.getEmail() : "");
        truc.setText(contacto instanceof Company && ((Company) contacto).getRUC() != null ? ((Company) contacto).getRUC() : "");
        tweb.setText(contacto instanceof Company && ((Company) contacto).getWebPage() != null ? ((Company) contacto).getWebPage() : "");
    }

    public void guardarContacto() {
        Address direccion = new Address(tfdir.getText());
        Contact contactoEditado = new Company(
            "Compania", 
            tfnom.getText(), 
            tftel.getText(), 
            truc.getText(), 
            direccion, 
            tfemail.getText(), 
            tfpais.getText(), 
            tweb.getText()
        );
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
    
     public void setOnSaveAction(EventHandler<ActionEvent> event) {
        btListo.setOnAction(event);
    }

    public TextField getTfnom() {
        return tfnom;
    }

    public void setTfnom(TextField tfnom) {
        this.tfnom = tfnom;
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

    public TextField getTruc() {
        return truc;
    }

    public void setTruc(TextField truc) {
        this.truc = truc;
    }

    public TextField getTweb() {
        return tweb;
    }

    public void setTweb(TextField tweb) {
        this.tweb = tweb;
    }
    
     
    
    
}
