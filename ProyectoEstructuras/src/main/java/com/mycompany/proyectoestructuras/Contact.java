/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectoestructuras;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
      
    public static ArrayList<Contact> cargarContactos(String nameFile){
        ArrayList<Contact> c = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(App.pathFiles+nameFile))){
            String linea;
            while((linea = br.readLine()) != null){
                String[] datos = linea.split(",");
                String tipo = datos[0].toLowerCase();
                
                Address address=null;
                        
                if(tipo.equals("person")){
                    String name = datos[1];
                    String lastName = datos[2];
                    String phoneNumber = datos[3];
                    if(!datos[5].isEmpty()){
                        address = new Address(datos[4], datos[5]);
                    }else{
                        address = new Address(datos[4]);
                    }
                    String email = datos[6];
                    String country = datos[7];
                    
                    Contact person = new Person(tipo,name, lastName, phoneNumber, address, email, country);
                    c.add(person);
                    
                }else if(tipo.equals("compania")){
                    String name = datos[1];
                    String phoneNumber = datos[2];
                    String RUC=datos[3];
                    
                    if(!datos[5].isEmpty()){
                        address = new Address(datos[4], datos[5]);
                    }else{
                        address = new Address(datos[4]);
                    }
                    String email = datos[5];
                    String country = datos[6];
                    String webPage = datos[7];
                    
                     Company company = new Company(tipo,name, phoneNumber, RUC,address, email, country,webPage);
                     c.add(company);
                }
            }
            
        }catch(IOException e){
            e.printStackTrace();
        }
        return c;
    }
}
