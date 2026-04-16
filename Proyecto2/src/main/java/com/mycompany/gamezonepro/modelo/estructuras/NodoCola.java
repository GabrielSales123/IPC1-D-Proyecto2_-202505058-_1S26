
package com.mycompany.gamezonepro.modelo.estructuras;

public class NodoCola<T> {
    public T dato; 
    public NodoCola<T> siguiente; 
    
    public NodoCola(T dato){
        this.dato = dato; 
        this.siguiente = null; 
    }
}
