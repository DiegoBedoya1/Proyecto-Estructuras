/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectoestructuras.structures;

/**
 *
 * @author diego
 */
public class MyArrayList<T> {
    private T[] array;
    private int size;
    
    public MyArrayList(){
        this.array = (T[]) new Object[1];
        this.size = 0;
    }
    
    public void add(T element){
        if(size == array.length)
            addCapacity();
        array[size] = element;
        size++;
    }
    public void add(int index,T element){
        if(index<0 || index>size){
            throw new IndexOutOfBoundsException("Index out of range");
        }
        else if(size==array.length){
            addCapacity();
        }
        for(int i = size;size>index;i--){
            array[i] = array[i-1];
        }
        array[index] = element;
        size++;
    }
    
    private void addCapacity(){
        int newCapacity = array.length+(array.length/2); 
        T[] newArray = (T[]) new Object[newCapacity];
        for(int i = 0;i<newArray.length;i++){
            newArray[i] = array[i];
        }
        array = newArray;
    }
    public boolean isEmpty(){
        return size==0;
    }
    public int size(){
        return size;
    }
    public T get(int index){
        if(index<0 || index>size)
           throw new IndexOutOfBoundsException("Index out of range");
        
        return array[index];
    }
    public void set(int index,T element){
        if(index<0 || index>size)
          throw new IndexOutOfBoundsException("Index out of range");
        array[index] = element;
    }
    
    public T remove(int index){
        if(index<0 || index>size)
            throw new IndexOutOfBoundsException("Index out of range");
        
        T element = array[index];
        for(int i = index;i<size;i++){
            array[i] = array[i+1];
        }
        array[size-1] = null;
        size--;
        return element;
    }
    
    public boolean contains(T element){
        for(int i = 0;i<size;i++){
            if(array[i].equals(element))
                return true;
        }
        return false;
    }
}
