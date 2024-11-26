/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectoestructuras;

/**
 *
 * @author diego
 */
public class Company extends Contact {
    String RUC;
    String webPage;
    public Company(String tipo,String name, String phoneNumber, String RUC,Address address, String email, String country,String webPage) {
        super(tipo,name, phoneNumber, address, email, country);
        this.RUC=RUC;
        this.webPage=webPage;
    }
    
    @Override
    public String toString() {
        return super.toString() + 
               "\nRUC: " + (RUC != null ? RUC : "N/A") +
               "\nPágina web: " + (webPage != null ? webPage : "N/A");
    }
    
}
