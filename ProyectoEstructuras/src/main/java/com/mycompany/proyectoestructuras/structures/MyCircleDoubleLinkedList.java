/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectoestructuras.structures;

import com.mycompany.proyectoestructuras.Company;
import com.mycompany.proyectoestructuras.Contact;
import com.mycompany.proyectoestructuras.Person;
import com.mycompany.proyectoestructuras.controller.InfoContactoController;
import java.util.Iterator;


/**
 *
 * @author aldaz
 * @param <T>
 */
public class MyCircleDoubleLinkedList<T> implements Iterable<T>{
    private CircularDoubleNode<T> head; // Referencia al primer nodo de la lista
    private int size;                  // Tamaño de la lista (número de nodos)

    // Constructor para inicializar la lista como vacía
    public MyCircleDoubleLinkedList() {
        this.head = null;
        this.size = 0;
    }
    
    // Método para añadir un elemento al final de la lista
    // Complejidad O(1) ya que se añade directamente al final sin recorrer la lista
    public void add(T dato) {
        CircularDoubleNode<T> nuevoNodo = new CircularDoubleNode<>(dato);
        if (head == null) {                 // Si la lista está vacía, el nuevo nodo es cabeza y se enlaza a sí mismo
            head = nuevoNodo;
            head.setNext(head);
            head.setPrevious(head);
        } else {
            CircularDoubleNode<T> cola = head.getPrevious(); // Obtener la cola desde la referencia de cabeza
            cola.setNext(nuevoNodo);       // El último nodo apunta al nuevo nodo
            nuevoNodo.setPrevious(cola);        // El nuevo nodo apunta al anterior (antigua cola)
            nuevoNodo.setNext(head);     // El nuevo nodo apunta a la cabeza
            head.setPrevious(nuevoNodo);      // La cabeza apunta de nuevo al nuevo nodo (nueva cola)
        }
        size++; // Incrementa el tamaño de la lista
    }
    
    // Método para añadir un elemento al inicio de la lista
    // Complejidad O(1) ya que solo cambia las referencias de cabeza y del nodo siguiente
    public void addFirst(T dato) {
        CircularDoubleNode<T> nuevoNodo = new CircularDoubleNode<>(dato);
        if (head == null) {                 // Si la lista está vacía, el nuevo nodo es cabeza y se enlaza a sí mismo
            head = nuevoNodo;
            head.setNext(head);
            head.setPrevious(head);
        } else {
            CircularDoubleNode<T> cola = head.getPrevious(); // Obtener la cola desde la referencia de cabeza
            nuevoNodo.setNext(head);     // El nuevo nodo apunta al antiguo primer nodo
            nuevoNodo.setPrevious(cola);        // El nuevo nodo apunta a la antigua cola
            cola.setNext(nuevoNodo);       // La cola apunta al nuevo nodo
            head.setPrevious(nuevoNodo);      // El antiguo primer nodo apunta al nuevo nodo
            head = nuevoNodo;               // El nuevo nodo se convierte en la cabeza
        }
        size++;
    }
    
    // Método para obtener el primer elemento de la lista
    // Complejidad O(1) ya que accede directamente al primer nodo
    public T getFirst() {
        if (head == null) {
            return null;
        }
        return head.getData();
    }
    

    public CircularDoubleNode<T> getFirstNode() {
        return head;  // Devuelve la referencia al nodo cabeza
    }
    
    public void setFirstNode(CircularDoubleNode<T> nodenuevo){
        this.head = nodenuevo;
    }
    
    public void decreaseSize() {
        if (size > 0) {
            size--;
        }
    }
    
    public T get(int index) {
        // Comprobamos que el índice sea válido
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Índice fuera de rango: " + index);
        }
        if (size == 0) {
            return null;
        }

