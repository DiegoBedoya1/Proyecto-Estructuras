/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectoestructuras;

import com.mycompany.proyectoestructuras.Contact;
import com.mycompany.proyectoestructuras.structures.CircularDoubleNode;
import com.mycompany.proyectoestructuras.structures.IteratorCircular;
<<<<<<< HEAD
import com.mycompany.proyectoestructuras.structures.IteratorCircular;
import com.mycompany.proyectoestructuras.structures.MyArrayList;
import com.mycompany.proyectoestructuras.structures.MyCircleDoubleLinkedList;
=======
>>>>>>> 2a3124ea104ab05b047bf804a6586a97eda53fc7
import com.mycompany.proyectoestructuras.structures.MyCircleDoubleLinkedList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

/**
 *
 * @author maza-
 */
public class ListaContactos {
    private MyCircleDoubleLinkedList<Contact> contactos;
    
    public ListaContactos(){
        this.contactos = new MyCircleDoubleLinkedList<>();
    }
    
    public void agregarContacto(Contact contacto){
        contactos.add(contacto);
    }
    
    public boolean eliminarContacto(Contact contacto){
        if(contactos.isEmpty()){
            return false;
        }
        /*if(contactos.contains(contacto)){
            eliminarNodo(contacto);
            return true;
        }
        return false;*/
        if(contactos.contains(contacto)){
<<<<<<< HEAD
             CircularDoubleNode<Contact> actual = contactos.getFirstNode();
            do{
                if(actual.getData().equals(contacto)){
                    CircularDoubleNode<Contact> anterior = actual.getPrevious();
                    CircularDoubleNode<Contact> siguiente = actual.getNext();
                    anterior.setNext(siguiente); 
                    siguiente.setPrevious(anterior);
                    
                    if (actual == contactos.getFirstNode()){
                        contactos.setFirstNode(siguiente);
                    }
                    
                    contactos.decreaseSize() ;
                    return true;
                }
                actual = actual.getNext();
                
            }while(actual!=contactos.getFirstNode());
=======
             
            int size = contactos.size();
            for(int j = 0 ;j<size-1;j++){
                return true;
            }
>>>>>>> 2a3124ea104ab05b047bf804a6586a97eda53fc7
        }
        return false;
    }
    
   /* private void eliminarNodo(Contact contacto){
        /*Contact primero = contactos.getFirst();
        CircularDoubleNode<Contact> actual = null;
        actual.setData(primero);*/
        /*CircularDoubleNode<Contact> actual =   // Inicia en el primer nodo
        do{
            if(actual.getData().equals(contacto)){
                CircularDoubleNode<Contact> previo = actual.getPrevious();
                CircularDoubleNode<Contact> siguiente = actual.getNext();
                
                previo.setNext(siguiente);
                siguiente.setPrevious(previo);
                
                if(actual == contactos.ge)
            }
        }
    }*/
    
    
    
    
    public boolean contieneContacto(Contact contacto){
        return contactos.contains(contacto);
    }
    
<<<<<<< HEAD
    public IteratorCircular<Contact> getIterator() {
        CircularDoubleNode<Contact> actual = contactos.getFirstNode();
        IteratorCircular<Contact> devuelto = new IteratorCircular<>(actual);
        return  devuelto;
    }
    
    
    
    public MyArrayList<Contact> filtrarContactos(Predicate<Contact> criterio) {
        // Método para filtrar contactos basado en un criterio específico
        MyArrayList<Contact> resultado = new MyArrayList<>();
    }
=======
    public Iterator<CircularDoubleNode> getIterator() {
        return (Iterator<CircularDoubleNode>) new IteratorCircular<>(contactos.getFirstNode());      
    }
    
    /*public List<Contact> filtrarContactos(Predicate<Contact> criterio) {
        // Método para filtrar contactos basado en un criterio específico
        return contactos.stream().filter(criterio).toList();
    }**/
>>>>>>> 2a3124ea104ab05b047bf804a6586a97eda53fc7
    
    public void ordenarContactos(Comparator<Contact> comparador) {
        // Puedes implementar el ordenamiento basado en el criterio
        // Requiere convertir la lista en un formato que soporte ordenar
    }
}

