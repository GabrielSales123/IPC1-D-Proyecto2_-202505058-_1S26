
package com.mycompany.gamezonepro.controlador;

import com.mycompany.gamezonepro.modelo.*;
import com.mycompany.gamezonepro.modelo.estructuras.*;
import com.mycompany.gamezonepro.vista.Torneos;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;


public class Taquilla implements Runnable{
    private String nombre; 
    private boolean activa = true; 
    private Torneo torneo; 
    private JTextField campo;
    private Cola<String> cola; 
    private Torneos vista;
    
    public Taquilla(String nombre, Cola<String> cola, Torneo torneo, JTextField campo, Torneos vista) {
        this.nombre = nombre;
        this.cola = cola;
        this.torneo = torneo;
        this.campo = campo;
        this.vista = vista;
    }
    
    @Override
    public void run(){
            while (activa) {
            String usuario = cola.desencolar();
            torneo.quitarTicket();
            vista.refrescarCola();
                if (usuario == null) {
                vista.actualizarTaquilla(campo, "");
                break;
                }
            vista.actualizarTaquilla(campo, usuario);
            vista.agregarLog("[" + nombre + "] atendió a " + usuario);

            try {
                Thread.sleep((long)(800 + Math.random() * 1200));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            vista.actualizarTaquilla(campo, "");
        }
    }   
    
    public void detener() {
        activa = false;
    }
}
