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
    public String toFileString() {
        return String.format("compania,%s,%s,%s,%s,%s,%s,%s",
            getName() != null ? getName() : "",
            getPhoneNumber() != null ? getPhoneNumber() : "",
            RUC != null ? RUC : "",
            getAddress() != null ? getAddress().getAddress() : "",
            getEmail() != null ? getEmail() : "",
            getCountry() != null ? getCountry() : "",
            webPage != null ? webPage : ""
        );
    }

    public String getRUC() {
        return RUC;
    }

    public void setRUC(String RUC) {
        this.RUC = RUC;
    }

    public String getWebPage() {
        return webPage;
    }

    public void setWebPage(String webPage) {
        this.webPage = webPage;
    }

    
    
}
