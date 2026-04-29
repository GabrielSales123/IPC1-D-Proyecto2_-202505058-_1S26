
package com.mycompany.gamezonepro.modelo;

import com.mycompany.gamezonepro.modelo.estructuras.Cola;
import java.time.*;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Torneo {
    private String Id;
    private String nombre; 
    private String juego; 
    private LocalDateTime fechaHora;
    private double precioTicket;
    private int ticketDisponibles;
    private Cola<String> cola;
    
  
    
    public Torneo(String Id, String nombre, String juego, LocalDateTime fechaHora,
            double precioTicket, int ticketDisponibles){
        this.Id = Id; 
        this.nombre = nombre; 
        this.juego = juego; 
        this.fechaHora = fechaHora; 
        this.precioTicket = precioTicket; 
        this.ticketDisponibles = ticketDisponibles; 
        this.cola = new Cola<>();
    }
    
    public String getId(){
        return Id; 
    }
    
    public String getNombre(){
        return nombre; 
    }
    
    public String getJuego(){
        return juego; 
    }
    
    public LocalDateTime getFechaHora(){
        return fechaHora; 
    }
    
    public LocalDate getFecha(){
        return fechaHora.toLocalDate();
    }
    
    public LocalTime getHora(){
        return fechaHora.toLocalTime();
    }
    
    public double getPrecioTicket(){
        return precioTicket; 
    }
    
    public int getTicketDisponibles(){
        return ticketDisponibles; 
    }
    
    public void quitarTicket(){
        ticketDisponibles = ticketDisponibles -1;
    }
            
    
    public Cola getCola(){
        return cola; 
    }
    
     public synchronized void registrarVenta(String taquilla, String usuario) {
        if (ticketDisponibles > 0) {
            System.out.println("[" + taquilla + "] vendio ticket a " + usuario);
            ticketDisponibles--;
            System.out.println("Tickets restantes: " + ticketDisponibles);

        } else {
            System.out.println("[" + taquilla + "] no pudo vender a" + usuario + "(agotado)");
        }
    }
     
     private void actualizarTaquilla(JTextField campo, String texto) {
        SwingUtilities.invokeLater(() -> {
            campo.setText(texto);
        });
    }
     
    
     
}
