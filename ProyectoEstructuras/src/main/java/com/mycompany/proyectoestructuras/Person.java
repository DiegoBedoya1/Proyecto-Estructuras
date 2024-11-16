/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectoestructuras;

/**
 *
 * @author diego
 */
public class Person extends Contact{
    private String lastName;

    public Person(String lastName, String name, String phoneNumber, Address address, String email,String country) {
        super(name, phoneNumber, address, email,country);
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
}
