
package com.mycompany.gamezonepro.modelo;

public class Carta {
    private String codigo; 
    private String nombre; 
    private String tipo; 
    private String rareza;
    private int atq; 
    private int def;
    private int ps;
    private String imagen; 
    
    public Carta(String codigo, String nombre, String tipo, String rareza, 
            int ataque, int defensa, int ps, String imagen){
        this.codigo = codigo; 
        this.nombre = nombre;
        this.tipo = tipo; 
        this.rareza = rareza; 
        this.atq = ataque;
        this.def = defensa; 
        this.ps = ps;
        this.imagen = imagen;
    }
    
    public String getCodigo(){
        return codigo; 
    }
    public String getNombre(){
        return nombre; 
    }
    public String getTipo(){
        return tipo; 
    }
    public String getRareza(){
        return rareza; 
    }
    public int getAtaque(){
        return atq; 
    }
    public int getDefensa(){
        return def; 
    }
    public int getPS(){
        return ps; 
    }
    public String getImagen(){
        return imagen; 
    }
}
