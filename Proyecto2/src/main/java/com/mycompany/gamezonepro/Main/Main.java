
package com.mycompany.gamezonepro.Main;
import com.mycompany.gamezonepro.controlador.*;
import com.mycompany.gamezonepro.modelo.*;
import com.mycompany.gamezonepro.vista.*;

public class Main {

    public static void main(String[] args) {
        
        ControlUsuarios sisu = new ControlUsuarios(10);
        sisu.agregarUsuario(new Usuario("Wopa", "1", 10000));
        sisu.agregarUsuario(new Usuario("2", "2", 3000));
        sisu.agregarUsuario(new Usuario("3", "3", 999));
        java.awt.EventQueue.invokeLater(() -> {
        new Login(sisu).setVisible(true);
    });
    }
}
