
package com.mycompany.gamezonepro.modelo;
import com.mycompany.gamezonepro.modelo.estructuras.*;
import java.io.*;


public class Album {
    private MallaOrtogonal malla; 
    Carta Wopa = new Carta("1","Wopa","Agua","Legendaria",1000,1000,1000,"imagen1");
    Carta Poliwaj = new Carta("2","Poliwaj","Agua","Comun",10,5,50,"imagen2");
    
    public Album(int filas, int columnas){
        this.malla = new MallaOrtogonal(filas, columnas);
    }
    
    public void agregarCarta(Carta carta){
        malla.agregarCarta(carta);
    }
    
    private Carta buscarCartaPorCodigo(String codigo) {
    if (codigo.equals("1")){
        return Wopa;}
    if (codigo.equals("2")){
        return Poliwaj;}
    
    return null;
    }
    public void cargarArchivo(){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("album.txt"));
            NodoMatriz filaActual = malla.getNodo(0,0);
            int i = 0;
            while (filaActual != null) {
                NodoMatriz columnaActual = filaActual;
                int j = 0;
                while (columnaActual != null) {
                    String codigo = (columnaActual.dato == null)
                            ? "null"
                            : columnaActual.dato.getCodigo();
                    bw.write(i + "|" + j + "|" + codigo);
                    bw.newLine();
                    columnaActual = columnaActual.derecha;
                    j++;
                }
                filaActual = filaActual.abajo;
                i++;
            }
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void guardarArchivo(){
         try {
            File file = new File("album.txt");
            if (!file.exists()) return;
            BufferedReader br = new BufferedReader(new FileReader(file));
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split("\\|");
                int fila = Integer.parseInt(partes[0]);
                int columna = Integer.parseInt(partes[1]);
                String codigo = partes[2];
                if (!codigo.equals("null")) {
                    Carta carta = buscarCartaPorCodigo(codigo);
                    NodoMatriz nodo = malla.getNodo(fila, columna);
                    if (nodo != null) {
                        nodo.dato = carta;
                    }
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public Carta generarCartaAleatoria() {
        int random = (int)(Math.random() * 2);
        switch(random){
            case 0: 
                return Wopa;
            case 1:
                return Poliwaj;
            default:
                return null;
        }
    }
}