        CircularDoubleNode<T> current = head;
        if (index == 0) {
            return current.data;
        }
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }


    // Método para obtener el último elemento de la lista
    // Complejidad O(1) ya que accede directamente al último nodo
    public CircularDoubleNode<T> getLastNode() {
        CircularDoubleNode<T> current = head;
        // Recorrer la lista hasta encontrar el último nodo
        while (current != null && current.next != head) {
            current = current.next;
        }
        return current;
    }

    

    public void remove(int index) {
        // Comprobamos que el índice sea válido
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Índice fuera de rango: " + index);
        }

        // Caso 1: Si la lista tiene solo un elemento
        if (size == 1) {
            head = null;  // Eliminamos el único nodo de la lista
        } else {
            // Caso 2: El índice es 0, eliminamos el primer nodo
            if (index == 0) {
                // Si la lista tiene más de un nodo
                CircularDoubleNode<T> lastNode = getLastNode();  // Conseguimos el último nodo
                head = head.next;  // La cabeza pasa a ser el siguiente nodo
                head.previous = lastNode;  // El nuevo primer nodo apunta al último
                lastNode.next = head;  // El último nodo apunta al nuevo primer nodo
            } else {
                // Caso 3: El índice no es 0, recorremos la lista
                CircularDoubleNode<T> current = head;

                // Recorremos hasta encontrar el nodo en el índice
                for (int i = 0; i < index; i++) {
                    current = current.next;
                }

                // Actualizamos los nodos adyacentes
                current.previous.next = current.next;  // El nodo anterior apunta al siguiente nodo
                current.next.previous = current.previous;  // El siguiente nodo apunta al anterior nodo

                // Caso 3.1: Si eliminamos el último nodo
                if (current.next == head) {
                    // El penúltimo nodo apunta a la cabeza
                    head.previous = current.previous;
                }
            }
        }

        // Reducimos el tamaño de la lista
        size--;
    }

    
    // Método para eliminar el primer elemento de la lista
    // Complejidad O(1) ya que solo cambia las referencias de cabeza, anterior y siguiente
    public T removeFirst() {
        if (head == null) {
            return null;
        }
        T datoEliminado = head.getData();
        if (head.getNext() == head) { // Si solo hay un elemento
            head = null;
        } else {
            CircularDoubleNode<T> cola = head.getPrevious();
            head = head.getNext();         // La cabeza ahora apunta al segundo nodo
            head.setPrevious(cola);            // La nueva cabeza apunta a la cola
            cola.setNext(head);           // La cola apunta de nuevo a la nueva cabeza
        }
        size--;
        return datoEliminado;
    }
    
    // Método para eliminar el último elemento de la lista
    // Complejidad O(1) ya que solo cambia la referencia de cola y del nodo anterior
    public T removeLast() {
        if (head == null) {
            return null;
        }
        T datoEliminado = head.getPrevious().getData();
        if (head.getNext() == head) { // Si solo hay un elemento
            head = null;
        } else {
            CircularDoubleNode<T> cola = head.getPrevious();
            cola.getPrevious().setNext(head);  // El penúltimo nodo apunta a la cabeza
            head.setPrevious(cola.getPrevious());   // La cabeza apunta al penúltimo nodo (nueva cola)
        }
        size--;
        return datoEliminado;
    }
    
    // Método para verificar si un elemento está en la lista
    // Complejidad O(n) ya que necesita recorrer la lista completa en el peor caso
    public boolean contains(T dato) {
        if (head == null) return false;

        CircularDoubleNode<T> actual = head;
        do {
            if (actual.getData().equals(dato)) {
                return true; // Dato encontrado
            }
            actual = actual.getNext();
        } while (actual != head);
        return false; // Dato no encontrado
    }

    // Método para obtener el tamaño actual de la lista
    // Complejidad O(1) ya que solo devuelve el valor del atributo tamaño
    public int size() {
        return size;
    }
    
    // Método para verificar si la lista está vacía
    // Complejidad O(1) ya que solo verifica si el tamaño es igual a 0
    public boolean isEmpty() {
        return size == 0;
    }

    // Método para imprimir la lista completa desde la cabeza hasta la cola
    // Complejidad O(n) ya que necesita recorrer toda la lista
    public void printList() {
        if (head == null) {
            System.out.println("La lista está vacía");
            return;
        }
        CircularDoubleNode<T> actual = head;
        do {
            System.out.print(actual.getData() + " <-> ");
            actual = actual.getNext();
        } while (actual != head);
        System.out.println("(cabeza)"); // Indicar el final de la lista y la referencia a la cabeza
    }
    
    // Método para imprimir la lista completa desde la cola hasta la cabeza (recorrido inverso)
    // Complejidad O(n) ya que necesita recorrer toda la lista
    public void printListReverse() {
        if (head == null) {
            System.out.println("La lista está vacía");
            return;
        }
        CircularDoubleNode<T> actual = head.getPrevious(); // Comienza desde la cola
        do {
            System.out.print(actual.getData() + " <-> ");
            actual = actual.getPrevious();
        } while (actual != head.getPrevious());
        System.out.println("(cabeza en reversa)"); // Indicar el final de la lista y referencia inversa
    }
    
    //iterador para la circular
    @Override
    public Iterator<T> iterator() {
        return new CircleListIterator();
    }

    // Clase interna que implementa Iterator para la lista circular
    private class CircleListIterator implements Iterator<T> {
        private CircularDoubleNode<T> current = head; // Nodo actual
        private boolean hasIteratedOnce = false; // Para evitar que el iterador recorra la lista nuevamente

        @Override
        public boolean hasNext() {
            // Si la lista no está vacía y no hemos recorrido todo el ciclo
            return current != null && (current != head || !hasIteratedOnce);
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new IllegalStateException("No hay más elementos en la lista");
            }
            T data = current.getData();
            current = current.getNext(); // Avanzamos al siguiente nodo
            hasIteratedOnce = true; // Marcamos que ya hemos iterado una vez
            return data;
        }
    }

    public CircularDoubleNode<T> getHead() {
        return head;
    }

    public void setHead(CircularDoubleNode<T> head) {
        this.head = head;
    }
    
    public void set(int index, T data) {
        if (head == null) {
            throw new IndexOutOfBoundsException("La lista está vacía.");
        }

        CircularDoubleNode<T> current = head;
        int count = 0;

        do {
            if (count == index) {
                current.data = data; // Reemplaza el dato
                return;
            }
            current = current.getNext();
            count++;
        } while (current != head);

        throw new IndexOutOfBoundsException("Índice fuera de rango: " + index);
    }

    
    
    public void update(Contact updatedContact) {
        if (head == null) {
            System.out.println("La lista está vacía, no se puede actualizar.");
            return;
        }

        CircularDoubleNode<T> current = head;

        do {
            if (current.getData().equals(updatedContact)) {
                current.data = (T) updatedContact; // Actualizamos el nodo
                System.out.println("Contacto actualizado.");
                return;
            }
            current = current.getNext();
        } while (current != head);

        System.out.println("Contacto no encontrado en la lista.");
    }


    
}
