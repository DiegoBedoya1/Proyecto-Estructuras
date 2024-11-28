/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectoestructuras;

import com.mycompany.proyectoestructuras.structures.MyArrayList;
import com.mycompany.proyectoestructuras.structures.MyCircleDoubleLinkedList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author diego
 */
public abstract class Contact implements Comparable<Contact> {
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
        this.country = country;
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
            linea = linea.trim();
            if (linea.isEmpty()) continue;
            String[] datos = linea.split(",");
            if (datos.length == 0 || datos[0].isEmpty()) {
                System.err.println("Línea inválida, tipo no especificado: " + linea);
                continue;
            }
            String tipo = datos.length > 0 && !datos[0].trim().isEmpty() ? datos[0].trim().toLowerCase() : "N/A"; // 'person' o 'compania'
            String name = datos.length > 1 ? datos[1].trim() : "";
            String lastName = datos.length > 2 ? datos[2].trim() : "";
            String phoneNumber = datos.length > 3 ? datos[3].trim() : "";
            String address = datos.length > 4 ? datos[4].trim() : "";
            String city = datos.length > 5 ? datos[5].trim() : "";
            String email = datos.length > 6 ? datos[6].trim() : "";
            String country = datos.length > 7 ? datos[7].trim() : "";
            Address addressObj = null;
            if (!address.isEmpty() && !city.isEmpty()) {
                addressObj = new Address(address, city);
            }
            if (tipo.equals("person")) {
                Contact persona = new Person(tipo, name, lastName, phoneNumber, addressObj, email, country);
                contactos.add(persona); // Añadir el contacto incluso si falta información
            } else if (tipo.equals("compania")) {
                String RUC = datos.length > 8 ? datos[8].trim() : "";
                String webPage = datos.length > 9 ? datos[9].trim() : "";
                Contact compania = new Company(tipo, name, phoneNumber, RUC, addressObj, email, country, webPage);
                contactos.add(compania); // Añadir la compañía incluso si falta información
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
    
    public static MyCircleDoubleLinkedList<Contact> cargarContactosCircular(String fileName) {
        MyCircleDoubleLinkedList<Contact> contactos = new MyCircleDoubleLinkedList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(App.pathFiles + fileName))) {
        String linea;
        while ((linea = br.readLine()) != null) {
            linea = linea.trim();
            if (linea.isEmpty()) continue;
            String[] datos = linea.split(",");
            if (datos.length == 0 || datos[0].isEmpty()) {
                System.err.println("Línea inválida, tipo no especificado: " + linea);
                continue;
            }
            String tipo = datos.length > 0 && !datos[0].trim().isEmpty() ? datos[0].trim().toLowerCase() : "N/A"; // 'person' o 'compania'
            String name = datos.length > 1 ? datos[1].trim() : "";
            String lastName = datos.length > 2 ? datos[2].trim() : "";
            String phoneNumber = datos.length > 3 ? datos[3].trim() : "";
            String address = datos.length > 4 ? datos[4].trim() : "";
            String city = datos.length > 5 ? datos[5].trim() : "";
            String email = datos.length > 6 ? datos[6].trim() : "";
            String country = datos.length > 7 ? datos[7].trim() : "";
            Address addressObj = null;
            if (!address.isEmpty() && !city.isEmpty()) {
                addressObj = new Address(address, city);
            }
            if (tipo.equals("person")) {
                Contact persona = new Person(tipo, name, lastName, phoneNumber, addressObj, email, country);
                contactos.add(persona); // Añadir el contacto incluso si falta información
            } else if (tipo.equals("compania")) {
                String RUC = datos.length > 8 ? datos[8].trim() : "";
                String webPage = datos.length > 9 ? datos[9].trim() : "";
                Contact compania = new Company(tipo, name, phoneNumber, RUC, addressObj, email, country, webPage);
                contactos.add(compania); // Añadir la compañía incluso si falta información
            } else {
                System.err.println("Tipo desconocido: " + tipo + " en línea: " + linea);
            }
        }
    } catch (IOException e) {
        System.err.println("Error al leer el archivo: " + e.getMessage());
    }
    return contactos;
    }

    @Override
    public String toString() {
        return "Tipo: " + tipo +
               "\nNombre: " + (name != null ? name : "N/A") +
               "\nTeléfono: " + (phoneNumber != null ? phoneNumber : "N/A") +
               "\nDirección: " + (address != null ? address.toString() : "N/A") +
               "\nCorreo: " + (email != null ? email : "N/A") +
               "\nPaís: " + (country != null ? country : "N/A");
    }
    
    @Override
    public int compareTo(Contact other) {
        if (this instanceof Person && other instanceof Person) {
            Person thisPerson = (Person) this;
            Person otherPerson = (Person) other;

            int lastNameComparison = compareStrings(thisPerson.getLastName(), otherPerson.getLastName());
            if (lastNameComparison != 0) {
                return lastNameComparison;
            }

            return compareStrings(this.getName(), other.getName());
        }

        return compareStrings(this.getName(), other.getName());
    }

    
    public int compareByAttributes(Contact other) {
        int thisAttributes = countNonNullAttributes();
        int otherAttributes = other.countNonNullAttributes();
        return Integer.compare(thisAttributes, otherAttributes);
    }

    public int compareByCountry(Contact other) {
        return compareStrings(this.getCountry(), other.getCountry());
    }

    private int countNonNullAttributes() {
        int count = 0;
        if (name != null) count++;
        if (phoneNumber != null) count++;
        if (address != null) count++;
        if (email != null) count++;
        if (country != null) count++;
        return count;
    }

    private int compareStrings(String s1, String s2) {
        if (s1 == null && s2 == null) return 0;
        if (s1 == null) return -1;
        if (s2 == null) return 1;
        return s1.compareToIgnoreCase(s2);
    }

    
}
