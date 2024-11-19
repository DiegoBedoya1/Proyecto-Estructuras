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
                String[] datos = linea.split(",");

                // Manejar datos nulos o vacíos asignando valores directamente
                String tipo = datos[0].toLowerCase();  // 'person' o 'compania'
                String name = datos.length > 1 ? datos[1] : null;
                String phoneNumber = datos.length > 2 ? datos[2] : null;
                Address address = null;

                if (datos.length > 3) {
                    String direccion = datos[3];
                    String urlAddress = datos.length > 7 ? datos[7] : null;
                    address = urlAddress != null ? new Address(direccion, urlAddress) : new Address(direccion);
                }

                String email = datos.length > 5 ? datos[5] : null;
                String country = datos.length > 6 ? datos[6] : null;

                if (tipo.equals("person")) {
                    // Crear persona incluso con valores faltantes o nulos
                    String lastName = datos.length > 4 ? datos[4] : null;
                    Contact persona = new Person(tipo, name, lastName, phoneNumber, address, email, country);
                    contactos.add(persona);
                } else if (tipo.equals("compania")) {
                    // Crear compañía incluso con valores faltantes o nulos
                    String RUC = datos.length > 4 ? datos[4] : null;
                    String webPage = datos.length > 8 ? datos[8] : null;
                    Contact compania = new Company(tipo, name, phoneNumber, RUC, address, email, country, webPage);
                    contactos.add(compania);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
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
