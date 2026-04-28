
package com.mycompany.gamezonepro.controlador;

import com.mycompany.gamezonepro.modelo.Carta;
import com.mycompany.gamezonepro.modelo.Logro;
import com.mycompany.gamezonepro.modelo.Usuario;
import com.mycompany.gamezonepro.modelo.estructuras.ListaSimple;
import javax.swing.JOptionPane;

public class ControlRecompensas {
    private ListaSimple<Logro> logros;
    private Usuario usuario;
    
    
    public ControlRecompensas(Usuario usuario){
        logros = new ListaSimple<>();
        this.usuario = usuario;
        this.logros = usuario.getLogros();
        if (logros.isEmpty()) {
        inicializarLogros(); 
        }
    }
    
    public void gestionLogros(int compras, int cartas, int xp, double dineroGastado, Carta carta){
        for (int i = 0; i < logros.tamanio(); i++) {
        Logro l = logros.buscar(i);

        if (!l.isDesbloqueado()) {

            switch (l.getNombre()) {

                case "Primera Compra":
                    if (compras >= 1) desbloquear(l);
                    break;

                case "Coleccionista Novato":
                    if (cartas >= 10) desbloquear(l);
                    break;

                case "Alta Rareza":
                    if (carta != null && carta.getRareza().equals("Legendaria")) {
                        desbloquear(l);
                    }
                    break;

                case "Gamer Dedicado":
                    if (xp >= 1000) desbloquear(l);
                    break;

                case "Veterano":
                    if (xp >= 1500) desbloquear(l);
                    break;

                case "Mestro en cartas":
                    if (xp >= 3500) desbloquear(l);
                    break;

                case "Leyenda Viviente":
                    if (xp >= 7000) desbloquear(l);
                    break;

                case "Gran Gastador":
                    if (dineroGastado >= 2000) desbloquear(l);
                    break;
                }
            }
        }
    }
   
    private void inicializarLogros() {
        logros.agregar(new Logro("Primera Compra", "Realiza tu primera compra"));
        logros.agregar(new Logro("Coleccionista Novato", "Añade 10 cartas"));
        logros.agregar(new Logro("Alta Rareza", "Obtén carta legendaria"));
        logros.agregar(new Logro("Gamer Dedicado", "Alcanza 1000 XP"));
        logros.agregar(new Logro("Leyenda Viviente", "Llega a nivel 5"));
        logros.agregar(new Logro("Mestro en cartas", "Llega a nivel 4"));
        logros.agregar(new Logro("Veterano", "Llega a nivel 3"));
        logros.agregar(new Logro("Gran Gastador", "Gasta Q2000"));
    }
    
    private void desbloquear(Logro l) {
        l.desbloquear();
        JOptionPane.showMessageDialog(
        null,
        "LOGRO DESBLOQUEADO\n\n" +
        "Nombre: " + l.getNombre() + "\n" +
        "Descripción: " + l.getDescripcion(),
        "Nuevo Logro",
        JOptionPane.INFORMATION_MESSAGE
        );
    }
    
}
