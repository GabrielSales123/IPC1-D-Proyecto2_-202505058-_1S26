
package com.mycompany.gamezonepro.controlador;

import com.mycompany.gamezonepro.modelo.*;


public class ControlAlbum {
    private Album album; 
    
    public void inicializar(){
        album = new Album(4,6);
        album.cargarArchivo();
        
    }
    
    public void agregarCarta(Carta carta){
        album.agregarCarta(carta);
    }
    
    public void guardarAlbum(){
        album.guardarArchivo();
    }
    
     public Album getAlbum() {
        return album;
    }
    
}
