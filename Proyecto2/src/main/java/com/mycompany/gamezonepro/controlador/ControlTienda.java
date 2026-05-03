
package com.mycompany.gamezonepro.controlador;

import com.mycompany.gamezonepro.modelo.Carta;
import com.mycompany.gamezonepro.modelo.estructuras.ListaSimple;
import com.mycompany.gamezonepro.modelo.estructuras.MallaOrtogonal;
import com.mycompany.gamezonepro.modelo.estructuras.NodoMatriz;

public class ControlTienda {
    private MallaOrtogonal mallaVista;
    private ListaSimple<Carta> carrito;
    private ListaSimple<Carta> inventario; 
    private int cont = 0; 
    
    public ControlTienda(int fila, int columna){
        mallaVista = new MallaOrtogonal(fila,columna);
        carrito = new ListaSimple<>();
    }
    
    public void stock(){
        mallaVista.agregarCarta(new Carta("60", "Charizard", "Fuego", "Legendaria", 150, 130, 200, "/imagenes/Charizard.png", 120.0, true,10));
        mallaVista.agregarCarta(new Carta("61", "Blastoise", "Agua", "Ultra Rara", 160, 180, 150, "/imagenes/Blastoise.png", 110.0, true,10));
        mallaVista.agregarCarta(new Carta("62", "Venusaur", "Planta", "Ultra Rara", 156, 130, 145, "/imagenes/Venusaur.png", 105.0, true,10));
        mallaVista.agregarCarta(new Carta("63", "Pikachu", "Electrico", "Rara", 150, 100, 120, "/imagenes/Pikachu.png", 60.0, true,10));
        mallaVista.agregarCarta(new Carta("64", "Gengar", "Fantasma", "Rara", 193, 150, 180, "/imagenes/Gengar.png", 90.0, true,10));
        mallaVista.agregarCarta(new Carta("152", "Chikorita", "Planta", "Comun", 45, 65, 45, "/imagenes/Chikorita.png", 60.0, true, 5));
        mallaVista.agregarCarta(new Carta("155", "Cyndaquil", "Fuego", "Comun", 52, 43, 39, "/imagenes/Cyndaquil.png", 70.0, true, 6));
        mallaVista.agregarCarta(new Carta("158", "Totodile", "Agua", "Comun", 65, 64, 43, "/imagenes/Totodile.png", 75.0, true, 7));
        mallaVista.agregarCarta(new Carta("172", "Pichu", "Electrico", "Poco Comun", 40, 15, 20, "/imagenes/Pichu.png", 40.0, true, 4));
        mallaVista.agregarCarta(new Carta("173", "Cleffa", "Normal", "Poco Comun", 30, 30, 40, "/imagenes/Cleffa.png", 35.0, true, 4));
        mallaVista.agregarCarta(new Carta("174", "Igglybuff", "Normal", "Poco Comun", 30, 15, 40, "/imagenes/Igglybuff.png", 35.0, true, 4));
        mallaVista.agregarCarta(new Carta("175", "Togepi", "Hada", "Rara", 35, 20, 65, "/imagenes/Togepi.png", 50.0, true, 6));
        mallaVista.agregarCarta(new Carta("179", "Mareep", "Electrico", "Comun", 55, 40, 55, "/imagenes/Mareep.png", 65.0, true, 6));
        mallaVista.agregarCarta(new Carta("183", "Marill", "Agua", "Poco Comun", 50, 80, 50, "/imagenes/Marill.png", 55.0, true, 5));
        mallaVista.agregarCarta(new Carta("185", "Sudowoodo", "Roca", "Rara", 70, 100, 70, "/imagenes/Sudowoodo.png", 80.0, true, 8));
        mallaVista.agregarCarta(new Carta("187", "Hoppip", "Planta", "Comun", 35, 35, 35, "/imagenes/Hoppip.png", 45.0, true, 4));
        mallaVista.agregarCarta(new Carta("198", "Murkrow", "Oscuro", "Rara", 85, 42, 60, "/imagenes/Murkrow.png", 90.0, true, 9));
        mallaVista.agregarCarta(new Carta("200", "Misdreavus", "Fantasma", "Rara", 60, 60, 85, "/imagenes/Misdreavus.png", 95.0, true, 10));
        mallaVista.agregarCarta(new Carta("209", "Snubbull", "Hada", "Poco Comun", 80, 50, 40, "/imagenes/Snubbull.png", 70.0, true, 6));
        mallaVista.agregarCarta(new Carta("215", "Sneasel", "Oscuro", "Rara", 95, 55, 55, "/imagenes/Sneasel.png", 100.0, true, 10));
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
    
    public void inventariar(){
        inventario = new ListaSimple<>(); 
        NodoMatriz fila = mallaVista.getNodo(0, 0);
            while (fila != null) {
            NodoMatriz actual = fila;
                while (actual != null) {
                if (actual.getDato() != null) {
                    inventario.agregar(actual.getDato());
                }
            actual = actual.derecha;
        }
        fila = fila.abajo;
        }
    }
    
    public ListaSimple getInventario(){
        return inventario; 
    }
}
