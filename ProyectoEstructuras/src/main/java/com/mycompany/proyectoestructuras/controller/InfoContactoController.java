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
import com.mycompany.proyectoestructuras.structures.CircularDoubleNode;
import com.mycompany.proyectoestructuras.structures.MyArrayList;
import com.mycompany.proyectoestructuras.structures.MyCircleDoubleLinkedList;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Iterator;
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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
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
    @FXML private VBox infoContenedor;


    private Contact currentContact;
    static MyCircleDoubleLinkedList<Contact> contactList = Contact.cargarContactosCircular("Contactos.txt");
    private int currentIndex = 0;

    @FXML
    public void setContacto(Contact contacto) {
       contactName.setText("");
       contactLastName.setText("");
       contactPhone.setText("");
       contactAddress.setText("");
       contactEmail.setText("");
       contactCountry.setText("");
       contactType.setText("");
       
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
            Stage currentStage = (Stage) closeButton.getScene().getWindow();
            currentStage.hide();
            
            if (currentContact instanceof Person) {
                Person persona = (Person) currentContact;
                fxmlLoader = new FXMLLoader(getClass().getResource("/com/mycompany/proyectoestructuras/editarP.fxml"));
                Parent root = fxmlLoader.load();

                EditarPController controller = fxmlLoader.getController();

                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
                controller.setContacto(currentContact);
                controller.setOnSaveAction(event -> {
                    String nuevoNombre = controller.getTfnom().getText();
                    String nuevoTelefono = controller.getTftel().getText();
                    String nuevaDireccion = controller.getTfdir().getText();
                    String nuevoEmail = controller.getTfemail().getText();
                    String nuevoPais = controller.getTfpais().getText();

                    boolean haCambiado = false;

                    if (!nuevoNombre.equals(persona.getName())) {
                        persona.setName(nuevoNombre);
                        haCambiado = true;
                    }
                    if (!nuevoTelefono.equals(persona.getPhoneNumber())) {
                        persona.setPhoneNumber(nuevoTelefono);
                        haCambiado = true;
                    }
                    if (!nuevaDireccion.equals(persona.getAddress() != null ? persona.getAddress().getAddress() : "")) {
                        if (persona.getAddress() == null) {
                            persona.setAddress(new Address(nuevaDireccion));
                        } else {
                            persona.getAddress().setAddress(nuevaDireccion);
                        }
                        haCambiado = true;
                    }
                    if (!nuevoEmail.equals(persona.getEmail())) {
                        persona.setEmail(nuevoEmail);
                        haCambiado = true;
                    }
                    if (!nuevoPais.equals(persona.getCountry())) {
                        persona.setCountry(nuevoPais);
                        haCambiado = true;
                    }

                    if (haCambiado) {
                        
                        MyArrayList.update(persona);
                        
                        for (int i = 0; i < contactList.size(); i++) {
                            if (contactList.get(i).equals(persona)) {
                                contactList.set(i, persona);
                                contactList.remove(i);
                                break;
                            }
                        }
                        contactList = Contact.cargarContactosCircular("Contactos.txt");
                    }
                });

            } else if (currentContact instanceof Company) {
                Company company = (Company) currentContact;
                fxmlLoader = new FXMLLoader(getClass().getResource("/com/mycompany/proyectoestructuras/editarC.fxml"));
                Parent root = fxmlLoader.load();

                EditarCController controller = fxmlLoader.getController();
                Stage stage = new Stage();
                stage.setTitle("Editar Compañía");
                stage.setScene(new Scene(root));
                stage.show();
                controller.setContacto(currentContact);
                controller.setOnSaveAction(event -> {
                    String nuevoNombre = controller.getTfnom().getText();
                    String nuevoTelefono = controller.getTftel().getText();
                    String nuevaDireccion = controller.getTfdir().getText();
                    String nuevoEmail = controller.getTfemail().getText();
                    String nuevoPais = controller.getTfpais().getText();
                    String nuevaUrl = controller.getTfurl().getText(); 
                    String nuevoRuc = controller.getTruc().getText(); 

                    boolean haCambiado = false;

                    if (!nuevoNombre.equals(company.getName())) {
                        company.setName(nuevoNombre);
                        haCambiado = true;
                    }
                    if (!nuevoTelefono.equals(company.getPhoneNumber())) {
                        company.setPhoneNumber(nuevoTelefono);
                        haCambiado = true;
                    }
                    if (!nuevaDireccion.equals(company.getAddress() != null ? company.getAddress().getAddress() : "")) {
                        if (company.getAddress() == null) {
                            company.setAddress(new Address(nuevaDireccion));
                        } else {
                            company.getAddress().setAddress(nuevaDireccion);
                        }
                        haCambiado = true;
                    }
                    if (!nuevoEmail.equals(company.getEmail())) {
                        company.setEmail(nuevoEmail);
                        haCambiado = true;
                    }
                    if (!nuevoPais.equals(company.getCountry())) {
                        company.setCountry(nuevoPais);
                        haCambiado = true;
                    }
                    if (!nuevaUrl.equals(company.getWebPage())) {
                        company.setWebPage(nuevaUrl);
                        haCambiado = true;
                    }
                    if (!nuevoRuc.equals(company.getRUC())) {
                        company.setRUC(nuevoRuc);
                        haCambiado = true;
                    }

                    if (haCambiado) {
                        
                        MyArrayList.update(company);
                        
                        for (int i = 0; i < contactList.size(); i++) {
                            if (contactList.get(i).equals(company)) {
                                contactList.set(i, company);
                                contactList.remove(i);
                                break;
                            }
                        }
                        contactList = Contact.cargarContactosCircular("Contactos.txt");
                    }
                });
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }





    @FXML
    private void deleteContact() {
        Stage currentStage = (Stage) deleteButton.getScene().getWindow();
        currentStage.close();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmación");
        alert.setHeaderText("Eliminar contacto");
        alert.setContentText("¿Está seguro de que desea eliminar este contacto de forma permanente?");
        Optional<ButtonType> result = alert.showAndWait();
        currentStage.show();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            
            int indice = 0;
            Iterator it = contactList.iterator();
            while (it.hasNext()) {
                if (!it.next().equals(currentContact)) {
                    indice++;
                }
                break;
            }
            contactList.remove(indice);
            
            
            Contact contactoAEliminar = currentContact;
            int indexInArrayList = GeneralController.contactos.indexOf(contactoAEliminar);

            if (indexInArrayList != -1) {
                GeneralController.contactos.remove(indexInArrayList);
            }
            
            actualizarArchivo();
            contactList = Contact.cargarContactosCircular("Contactos.txt");
            
            if (!contactList.isEmpty()) {
                currentIndex = (currentIndex == contactList.size()) ? contactList.size() - 1 : currentIndex;
                setContacto(contactList.get(currentIndex));
            } else {
                closeWindow();
            }
        }
    }

    public static void actualizarArchivo() {
        MyArrayList<String> contactosActualizados = new MyArrayList<>();
        for (Contact contacto : GeneralController.contactos) {
            contactosActualizados.add(contacto.toFileString());
        }

        try {
            Files.write(
                Paths.get(App.pathFiles + "Contactos.txt"),
                contactosActualizados,
                StandardOpenOption.TRUNCATE_EXISTING,
                StandardOpenOption.CREATE
            );
        } catch (IOException e) {
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

    public static void actualizarContactoEnCircular(MyCircleDoubleLinkedList<Contact> circularList, Contact oldContact, Contact newContact) {
        CircularDoubleNode<Contact> currentNode = circularList.getHead();

        if (currentNode == null) {
            return;
        }

        do {
            if (currentNode.getData().equals(oldContact)) {
                currentNode.setData(newContact);
                break; 
            }
            currentNode = currentNode.getNext(); 
        } while (currentNode != circularList.getHead()); 


        actualizarArchivo();
    }
    
}
