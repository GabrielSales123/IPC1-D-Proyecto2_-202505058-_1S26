
package com.mycompany.gamezonepro.modelo;
import com.mycompany.gamezonepro.modelo.estructuras.*;
import java.io.*;


public class Album {
    private MallaOrtogonal malla; 
    private Carta Wopa = new Carta("1","Wopa","Agua","Legendaria",1000,1000,1000,"Webo.png");
    private Carta Poliwaj = new Carta("2","Poliwaj","Agua","Comun",10,5,50,"imagen2");
    
    public Album(int filas, int columnas){
        this.malla = new MallaOrtogonal(filas, columnas);
        agregarCarta(new Carta("1","Wopa","Agua","Legendaria",200,200,200,"/imagenes/Webo.png"));
        agregarCarta(new Carta("2","Poliwaj","Agua","Comun",150,100,50,"/imagenes/poli.png"));
        agregarCarta(new Carta("24","Arcanine","Fuego","Rara",190,130,120,"imagen"));
        agregarCarta(new Carta("25","Lapras","Agua","Rara",160,150,130,"imagen"));
        agregarCarta(new Carta("26","Sceptile","Planta","Rara",170,120,160,"imagen"));
        agregarCarta(new Carta("27","Electivire","Electrico","Rara",180,110,140,"imagen"));
        agregarCarta(new Carta("28","Alakazam","Psiquico","Rara",180,90,170,"imagen"));
        agregarCarta(new Carta("29","Snorlax","Normal","Rara",150,180,100,"imagen"));
        agregarCarta(new Carta("30","Umbreon","Oscuro","Rara",140,170,120,"imagen"));
        agregarCarta(new Carta("31","Metagross","Acero","Rara",190,180,90,"imagen"));
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
    
    public MallaOrtogonal getMalla(){
        return malla; 
    }
}
