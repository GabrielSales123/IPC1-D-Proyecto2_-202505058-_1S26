
package com.mycompany.gamezonepro.controlador;

import com.mycompany.gamezonepro.modelo.Carta;
import com.mycompany.gamezonepro.modelo.estructuras.ListaSimple;
import com.mycompany.gamezonepro.modelo.estructuras.MallaOrtogonal;

public class ControlTienda {
    private MallaOrtogonal mallaVista;
    private ListaSimple<Carta> carrito;
    private int cont = 0; 
    
    public ControlTienda(int fila, int columna){
        mallaVista = new MallaOrtogonal(fila,columna);
        carrito = new ListaSimple<>();
    }
    
    public void stock(){
        mallaVista.agregarCarta(new Carta("60", "Charizard", "Fuego", "Legendaria", 260, 200, 220, "/imagenes/charizard.png", 120.0, true));
        mallaVista.agregarCarta(new Carta("61", "Blastoise", "Agua", "Ultra Rara", 230, 210, 240, "/imagenes/blastoise.png", 110.0, true));
        mallaVista.agregarCarta(new Carta("62", "Venusaur", "Planta", "Ultra Rara", 220, 200, 230, "/imagenes/venusaur.png", 105.0, true));
        mallaVista.agregarCarta(new Carta("63", "Pikachu", "Electrico", "Rara", 150, 100, 120, "/imagenes/pikachu.png", 60.0, true));
        mallaVista.agregarCarta(new Carta("64", "Gengar", "Fantasma", "Rara", 210, 150, 180, "/imagenes/gengar.png", 90.0, true));

    }
    
    public MallaOrtogonal getTienda(){
        return mallaVista;
    }
    
    public void agregarCarrito(Carta c) {
        carrito.agregar(c);
        this.cont = cont+1; 
    }
    
    public void eliminarCarrito(int index) {
        carrito.eliminar(index);
    }
    
    public Carta buscarCarrito(int index) {
        return carrito.buscar(index);
    }
    
    public int getContador(){
        return cont; 
    }
    
    public void setContador(int cont){
        this.cont = cont;
    }
}
