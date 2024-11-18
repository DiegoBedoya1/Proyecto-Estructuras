/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectoestructuras;

import com.mycompany.proyectoestructuras.structures.MyArrayList;
import java.io.BufferedWriter;
import java.io.FileWriter;

/**
 *
 * @author maza-
 */
public class FileManager {
    
    public static void guardarContactos(String rutaArchivo, MyArrayList<Contact> contactos) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo))) {
            for (Contact contacto : contactos) {
                if (contacto instanceof Person) {
                    Person person = (Person) contacto;
                    writer.write("Person-" + person.getLastName() + "-" + person.getName() + "-" +
                        person.getPhoneNumber() + "-" + person.getAddress().getAddress() + "-" +
                        (person.getAddress().getUrlAddress() != null ? person.getAddress().getUrlAddress() : "") + "-" +
                        person.getEmail() + "-" + person.getCountry());
                    writer.newLine();
                } else if (contacto instanceof Company) {
                    Company company = (Company) contacto;
                    writer.write("COMPANIA-" + company.getName() + "-" + company.getPhoneNumber() + "-" +
                        company.getAddress().getAddress() + "-" +
                        (company.getAddress().getUrlAddress() != null ? company.getAddress().getUrlAddress() : "") + "-" +
                        company.getEmail() + "-" + company.getCountry());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Error al guardar los contactos en el archivo: " + e.getMessage());
        }
    }
    
}
