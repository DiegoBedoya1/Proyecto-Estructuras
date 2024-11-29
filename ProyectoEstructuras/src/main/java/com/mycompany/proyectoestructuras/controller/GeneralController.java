/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.proyectoestructuras.controller;

import com.mycompany.proyectoestructuras.Company;
import com.mycompany.proyectoestructuras.Contact;
import com.mycompany.proyectoestructuras.Person;
import com.mycompany.proyectoestructuras.structures.MyArrayList;
import java.io.IOException;
import java.net.URL;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
    private Button cancelarBusquedaBtn;
    @FXML
    private ComboBox<String> sortCriteriaComboBox;
    @FXML
    private Circle añadir;
    @FXML
    private TextField buscador;
    @FXML
    private VBox contactList;

    private final Map<Character, VBox> secciones;
    static MyArrayList<Contact> contactos = Contact.cargarContactos("Contactos.txt");
    private final MyArrayList<Contact> contactosFiltrados = new MyArrayList<>();

    public GeneralController() {
        this.secciones = new HashMap<>();
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inicializarSecciones();
        copiarLista(contactos, contactosFiltrados);
        sortCriteriaComboBox.getItems().addAll("Nombre y Apellido", "Cantidad de Atributos", "País");
        sortCriteriaComboBox.setValue(null);
        sortCriteriaComboBox.setOnAction(event -> mostrarContactosConJerarquia());
        buscador.textProperty().addListener((observable, oldValue, newValue) -> filtrarContactos(newValue));
        cancelarBusquedaBtn.setOnAction(event -> cancelarBusqueda());
        Image img1 = new Image(getClass().getResource("/com/mycompany/proyectoestructuras/images/buscar.png").toExternalForm());
        Image img2 = new Image(getClass().getResource("/com/mycompany/proyectoestructuras/images/añadir.png").toExternalForm());
        buscar.setFill(new ImagePattern(img1));
        añadir.setFill(new ImagePattern(img2));
        añadir.setOnMouseClicked(event -> {
            cambiarVentana();
            cerrarVentana();
        });
        mostrarContactosConJerarquia();
    }

    @FXML
    private void mostrarDetallesContacto(Contact contacto) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/mycompany/proyectoestructuras/InfoContacto.fxml"));
            Parent root = fxmlLoader.load();

            InfoContactoController controller = fxmlLoader.getController();
            controller.setContacto(contacto);
            Stage stage = (Stage) añadir.getScene().getWindow();
            stage.close();

            Stage detallesStage = new Stage();
            detallesStage.setTitle("Detalles del Contacto");
            detallesStage.setScene(new Scene(root));
            detallesStage.show();
        } catch (IOException e) {
        }
    }

    @FXML
    private void inicializarSecciones() {
        contactList.getChildren().clear();

        for (char letra = 'A'; letra <= 'Z'; letra++) {
            VBox seccion = new VBox();
            seccion.setSpacing(5);
            seccion.setStyle("-fx-background-color: #f0f0f0; -fx-padding: 10; -fx-border-radius: 10; -fx-background-radius: 10;");
            Text encabezado = new Text(String.valueOf(letra));
            encabezado.setStyle("-fx-font-weight: bold; -fx-fill: black;");
            encabezado.setFont(Font.font("Arial", FontWeight.BOLD, 18));
            seccion.getChildren().add(encabezado);
            contactList.getChildren().add(seccion);
            secciones.put(letra, seccion);
        }
    }

    @FXML
    public void mostrarContactosConJerarquia() {
        String criterio = sortCriteriaComboBox.getValue();

        if (criterio != null && !criterio.isEmpty()) {
            Comparator<Contact> comparator;
            switch (criterio) {
                case "Cantidad de Atributos":
                    comparator = (c1, c2) -> c1.compareByAttributes(c2);
                    break;
                case "País":
                    comparator = (c1, c2) -> c1.compareByCountry(c2);
                    break;
                case "Apellido y Nombre":
                default:
                    comparator = (c1, c2) -> c1.compareTo(c2); 
                    break;
            }
            contactos.sort(comparator);
        }

        inicializarSecciones();

        for (Contact con : contactos) {
//                System.out.println(con.toFileString());
            String nombre = con.getName() != null ? con.getName().toUpperCase() : "";
            if (!nombre.isEmpty()) {
                char letra = nombre.charAt(0); 
                VBox seccion = secciones.get(letra);
                if (seccion != null) {
                    HBox contactoHBox = crearContactoHBox(con);
                    seccion.getChildren().add(contactoHBox);
                }
            }
        }
    }

    @FXML
    private HBox crearContactoHBox(Contact con) {
        HBox contactoHBox = new HBox();
        contactoHBox.setCursor(javafx.scene.Cursor.HAND);
        contactoHBox.setSpacing(15);
        contactoHBox.setAlignment(Pos.CENTER_LEFT);
        contactoHBox.setStyle("-fx-background-radius: 20; -fx-background-color: #ffffff; -fx-padding: 10; -fx-border-color: #d0d0d0; -fx-border-radius: 20; -fx-border-width: 1;");

        Circle fotoCirculo = new Circle(25);
        Image foto = new Image(getClass().getResource("/com/mycompany/proyectoestructuras/images/fotoDefault.png").toExternalForm());
        fotoCirculo.setFill(new ImagePattern(foto));

        VBox datosC = new VBox();
        datosC.setSpacing(5);
        datosC.setAlignment(Pos.CENTER_LEFT);

        Label nombres = new Label();
        nombres.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        nombres.setWrapText(true);

        if (con instanceof Person) {
            Person per = (Person) con;
            nombres.setText((per.getLastName() != null ? per.getName() + " " : "") + per.getLastName());
        } else if (con instanceof Company) {
            Company comp = (Company) con;
            nombres.setText(comp.getName());
        }
        nombres.setStyle("-fx-text-fill: #000000;");
        datosC.getChildren().add(nombres);
        contactoHBox.getChildren().addAll(fotoCirculo, datosC);
        contactoHBox.setOnMouseClicked(event -> mostrarDetallesContacto(con));

        return contactoHBox;
    }

    @FXML
    public void cambiarVentana() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/mycompany/proyectoestructuras/Eleccion.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            Stage detallesStage = new Stage();
            detallesStage.setScene(scene);
            detallesStage.show();
        } catch (IOException e) {
        }
    }

    @FXML
    public void cerrarVentana() {
        Stage stage = (Stage) añadir.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void filtrarContactos(String query) {
        contactosFiltrados.clear();
        if (query.isEmpty()) {
            copiarLista(contactos, contactosFiltrados);
        } else {
            for (Contact con : contactos) {
                boolean matches = false;
                if (con.getName() != null && con.getName().toLowerCase().contains(query.toLowerCase())) {
                    matches = true;
                }
                if (con.getCountry() != null && con.getCountry().toLowerCase().contains(query.toLowerCase())) {
                    matches = true;
                }
                if (matches) {
                    contactosFiltrados.add(con);
                }
            }
        }
        mostrarContactosOrdenados(contactosFiltrados);
    }

    @FXML
    private void cancelarBusqueda() {
        buscador.clear();
        mostrarContactosConJerarquia();
    }

    @FXML
    private void copiarLista(MyArrayList<Contact> fromList, MyArrayList<Contact> toList) {
        toList.clear();
        for (int i = 0; i < fromList.size(); i++) {
            toList.add(fromList.get(i));
        }
    }

    @FXML
    private void mostrarContactosOrdenados(MyArrayList<Contact> contactos) {
        contactList.getChildren().clear();
        for (Contact con : contactos) {
            HBox contactoHBox = crearContactoHBox(con);
            contactList.getChildren().add(contactoHBox);
        }
    }

    
    public static void actualizarContacto(Contact oldContact, Contact newContact) {
        int index = contactos.indexOf(oldContact);
        if (index != -1) {
            contactos.set(index, newContact); // Reemplazar el contacto antiguo por el nuevo
        }
    }
    
}
