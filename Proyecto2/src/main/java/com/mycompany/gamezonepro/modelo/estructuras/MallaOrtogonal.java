package com.mycompany.gamezonepro.modelo.estructuras;
import com.mycompany.gamezonepro.modelo.*;

public class MallaOrtogonal {

    private NodoMatriz inicio;
    private int filas;
    private int columnas;

    public MallaOrtogonal(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        construir();
    }

    private void construir() {
        NodoMatriz[][] temp = new NodoMatriz[filas][columnas];
        for (int i=0; i<filas; i++) {
            for (int j = 0; j < columnas; j++) {
                temp[i][j] = new NodoMatriz(null);
            }
        }
        for (int i=0; i<filas; i++) {
            for (int j=0; j<columnas; j++) {

                if (i > 0)
                    temp[i][j].arriba = temp[i-1][j];

                if (i<filas-1)
                    temp[i][j].abajo = temp[i+1][j];

                if (j>0)
                    temp[i][j].izquierda = temp[i][j-1];

                if (j<columnas-1)
                    temp[i][j].derecha = temp[i][j+1];
            }
        }
        inicio = temp[0][0];
    }

    public void agregarCarta(Carta carta) {
        NodoMatriz filaActual = inicio;
        while (filaActual != null) {
            NodoMatriz columnaActual = filaActual;
            while (columnaActual != null) {
                if (columnaActual.dato == null) {
                    columnaActual.dato = carta;
                    return;
                }
                columnaActual = columnaActual.derecha;
            }
            filaActual = filaActual.abajo;
        }
        System.out.println("Álbum lleno");
    }

    public void imprimir() {
        NodoMatriz filaActual = inicio;
        while (filaActual != null) {
            NodoMatriz columnaActual = filaActual;
            while (columnaActual != null) {
                if (columnaActual.dato == null)
                    System.out.print("[Vacía] ");
                else
                    System.out.print("[" + columnaActual.dato.getNombre() + "] ");

                columnaActual = columnaActual.derecha;
            }

            System.out.println();
            filaActual = filaActual.abajo;
        }
    }

    public NodoMatriz getNodo(int fila, int columna) {
        NodoMatriz actual = inicio;
        for (int i = 0; i < fila; i++) {
            if (actual != null)
                actual = actual.abajo;
        }
        for (int j = 0; j < columna; j++) {
            if (actual != null)
                actual = actual.derecha;
        }
        return actual;
    }
    
    public void intercambiar(NodoMatriz a, NodoMatriz b) {
        Carta temp = a.getDato();
        a.setDato(b.getDato());
        b.setDato(temp);
    }
    
    public int contarCartas(NodoMatriz inicio) {
    int contador = 0;
    NodoMatriz fila = inicio;
    while (fila != null) {
        NodoMatriz actual = fila;
        while (actual != null) {
            if (actual.getDato() != null) {
                contador++;
            }
            actual = actual.getDerecha();
        }
        fila = fila.getAbajo();
        }
     return contador;
    }
}

