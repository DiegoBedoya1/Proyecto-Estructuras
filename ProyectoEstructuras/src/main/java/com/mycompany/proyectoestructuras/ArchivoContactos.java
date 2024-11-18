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
    
    public MyCircleDoubleLinkedList<Contact> getListaContactos() {
        return listaContactos;
    }
    
}
