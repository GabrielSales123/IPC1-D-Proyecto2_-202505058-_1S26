
package com.mycompany.gamezonepro.modelo.estructuras;
import com.mycompany.gamezonepro.modelo.*;


public class NodoMatriz {
    public Carta dato; 
    
    public boolean resaltada;
    public NodoMatriz arriba; 
    public NodoMatriz abajo; 
    public NodoMatriz izquierda; 
    public NodoMatriz derecha; 
    
    public NodoMatriz(Carta dato){
        this.dato = dato; 
        this.arriba = null; 
        this.abajo = null;
        this.izquierda = null; 
        this.derecha = null;
        this.resaltada = false;
    }
    
    public Carta getDato(){
        return dato; 
    }
    
    public void setDato(Carta dato){
        this.dato = dato; 
    }
    
    public void setResaltada(boolean resaltada){
        this.resaltada = resaltada;
    }
    
    public boolean isResaltada() {
        return resaltada;
    }
}
