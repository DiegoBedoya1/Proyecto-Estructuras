/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectoestructuras.structures;

import com.mycompany.proyectoestructuras.Contact;
import java.util.Comparator;

/**
 *
 * @author john
 * @param <T>
 */
public class MyArrayList<T extends Comparable<T>> implements Iterable<T> {
    private Object[] array;
    private int size;

    // Constructor para inicializar la lista con un tamaño inicial
    public MyArrayList() {
        array = new Object[10];  // Tamaño inicial
        size = 0;
    }

    // Método para añadir un elemento al final de la lista
    public void add(T element) {
        if (size == array.length) {
            ensureCapacity();  // Asegurarse de que haya espacio suficiente
        }
        array[size++] = element;  // Añadir el elemento y aumentar el tamaño
    }

    // Método para obtener un elemento en un índice específico
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Índice fuera de rango");
        }
        return (T) array[index];
    }

    // Método para obtener el tamaño de la lista
    public int size() {
        return size;
    }

    // Método para asegurarse de que el array tenga suficiente capacidad
    private void ensureCapacity() {
        int newCapacity = array.length * 2;  // Duplicar el tamaño del array
        array = new Object[newCapacity];  // Crear un nuevo array más grande
        System.arraycopy(array, 0, array, 0, size);  // Copiar los elementos existentes
    }

    // Método para eliminar un elemento en un índice específico
    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Índice fuera de rango");
        }

        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];  // Mover los elementos a la izquierda
        }
        array[size - 1] = null;  // Eliminar la última posición
        size--;
    }

    // Método para comprobar si la lista está vacía
    public boolean isEmpty() {
        return size == 0;
    }

    // Método para limpiar la lista
    public void clear() {
        for (int i = 0; i < size; i++) {
            array[i] = null;  // Establecer todos los elementos a null
        }
        size = 0;
    }

    // Método para obtener el índice de un elemento
    public int indexOf(T element) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(element)) {
                return i;
            }
        }
        return -1;  // No encontrado
    }

    // Método para convertir la lista a un arreglo de objetos
    public Object[] toArray() {
        Object[] result = new Object[size];
        System.arraycopy(array, 0, result, 0, size);
        return result;
    }

    // Método para imprimir la lista
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(array[i].toString());
            if (i < size - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    // Implementación de Iterable, devolviendo un iterador personalizado
    @Override
    public MyIterator iterator() {
        return new MyIterator();
    }

    // Clase interna para el iterador personalizado
    private class MyIterator implements java.util.Iterator<T> {
        private int currentIndex = 0;

        // Método que verifica si hay más elementos en la lista
        @Override
        public boolean hasNext() {
            return currentIndex < size;
        }

        // Método que devuelve el siguiente elemento
        @Override
        public T next() {
            if (!hasNext()) {
                throw new IndexOutOfBoundsException("No hay más elementos en la lista");
            }
            return (T) array[currentIndex++];
        }

        // Método que no es necesario, pero se puede implementar
        @Override
        public void remove() {
            throw new UnsupportedOperationException("Eliminar no es soportado");
        }
    }
    
    public void sort(Comparator<? super T> comparator) {
        for (int i = 0; i < size - 1; i++) {
            for (int j = i + 1; j < size; j++) {
                T c1 = (T) array[i];
                T c2 = (T) array[j];

                // Usar el Comparator para comparar los elementos
                if (comparator.compare(c1, c2) > 0) {
                    // Intercambiar los elementos si el orden es incorrecto
                    Object temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }
    }

}