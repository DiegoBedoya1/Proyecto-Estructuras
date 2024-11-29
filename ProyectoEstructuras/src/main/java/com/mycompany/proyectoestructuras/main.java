/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectoestructuras;

import com.mycompany.proyectoestructuras.controller.GeneralController;
import com.mycompany.proyectoestructuras.structures.MyArrayList;

/**
 *
 * @author diego
 */
public class main {
    public static void main(String[] args) {
        //MyArrayList<Contact> arr = Contact.cargarContactos("Contactos.txt");
        //Address a = new Address("Guangala");
        //Person p = new Person("person","Diego","Bedoya","09697970",a,"dbedoya@espol.edu.ec","ecuador");
        //GeneralController.contactos.add(p);
        for(Contact c:GeneralController.contactos){
            if(c==null){
                System.out.println("nulo");
            }
            else{
                System.out.println(c.toFileString());
            }
            
        }
    }
}
