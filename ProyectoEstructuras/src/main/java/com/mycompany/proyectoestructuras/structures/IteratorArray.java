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
public class IteratorArray <T> {
    private MyArrayList<T> lista; // Referencia a la lista
    private int indiceActual;   // Índice actual

    // Constructor que recibe una instancia de MyArrayList
    public IteratorArray(MyArrayList<T> list) {
        this.lista = list;
        this.indiceActual = 0;
    }

    public boolean hasNext() {
        return indiceActual < lista.size();
    }

    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No more elements in the list");
        }
        return lista.get(indiceActual++);
    }

    public void remove() {
        if (indiceActual <= 0 || indiceActual > lista.size()) {
            throw new IllegalStateException("Invalid state for remove operation");
        }
        // Eliminar el elemento anterior al índice actual
        lista.remove(--indiceActual);
    }
}
