/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectoestructuras.structures;

import java.util.NoSuchElementException;

/**
 *
 * @author flsan
 */
public class IteratorCircular <T> {
    private CircularDoubleNode<T> actual; // Nodo actual en el iterador
    private final CircularDoubleNode<T> cabeza; // Nodo cabeza (referencia inicial)
    private boolean firstPass; // Indica si el iterador ya ha empezado el recorrido

    // Constructor
    public IteratorCircular(CircularDoubleNode<T> n) {
        this.cabeza = n;
        this.actual = n;
        this.firstPass = false;
    }


    public boolean hasNext() {
        return cabeza != null && (!firstPass || actual != cabeza);
    }


    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No more elements in the list.");
        }
        T data = actual.getData();
        actual = actual.getNext();
        firstPass = true;
        return data;
    }


    public void remove() {
        throw new UnsupportedOperationException("Remove operation is not supported.");
    }
}
