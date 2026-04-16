
package com.mycompany.gamezonepro.modelo.estructuras;

public class ListaSimple<T>{
    private NodoSimple<T> cabeza;
    
    public ListaSimple(){
        cabeza = null; 
    }
    
    public void agregar(T dato){
        NodoSimple<T> nuevo  = new NodoSimple<>(dato);
        if(cabeza == null){
            cabeza = nuevo; 
        }else{
            NodoSimple<T> aux = cabeza; 
            while(aux.siguiente != null){
                aux = aux.siguiente;
            }
            aux.siguiente = nuevo;
        }
    }
    
    public void eliminar(int index) {
        if (cabeza == null){
            return;
        }
        
        if(index == 0){
            cabeza = cabeza.siguiente;
            return;
        }
        
        NodoSimple<T> aux = cabeza;
        for(int i = 0; i<index-1 && aux.siguiente != null; i++){
            aux = aux.siguiente;
        }
        if(aux.siguiente != null){
            aux.siguiente = aux.siguiente.siguiente;
        }
    }
    
    public T buscar(int index){
        NodoSimple<T> aux = cabeza; 
        int i = 0; 
        while(aux != null){
            if(i == index){ 
                return aux.dato;
            }
            aux = aux.siguiente;
            i++;
        }
        return null;
    }
    
    public int tamanio(){
        int cont = 0; 
        NodoSimple<T> aux = cabeza; 
        while(aux != null){
            cont++; 
            aux = aux.siguiente;
        }
        return cont; 
    }
    
    public boolean isEmpty(){
        return cabeza == null; 
    }
}
