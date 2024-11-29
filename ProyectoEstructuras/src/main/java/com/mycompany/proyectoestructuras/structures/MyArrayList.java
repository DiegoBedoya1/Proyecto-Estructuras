/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectoestructuras.structures;

import com.mycompany.proyectoestructuras.Company;
import com.mycompany.proyectoestructuras.Contact;
import com.mycompany.proyectoestructuras.Person;
import com.mycompany.proyectoestructuras.controller.GeneralController;
import java.util.Comparator;
import java.lang.reflect.Array;

/**
 *
 * @author john
 * @param <T>
 */
public class MyArrayList<T extends Comparable<T>> implements Iterable<T> {
    private T[] array;
    private int size;

    // Constructor para inicializar la lista con un tamaño inicial
    public MyArrayList(Class<T> clazz) {
        array = (T[]) Array.newInstance(clazz, 10);
        size = 0;
    }

    public void add(T element) {
        if (size == array.length) {
            ensureCapacity();
        }
        if(element==null){
            System.out.println("Error al agregar");
            return;
    }
        array[size++] = element;
    }

    // Método para obtener un elemento en un índice específico
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Índice fuera de rango");
        }
        return array[index];
    }

    // Método para obtener el tamaño de la lista
    public int size() {
        return size;
    }

    private void ensureCapacity() {
        int newCapacity = array.length+(array.length/2);  
        T[] nuevo = (T[]) Array.newInstance(array.getClass().getComponentType(), newCapacity);
        System.arraycopy(array, 0, nuevo, 0, size); 
        array = nuevo;
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

    // Método para ordenar la lista usando un comparador
    public void sort(Comparator<? super T> comparator) {
        for (int i = 0; i < size - 1; i++) {
            for (int j = i + 1; j < size; j++) {
                T c1 = (T) array[i];
                T c2 = (T) array[j];

                // Usar el Comparator para comparar los elementos
                if (comparator.compare(c1, c2) > 0) {
                    // Intercambiar los elementos si el orden es incorrecto
                    T temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }
    }

    public void set(int index, T element) {
        checkIndex(index);  // Verificar que el índice es válido
        array[index] = element;  // Reemplazar el elemento
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Índice fuera de rango");
        }
    }
    
    public static void update(Person updatedPerson) {
        for (int i = 0; i < GeneralController.contactos.size(); i++) {
            Contact contact = GeneralController.contactos.get(i);
            if (contact.equals(updatedPerson)) {
                GeneralController.contactos.set(i, updatedPerson);
                break;
            }
        }
    }
    
    public static void update(Company updatedCompany) {
        for (int i = 0; i < GeneralController.contactos.size(); i++) {
            Contact contact = GeneralController.contactos.get(i);
            if (contact.equals(updatedCompany)) {
                GeneralController.contactos.set(i, updatedCompany);
                break;
            }
        }
    }
}
