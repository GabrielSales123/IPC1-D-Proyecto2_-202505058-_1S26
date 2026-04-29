
package com.mycompany.gamezonepro.controlador;

import com.mycompany.gamezonepro.modelo.Torneo;
import com.mycompany.gamezonepro.modelo.estructuras.Cola;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class ControlTorneos {
    
    private Torneo[] torneos; 
    private int totalTorneos; 

    public ControlTorneos(Cola cola) {
        this.totalTorneos = 0;
        this.torneos = new Torneo[100];
    }
    
    public void agregarTorneo(Torneo t) {
        torneos[totalTorneos++] = t;
    }
    
    public Torneo[] getTorneos() {
        return torneos;
    }
    
    public int getTotalTorneos(){
        return totalTorneos; 
    }

    
    public void guardarTorneos() {
    try (BufferedWriter bw = new BufferedWriter(new FileWriter("torneos.txt"))) {

        for (int i = 0; i < totalTorneos; i++) {

            Torneo t = torneos[i];
            if (t == null) continue;

            bw.write(
                t.getId() + "|" +
                t.getNombre() + "|" +
                t.getJuego() + "|" +
                t.getFechaHora().toLocalDate().toString() + "|" +
                t.getFechaHora().toLocalTime().toString() + "|" +
                t.getPrecioTicket() + "|" +
                t.getTicketDisponibles()
            );

            bw.newLine();
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
}
    
    public void cargarTorneos() {

    try (BufferedReader br = new BufferedReader(new FileReader("torneos.txt"))) {

        String linea;

        while ((linea = br.readLine()) != null) {

            linea = linea.trim();
            if (linea.isEmpty()) continue;

            String[] partes = linea.split("\\|");

            String id = partes[0];
            String nombre = partes[1];
            String juego = partes[2];
            LocalDate fecha = LocalDate.parse(partes[3]);
            LocalTime hora = LocalTime.parse(partes[4]);
            LocalDateTime fechaHora = LocalDateTime.of(fecha, hora);
            double precio = Double.parseDouble(partes[5]);
            int tickets = Integer.parseInt(partes[6]);
            Torneo t = new Torneo(
                id,
                nombre,
                juego,
                fechaHora,
                precio,
                tickets
            );

            agregarTorneo(t);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}
    
    
}
