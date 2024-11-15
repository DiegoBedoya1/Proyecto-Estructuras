/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectoestructuras;

/**
 *
 * @author aldaz
 */
public class CircularDoubleNode<T> {
    T data;                        // Dato almacenado en el nodo
    CircularDoubleNode<T> next; // Referencia al siguiente nodo
    CircularDoubleNode<T> previous;  // Referencia al nodo anterior

    // Constructor para inicializar un nodo con un dato
    public CircularDoubleNode(T dato) {
        this.data = data;
        this.next = null;
        this.previous = null;
    }
}
