/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.proyectoestructuras.controller;

import com.mycompany.proyectoestructuras.App;
import com.mycompany.proyectoestructuras.Company;
import com.mycompany.proyectoestructuras.Contact;
import com.mycompany.proyectoestructuras.Person;
import com.mycompany.proyectoestructuras.structures.MyArrayList;
import com.mycompany.proyectoestructuras.structures.MyCircleDoubleLinkedList;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Path;
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
    private int currentIndex = 0;

    @FXML
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
            contactLastName.setText(""); 
            contactPhone.setText(empresa.getPhoneNumber() != null ? empresa.getPhoneNumber() : "Sin información");
            contactAddress.setText(empresa.getAddress() != null ? empresa.getAddress().getAddress() : "Sin información");
            contactEmail.setText(empresa.getEmail() != null ? empresa.getEmail() : "Sin información");
            contactCountry.setText(empresa.getCountry() != null ? empresa.getCountry() : "Sin información");
            contactType.setText("Empresa");
        }
        Image image = new Image(getClass().getResource("/com/mycompany/proyectoestructuras/images/fotoDefault.png").toExternalForm());
        contactPhoto.setFill(new ImagePattern(image));
        contactPhoto.setSmooth(true);
    }

    @FXML
    private void nextContact() {
        if (contactList != null && !contactList.isEmpty()) {
            currentIndex = (currentIndex + 1) % contactList.size(); 
            setContacto(contactList.get(currentIndex));
        }
    }


    @FXML
    private void prevContact() {
        if (contactList != null && !contactList.isEmpty()) {
            currentIndex = (currentIndex - 1 + contactList.size()) % contactList.size();
            setContacto(contactList.get(currentIndex));
        }
    }


    @FXML
    private void closeWindow() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void editContact() {
        System.out.println("Editar contacto: " + currentContact.getName());
    }

    @FXML
    private void deleteContact() {
        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Eliminar Contacto");
        confirmAlert.setHeaderText("¿Estás seguro de que deseas eliminar este contacto?");
        confirmAlert.setContentText("Esta acción es permanente y no se puede deshacer.");

        Optional<ButtonType> result = confirmAlert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                contactList.remove(currentIndex);

                actualizarArchivo();

                if (!contactList.isEmpty()) {
                    currentIndex = currentIndex % contactList.size();
                    setContacto(contactList.get(currentIndex));
                } else {
                    Stage stage = (Stage) closeButton.getScene().getWindow();
                    stage.close();
                }

                // Notificación de éxito
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Contacto Eliminado");
                successAlert.setHeaderText(null);
                successAlert.setContentText("El contacto ha sido eliminado exitosamente.");
                successAlert.showAndWait();

            } catch (IOException e) {
                e.printStackTrace();
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle("Error");
                errorAlert.setHeaderText("No se pudo eliminar el contacto");
                errorAlert.setContentText("Ocurrió un error al intentar actualizar el archivo.");
                errorAlert.showAndWait();
            }
        }
    }

   @FXML
    private void actualizarArchivo() throws IOException {
        java.nio.file.Path filePath = Paths.get(App.pathFiles + "Contactos.txt");        
        MyArrayList<String> updatedLines = new MyArrayList<>();
        for (int i = 0; i < contactList.size(); i++) {
            Contact contact = contactList.get(i);
            String line = construirLinea(contact);
            updatedLines.add(line);
        }

        Files.write(filePath, updatedLines, StandardOpenOption.TRUNCATE_EXISTING);
    }
    @FXML
    private String construirLinea(Contact contact) {
        if (contact instanceof Person) {
            Person person = (Person) contact;
            return String.format("person,%s,%s,%s,%s,%s,%s",
                person.getName(),
                person.getLastName(),
                person.getPhoneNumber(),
                person.getAddress() != null ? person.getAddress().getAddress() : "",
                person.getEmail(),
                person.getCountry());
        } else if (contact instanceof Company) {
            Company company = (Company) contact;
            return String.format("company,%s,%s,%s,%s,%s,%s,%s",
                company.getName(),
                company.getPhoneNumber(),
                company.getRUC(),
                company.getAddress() != null ? company.getAddress().getAddress() : "",
                company.getEmail(),
                company.getCountry(),
                company.getWebPage());
        }
        return "";
    }




    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Inicializar la lista de contactos si es necesario (puedes pasarla desde el controlador anterior)
        if (contactList != null && !contactList.isEmpty()) {
            setContacto(contactList.get(currentIndex)); // Cargar el primer contacto en la vista
        }
        nextButton.setDisable(contactList == null || contactList.isEmpty());
        previousButton.setDisable(contactList == null || contactList.isEmpty());

        editButton.setOnAction(event -> editContact());
        deleteButton.setOnAction(event -> deleteContact());
        closeButton.setOnAction(event -> closeWindow());
        nextButton.setOnAction(event -> nextContact());
        previousButton.setOnAction(event -> prevContact());
    }

}
