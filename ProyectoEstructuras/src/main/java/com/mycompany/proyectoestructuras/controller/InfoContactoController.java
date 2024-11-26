/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.proyectoestructuras.controller;

import com.mycompany.proyectoestructuras.Company;
import com.mycompany.proyectoestructuras.Contact;
import com.mycompany.proyectoestructuras.Person;
import com.mycompany.proyectoestructuras.structures.MyCircleDoubleLinkedList;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author flsan
 */
public class InfoContactoController implements Initializable {

    @FXML private Label contactName;
    @FXML private Label contactLastName;
    @FXML private Label contactPhone;
    @FXML private Label contactAddress;
    @FXML private Label contactEmail;
    @FXML private Label contactCountry;
    @FXML private Label contactType;
    @FXML private Circle contactPhoto;
    @FXML private Button editButton;
    @FXML private Button deleteButton;
    @FXML private Button closeButton;
    @FXML private Button previousButton;
    @FXML private Button nextButton;


    private Contact currentContact;
    private MyCircleDoubleLinkedList<Contact> contactList = Contact.cargarContactosCircular("Contactos.txt");
    private int currentIndex = 0; // Para mantener el índice del contacto actual en la lista


    public void setContacto(Contact contacto) {
        this.currentContact = contacto; // Guardar el contacto actual

        if (contacto instanceof Person) {
            Person persona = (Person) contacto;

            // Asignar valores a los campos correspondientes
            contactName.setText(persona.getName() != null ? persona.getName() : "Sin información");
            contactLastName.setText(persona.getLastName() != null ? persona.getLastName() : "Sin información");
            contactPhone.setText(persona.getPhoneNumber() != null ? persona.getPhoneNumber() : "Sin información");
            contactAddress.setText(persona.getAddress() != null ? persona.getAddress().getAddress() : "Sin información");
            contactEmail.setText(persona.getEmail() != null ? persona.getEmail() : "Sin información");
            contactCountry.setText(persona.getCountry() != null ? persona.getCountry() : "Sin información");
            contactType.setText("Persona");
        } else if (contacto instanceof Company) {
            Company empresa = (Company) contacto;

            // Asignar valores a los campos correspondientes
            contactName.setText(empresa.getName() != null ? empresa.getName() : "Sin información");
            contactLastName.setText("N/A"); // Las empresas no tienen apellido
            contactPhone.setText(empresa.getPhoneNumber() != null ? empresa.getPhoneNumber() : "Sin información");
            contactAddress.setText(empresa.getAddress() != null ? empresa.getAddress().getAddress() : "Sin información");
            contactEmail.setText(empresa.getEmail() != null ? empresa.getEmail() : "Sin información");
            contactCountry.setText(empresa.getCountry() != null ? empresa.getCountry() : "Sin información");
            contactType.setText("Empresa");
        }

        // Cargar la foto predeterminada
        Image image = new Image(getClass().getResource("/com/mycompany/proyectoestructuras/images/fotoDefault.png").toExternalForm());
        contactPhoto.setFill(new ImagePattern(image));
        contactPhoto.setSmooth(true);
    }

    // Método para cargar el siguiente contacto (circular)
    @FXML
    private void nextContact() {
        if (contactList != null && !contactList.isEmpty()) {
            currentIndex = (currentIndex + 1) % contactList.size(); // Asegura el ciclo
            setContacto(contactList.get(currentIndex));
        }
    }

    // Método para cargar el contacto anterior (circular)
    @FXML
    private void prevContact() {
        if (contactList != null && !contactList.isEmpty()) {
            currentIndex = (currentIndex - 1 + contactList.size()) % contactList.size(); // Asegura el ciclo
            setContacto(contactList.get(currentIndex));
        }
    }

    // Método para cerrar la ventana
    @FXML
    private void closeWindow() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    // Método para editar el contacto (a implementar según la lógica)
    @FXML
    private void editContact() {
        // Implementar la lógica de edición, como abrir un formulario para editar
        System.out.println("Editar contacto: " + currentContact.getName());
    }

    // Método para eliminar el contacto (a implementar según la lógica)
    @FXML
    private void deleteContact() {
        // Implementar la lógica de eliminación, como confirmar y eliminar de la lista
        System.out.println("Eliminar contacto: " + currentContact.getName());
        contactList.remove(currentIndex);
        currentIndex = (currentIndex == 0) ? 0 : currentIndex - 1; // Asegurar que no se quede fuera de los límites
        if (!contactList.isEmpty()) {
            setContacto(contactList.get(currentIndex));
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Inicializar la lista de contactos si es necesario (puedes pasarla desde el controlador anterior)
        if (contactList != null && !contactList.isEmpty()) {
            setContacto(contactList.get(currentIndex)); // Cargar el primer contacto en la vista
        }

        // Deshabilitar los botones de navegación si la lista está vacía
        nextButton.setDisable(contactList == null || contactList.isEmpty());
        previousButton.setDisable(contactList == null || contactList.isEmpty());

        // Configurar las acciones de los botones (si no están configuradas desde FXML)
        editButton.setOnAction(event -> editContact());
        deleteButton.setOnAction(event -> deleteContact());
        closeButton.setOnAction(event -> closeWindow());
        nextButton.setOnAction(event -> nextContact());
        previousButton.setOnAction(event -> prevContact());
    }

}
