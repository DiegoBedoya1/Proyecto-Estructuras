/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.proyectoestructuras.controller;

import com.mycompany.proyectoestructuras.Company;
import com.mycompany.proyectoestructuras.Contact;
import com.mycompany.proyectoestructuras.Person;
import com.mycompany.proyectoestructuras.structures.MyArrayList;
import com.mycompany.proyectoestructuras.structures.MyCircleDoubleLinkedList;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author flsan
 */
public class GeneralController implements Initializable {
    
    @FXML
    private ScrollPane contactScrollPane;
    @FXML
    private Circle buscar;
    @FXML
    private Circle añadir;
    @FXML
    private TextField buscador;
    @FXML
    private VBox contactList;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        inicializarSecciones();
        try{
            mostrarContactos();
        }catch(IOException | RuntimeException e){
            System.out.println("Ha ocurrido un error");
        }
        
        Image img1 = new Image(getClass().getResource("/com/mycompany/proyectoestructuras/images/buscar.png").toExternalForm());
        Image img2 = new Image(getClass().getResource("/com/mycompany/proyectoestructuras/images/añadir.png").toExternalForm());
        buscar.setFill(new ImagePattern(img1));
        añadir.setFill(new ImagePattern(img2));
        añadir.setOnMouseClicked(event -> {
                cambiarVentana();
                cerrarVentana();
            });
    }
    
    private Map<Character, VBox> secciones = new HashMap<>();
    
    @FXML
    private void mostrarDetallesContacto(Contact contacto) {
        try {
            // Cargar la ventana de detalles
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/mycompany/proyectoestructuras/InfoContacto.fxml"));
            Parent root = fxmlLoader.load();

            // Obtener el controlador de la ventana de detalles
            InfoContactoController controller = fxmlLoader.getController();
            controller.setContacto(contacto); // Pasar el contacto al controlador

            // Configurar y mostrar la ventana
            Stage detallesStage = new Stage();
            detallesStage.setTitle("Detalles del Contacto");
            detallesStage.setScene(new Scene(root));
            detallesStage.show();
        } catch (IOException e) {
            e.printStackTrace(); // Imprimir el error para depuración
        }
    }
    
    private void inicializarSecciones() {
        // Limpiar el VBox antes de agregar nuevas secciones
        contactList.getChildren().clear();

        // Crear las secciones de letras A-Z
        for (char letra = 'A'; letra <= 'Z'; letra++) {
            VBox seccion = new VBox();
            seccion.setSpacing(5);
            seccion.setStyle("-fx-background-color: #f0f0f0; -fx-padding: 10; -fx-border-radius: 10; -fx-background-radius: 10;");

            // Encabezado de la letra
            Text encabezado = new Text(String.valueOf(letra));
            encabezado.setStyle("-fx-font-weight: bold; -fx-fill: black;");
            encabezado.setFont(Font.font("Arial", FontWeight.BOLD, 18));

            seccion.getChildren().add(encabezado); // Agregar encabezado
            contactList.getChildren().add(seccion); // Agregar la sección al VBox principal

            // Guardar la sección en el mapa para referencia futura
            secciones.put(letra, seccion);
        }
    }

    @FXML
    public void mostrarContactos() throws IOException {
        // Cargar los contactos desde el archivo
        MyArrayList<Contact> contactos = Contact.cargarContactos("Contactos.txt");
        MyCircleDoubleLinkedList<Contact> contactList = Contact.cargarContactosCircular("Contactos.txt");
        for (Contact x : contactos) {
            System.out.println(x);
        }
        
        // Recorrer los contactos y agregarlos a las secciones correspondientes
        for (Contact con : contactos) {
            String nombre = con.getName().toUpperCase();
            if (nombre.isEmpty()) continue; // Ignorar contactos sin nombre

            char letra = nombre.charAt(0); // Obtener la letra inicial

            VBox seccion = secciones.get(letra); // Obtener la sección correspondiente
            if (seccion != null) {
                HBox contactoHBox = crearContactoHBox(con); // Crear el HBox para el contacto
                seccion.getChildren().add(contactoHBox); // Agregar el HBox a la sección correspondiente
            }
        }
    }

    private HBox crearContactoHBox(Contact con) {
        // Crear el HBox para representar al contacto
        HBox contactoHBox = new HBox();
        contactoHBox.setCursor(javafx.scene.Cursor.HAND);
        contactoHBox.setSpacing(15);
        contactoHBox.setAlignment(Pos.CENTER_LEFT);
        contactoHBox.setStyle("-fx-background-radius: 20; -fx-background-color: #ffffff; -fx-padding: 10; -fx-border-color: #d0d0d0; -fx-border-radius: 20; -fx-border-width: 1;");

        // Crear el círculo para la imagen del contacto
        Circle fotoCirculo = new Circle(25);
        Image foto = new Image(getClass().getResource("/com/mycompany/proyectoestructuras/images/fotoDefault.png").toExternalForm());
        fotoCirculo.setFill(new ImagePattern(foto)); // Establecer la imagen como fondo del círculo

        // Crear el VBox para los datos del contacto
        VBox datosC = new VBox();
        datosC.setSpacing(5);
        datosC.setAlignment(Pos.CENTER_LEFT);

        // Crear el label para el nombre del contacto
        Label nombres = new Label();
        nombres.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        nombres.setWrapText(true);

        // Asignar el nombre del contacto
        if (con instanceof Person) {
            Person per = (Person) con;
            nombres.setText(per.getName() + " " + per.getLastName());
        } else if (con instanceof Company) {
            Company comp = (Company) con;
            nombres.setText(comp.getName());
        }
        nombres.setStyle("-fx-text-fill: #000000;");

        // Agregar el nombre al VBox de datos
        datosC.getChildren().add(nombres);

        // Agregar el círculo y los datos al HBox
        contactoHBox.getChildren().addAll(fotoCirculo, datosC);

        // Agregar evento de clic para mostrar detalles
        contactoHBox.setOnMouseClicked(event -> mostrarDetallesContacto(con));

        return contactoHBox;
    }
    
    public void cambiarVentana(){
        try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/mycompany/proyectoestructuras/añadirVentana.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage detallesStage = new Stage();
        detallesStage.setScene(scene);
        detallesStage.show();
        
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    public void cerrarVentana() {
        Stage stage = (Stage) añadir.getScene().getWindow();
        stage.close(); 
    }
    
}
    

