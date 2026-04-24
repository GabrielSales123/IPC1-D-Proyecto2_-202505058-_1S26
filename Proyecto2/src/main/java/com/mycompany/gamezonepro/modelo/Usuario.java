
package com.mycompany.gamezonepro.modelo;

import com.mycompany.gamezonepro.modelo.estructuras.MallaOrtogonal;

public class Usuario {
    private String Id;
    private String pass;
    private int xp; 
    private Album album;
    
    public Usuario(String Id,String pass,  int xp){
        this.Id = Id; 
        this.pass = pass; 
        this.xp = xp;
        this.album = new Album(6,4);
    }
    
    public String getId(){
        return Id;
    } 
    
    public String getPass(){
        return pass; 
    }
    
    public int getXp(){
        return xp; 
    }
    
    public void sumarXp(int cantidad){
        xp = xp +cantidad;
    }
    
     public Album getAlbum() {
        return album;
    }
}
