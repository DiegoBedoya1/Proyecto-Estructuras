/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectoestructuras;

import com.mycompany.proyectoestructuras.structures.MyArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author diego
 */
public abstract class Contact {
    private String name;
    private String phoneNumber;
    private Address address;
    private String email;
    private String country;
    private String tipo;
    
    public Contact(String tipo,String name, String phoneNumber, Address address, String email, String country){
        this.tipo=tipo;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.email = email;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
      
   public static MyArrayList<Contact> cargarContactos(String fileName) {
    MyArrayList<Contact> contactos = new MyArrayList<>();
    try (BufferedReader br = new BufferedReader(new FileReader(App.pathFiles + fileName))) {
        String linea;
        while ((linea = br.readLine()) != null) {
            // Manejar línea vacía o nula
            if (linea.trim().isEmpty()) continue;

            String[] datos = linea.split(",");
            
            // Validar al menos el tipo (person o compania)
            if (datos.length == 0 || datos[0].isEmpty()) {
                System.err.println("Línea inválida, tipo no especificado: " + linea);
                continue;
            }

            // Extraer valores con manejo de índices fuera de rango
            String tipo = datos[0].trim().toLowerCase();  // 'person' o 'compania'
            String name = datos.length > 1 ? datos[1].trim() : null;
            String phoneNumber = datos.length > 2 ? datos[2].trim() : null;

            // Construir la dirección si está disponible
            Address address = null;
            if (datos.length > 3 && !datos[3].isEmpty()) {
                String direccion = datos[3].trim();
                String urlAddress = datos.length > 7 ? datos[7].trim() : null;
                address = urlAddress != null && !urlAddress.isEmpty()
                        ? new Address(direccion, urlAddress)
                        : new Address(direccion);
            }

            String email = datos.length > 5 ? datos[5].trim() : null;
            String country = datos.length > 6 ? datos[6].trim() : null;

            if (tipo.equals("person")) {
                // Crear una persona
                String lastName = datos.length > 4 ? datos[4].trim() : null;
                Contact persona = new Person(tipo, name, lastName, phoneNumber, address, email, country);
                contactos.add(persona);
            } else if (tipo.equals("compania")) {
                // Crear una compañía
                String RUC = datos.length > 4 ? datos[4].trim() : null;
                String webPage = datos.length > 8 ? datos[8].trim() : null;
                Contact compania = new Company(tipo, name, phoneNumber, RUC, address, email, country, webPage);
                contactos.add(compania);
            } else {
                System.err.println("Tipo desconocido: " + tipo + " en línea: " + linea);
            }
        }
    } catch (IOException e) {
        System.err.println("Error al leer el archivo: " + e.getMessage());
    }
    return contactos;
}

    
    public static void guardarContactos(String linea){
        try(BufferedWriter wr = new BufferedWriter(new FileWriter(App.pathFiles+"Contactos.txt",true))){
            wr.write(linea);
            wr.newLine();         
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
