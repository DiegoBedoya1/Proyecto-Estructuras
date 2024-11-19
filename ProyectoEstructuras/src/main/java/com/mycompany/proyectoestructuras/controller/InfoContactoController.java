/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.proyectoestructuras.controller;

import com.mycompany.proyectoestructuras.Contact;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author flsan
 */
public class InfoContactoController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @FXML
    private Circle contactPhoto;
    @FXML
    private Label contactName, contactPhone, contactAddress, contactEmail, contactCountry, contactType;
    @FXML
    private Button editButton, deleteButton, closeButton;

    private Contact currentContact;

    public void setContacto(Contact contacto) {
        this.currentContact = contacto;
        updateUI();
    }

    private void updateUI() {
        // Actualizar la información en los labels
        contactName.setText(currentContact.getName());
        contactPhone.setText(currentContact.getPhoneNumber());
        contactAddress.setText(currentContact.getAddress() != null ? currentContact.getAddress().getAddress() : "Sin dirección");
        contactEmail.setText(currentContact.getEmail() != null ? currentContact.getEmail() : "Sin correo");
        contactCountry.setText(currentContact.getCountry() != null ? currentContact.getCountry() : "Sin país");
        contactType.setText(currentContact.getTipo());

        // Actualizar la foto

        Image image = new Image(getClass().getResource("/com/mycompany/proyectoestructuras/images/fotoDefault.png").toExternalForm());
        contactPhoto.setFill(new ImagePattern(image));
        contactPhoto.setSmooth(true);
    }

    @FXML
    private void closeWindow() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
    
