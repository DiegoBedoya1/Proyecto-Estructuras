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
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
    static MyCircleDoubleLinkedList<Contact> contactList = Contact.cargarContactosCircular("Contactos.txt");
    private int currentIndex = 0;

    @FXML
    public void setContacto(Contact contacto) {
        this.currentContact = contacto;

        if (contacto instanceof Person) {
            Person persona = (Person) contacto;
            contactName.setText(persona.getName() != null ? persona.getName() : "Sin información");
            contactLastName.setText(persona.getLastName() != null ? persona.getLastName() : "Sin información");
            contactPhone.setText(persona.getPhoneNumber() != null ? persona.getPhoneNumber() : "Sin información");
            contactAddress.setText(persona.getAddress() != null ? persona.getAddress().getAddress() : "Sin información");
            contactEmail.setText(persona.getEmail() != null ? persona.getEmail() : "Sin información");
            contactCountry.setText(persona.getCountry() != null ? persona.getCountry() : "Sin información");
            contactType.setText("Persona");
        } else if (contacto instanceof Company) {
            Company empresa = (Company) contacto;
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
    try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/mycompany/proyectoestructuras/general.fxml"));
        Parent root = fxmlLoader.load();

        Stage stage = new Stage();
        stage.setTitle("Gestión de Contactos");
        stage.setScene(new Scene(root));
        stage.show();

        Stage currentStage = (Stage) closeButton.getScene().getWindow();
        currentStage.close();
    } catch (IOException e) {
    }
}


    @FXML
    private void editContact() {
         try {
        FXMLLoader fxmlLoader;
        if (currentContact instanceof Person) {
            Person persona = (Person) currentContact;
            // Cargar el archivo FXML
            fxmlLoader = new FXMLLoader(getClass().getResource("/com/mycompany/proyectoestructuras/añadirVentana.fxml"));
            Parent root = fxmlLoader.load(); 
            
            // Obtener el controlador directamente del FXMLLoader
            AñadirVentanaController controller = fxmlLoader.getController();
            controller.setNom(persona.getName()); 
            controller.setAp(persona.getLastName());
            controller.setDir(persona.getAddress().getAddress());
            controller.setTel(persona.getPhoneNumber());
            controller.setEmail(persona.getEmail());
            controller.setPais(persona.getCountry());
            String nomAct = controller.tfnom.getText();
            System.out.println("nombre actualizado: "+nomAct);
            
            // Mostrar la ventana
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            } else {
                fxmlLoader = new FXMLLoader(getClass().getResource("/com/mycompany/proyectoestructuras/añadirC.fxml"));
            }

            Parent root = fxmlLoader.load();

//            if (currentContact instanceof Person) {
//                AñadirVentanaController controller = fxmlLoader.getController();
//                controller.setContacto((Person) currentContact);
//            } else if (currentContact instanceof Company) {
//                AñadirCController controller = fxmlLoader.getController();
//                controller.setContacto((Company) currentContact);
//            }

            // Mostrar la ventana de edición
            Stage stage = new Stage();
            stage.setTitle("Editar Contacto");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
        }
    }


    @FXML
    private void deleteContact() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmación");
        alert.setHeaderText("Eliminar contacto");
        alert.setContentText("¿Está seguro de que desea eliminar este contacto de forma permanente?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            contactList.remove(currentIndex);
            Contact contactoAEliminar = currentContact;
            int indexInArrayList = GeneralController.contactos.indexOf(contactoAEliminar);

            if (indexInArrayList != -1) {
                GeneralController.contactos.remove(indexInArrayList);
            }
            actualizarArchivo();
            if (!contactList.isEmpty()) {
                currentIndex = (currentIndex == contactList.size()) ? contactList.size() - 1 : currentIndex;
                setContacto(contactList.get(currentIndex));
            } else {
                closeWindow();
            }
        }
    }

    private void actualizarArchivo() {
        MyArrayList<String> contactosActualizados = new MyArrayList<>();
        for (Contact contacto : GeneralController.contactos) {
            contactosActualizados.add(contacto.toString());
        }

        try {
            Files.write(
                Paths.get(App.pathFiles + "Contactos.txt"),
                contactosActualizados,
                StandardOpenOption.TRUNCATE_EXISTING,
                StandardOpenOption.CREATE
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (contactList != null && !contactList.isEmpty()) {
            setContacto(contactList.get(currentIndex));
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
