
package com.mycompany.gamezonepro.modelo;


public class Logro {
    private String nombre;
    private String descripcion;
    private boolean desbloqueado;

    public Logro(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.desbloqueado = false;
    }

    public void desbloquear() {
        this.desbloqueado = true;
    }

    public boolean isDesbloqueado() {
        return desbloqueado;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
