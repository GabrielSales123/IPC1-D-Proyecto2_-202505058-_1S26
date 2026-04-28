
package com.mycompany.gamezonepro.modelo;

import com.mycompany.gamezonepro.modelo.estructuras.*;

public class Usuario {
    private String Id;
    private String pass;
    private int xp; 
    private int compras; 
    private double dineroGastado;
    private ListaSimple<Logro> logros;
    private ListaSimple<Carta> historialCompras;
    private Album album;
    
    public Usuario(String Id,String pass,  int xp){
        this.Id = Id; 
        this.pass = pass; 
        this.xp = xp;
        this.compras = 0;
        this.dineroGastado = 0;
        this.logros = new ListaSimple<>();
        this.historialCompras = new ListaSimple<>();
        this.album = new Album(6,4);
    }
    
    public String getId(){
        return Id;
    } 
    
    public String getPass(){
        return pass; 
    }
    
    public void setPass(String pass) {
        this.pass = pass;
    }
    
    public int getXp(){
        return xp; 
    }
    
    public void setXp(int xp){
        this.xp = xp; 
    }
    
    public void sumarXp(int cantidad){
        xp = xp +cantidad;
    }
    
     public Album getAlbum() {
        return album;
    }
     
    public void sumarCompra(){
        compras = compras + 1;
    }
    
    public int getCompras(){
        return compras; 
    }
    
    public void sumaGasto(double suma){
        dineroGastado = dineroGastado + suma;  
    }
    
    public double getGastos(){
        return dineroGastado;  
    }
    
    public ListaSimple<Logro> getLogros() {
        return logros;
    }
    
    public ListaSimple<Carta> getHistorialCompras() {
        return historialCompras;
    }
}
