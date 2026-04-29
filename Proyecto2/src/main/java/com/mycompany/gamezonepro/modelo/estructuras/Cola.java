
package com.mycompany.gamezonepro.modelo.estructuras;

public class Cola<T> {
     private NodoCola<T> frente;
    private NodoCola<T> fin;
    private int tamanio;

    public Cola() {
        frente = null;
        fin = null;
        tamanio = 0;
    }

    public synchronized void encolar(T dato) {
        NodoCola<T> nuevo = new NodoCola<>(dato);
        if (estaVacia()){
            frente = fin = nuevo;
        } else{
            fin.siguiente = nuevo;
            fin = nuevo;
        }
        tamanio++;
    }

    public synchronized T desencolar(){
        if (estaVacia()) return null;
        T dato = frente.dato;
        frente = frente.siguiente;
        if (frente == null) {
            fin = null;
        }
        tamanio--;
        return dato;
    }

    public T peek(){
        return (frente != null) ? frente.dato : null;
    }

    public boolean estaVacia(){
        return frente == null;
    }

    public int tamanio(){
        return tamanio;
    }
    
    public NodoCola<T> getFrenteNodo() {
        return frente;
    }
}
