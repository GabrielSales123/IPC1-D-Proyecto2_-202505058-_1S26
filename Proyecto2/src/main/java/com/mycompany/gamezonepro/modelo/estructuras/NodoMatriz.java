
package com.mycompany.gamezonepro.modelo.estructuras;
import com.mycompany.gamezonepro.modelo.*;


public class NodoMatriz {
    public Carta dato; 
    
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
    }
}
