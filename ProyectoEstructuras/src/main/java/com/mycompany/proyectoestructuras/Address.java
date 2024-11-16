/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectoestructuras;

/**
 *
 * @author diego
 */
public class Address {
    private String address;
    private String urlAddress;

    public Address(String address) {
        this.address = address;
    }

    public Address(String address, String urlAddress) {
        this.address = address;
        this.urlAddress = urlAddress;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUrlAddress() {
        return urlAddress;
    }

    public void setUrlAddress(String urlAddress) {
        this.urlAddress = urlAddress;
    }
    
}
