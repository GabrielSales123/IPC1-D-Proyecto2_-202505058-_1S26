
package com.mycompany.gamezonepro.controlador;

import com.mycompany.gamezonepro.modelo.*;


public class ControlAlbum {
    private Album album; 
    
    
    
    public void agregarCarta(Carta carta){
        album.agregarCarta(carta);
    }
    
    
     public Album getAlbum() {
        return album;
    }
    
}
