
package com.mycompany.gamezonepro.modelo;
import com.mycompany.gamezonepro.controlador.ControlUsuarios;
import com.mycompany.gamezonepro.modelo.estructuras.*;
import java.io.*;


public class Album {
    private MallaOrtogonal malla; 
    private ControlUsuarios sisu;
    private Usuario usuarioActual; 
    private Carta Wopa = new Carta("1","Wopa","Agua","Legendaria",1000,1000,1000,"Webo.png");
    private Carta Poliwaj = new Carta("2","Poliwaj","Agua","Comun",10,5,50,"imagen2");
 
    public Album(int filas, int columnas){
        this.malla = new MallaOrtogonal(filas, columnas);
        this.sisu = sisu;
        this.usuarioActual = usuarioActual;
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
    public void cargarArchivo(String nombreUsuario) {
        try (BufferedReader br = new BufferedReader(new FileReader("album.txt"))) {
        String linea;
        boolean cargar = false;
        while ((linea = br.readLine()) != null) {
            if (linea.startsWith("///")) {
                String[] partes = linea.substring(3).trim().split("\\|");
                String id = partes[0];
                if (id.equals(nombreUsuario)) {
                    cargar = true;
                } else {
                cargar = false;
            }
                continue;
            }
            if (cargar && !linea.isEmpty()) {
            String[] datos = linea.split(",");
                Carta c = new Carta(
                    datos[0],
                    datos[1],
                    datos[2],
                    datos[3],
                    Integer.parseInt(datos[4]),
                    Integer.parseInt(datos[5]),
                    Integer.parseInt(datos[6]),
                    datos[7]
                );
                agregarCarta(c);
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
        }
    }
    
    
    public MallaOrtogonal getMalla(){
        return malla; 
    }
}
