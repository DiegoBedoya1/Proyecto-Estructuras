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

                // Verificamos que haya al menos 8 campos para evitar errores
                if (datos.length < 8) {
                    System.out.println("Línea ignorada debido a datos incompletos: " + linea);
                    continue;
                }

                String tipo = datos[0].toLowerCase();  // 'person' o 'compania'
                Address address = null;
                String name = datos[1];
                String phoneNumber = datos[2];
                String email = datos[5];
                String country = datos[6];

                if (!datos[3].isEmpty()) {
                    // Si hay una URL de la dirección, creamos la dirección con URL
                    if (datos.length > 8 && !datos[7].isEmpty()) {
                        address = new Address(datos[3], datos[7]);
                    } else {
                        address = new Address(datos[3]);
                    }
                }

                // Cargar personas o empresas dependiendo del tipo
                if (tipo.equals("person")) {
                    String lastName = datos[2];
                    Contact persona = new Person(tipo, name, lastName, phoneNumber, address, email, country);
                    contactos.add(persona);
                } else if (tipo.equals("compania")) {
                    String RUC = datos[3];
                    String webPage = datos.length > 8 ? datos[8] : "";  // Si hay una URL de empresa, la guardamos
                    Contact compania = new Company(tipo, name, phoneNumber, RUC, address, email, country, webPage);
                    contactos.add(compania);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contactos;
    }




    
    public void guardarContactos(String tipo,String linea){
        try(BufferedWriter wr = new BufferedWriter(new FileWriter(App.pathFiles+"Contactos.txt"))){
            wr.write(linea);
            wr.newLine();         
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
