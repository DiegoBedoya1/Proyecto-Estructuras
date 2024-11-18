package com.mycompany.proyectoestructuras;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import com.mycompany.proyectoestructuras.Address;
import com.mycompany.proyectoestructuras.Company;
import com.mycompany.proyectoestructuras.Contact;
import com.mycompany.proyectoestructuras.Person;
import com.mycompany.proyectoestructuras.structures.MyCircleDoubleLinkedList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
/**
 *
 * @author maza-
 */


public class ArchivoContactos {
    private MyCircleDoubleLinkedList<Contact> listaContactos;
    
    public ArchivoContactos(){
        this.listaContactos = new MyCircleDoubleLinkedList<>();
    }
    
    public void cargarContactos(String rutaArchivo){
        try(BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))){
            String linea;
            while((linea = br.readLine()) != null){
                String[] datos = linea.split("-");
                String tipo = datos[0];
                
                Address address;
                        
                if(tipo.equals("Person")){
                    String lastName = datos[1];
                    String name = datos[2];
                    String phoneNumber = datos[3];
                    
                    if(datos.length > 5 && !datos[5].isEmpty()){
                        address = new Address(datos[4], datos[5]);
                    }else{
                        address = new Address(datos[4]);
                    }
                    String email = datos[6];
                    String country = datos[7];
                    
                    Person person = new Person(lastName, name, phoneNumber, address, email, country);
                    listaContactos.add(person);
                    
                }else if(tipo.equals("COMPANIA")){
                    String name = datos[1];
                    String phoneNumber = datos[2];
                    
                    if(datos.length > 4 && !datos[4].isEmpty()){
                        address = new Address(datos[3], datos[4]);
                    }else{
                        address = new Address(datos[3]);
                    }
                    
                    String email = datos[5];
                    String country = datos[6];
                    
                     Company company = new Company(name, phoneNumber, address, email, country);
                }
            }
            
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public MyCircleDoubleLinkedList<Contact> getListaContactos() {
        return listaContactos;
    }
    
}
