
package com.mycompany.gamezonepro.modelo;

public class Usuario {
    private String Id;
    private String pass;
    private int xp; 
    
    public Usuario(String Id,String pass,  int xp){
        this.Id = Id; 
        this.pass = pass; 
        this.xp = xp;
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
}
