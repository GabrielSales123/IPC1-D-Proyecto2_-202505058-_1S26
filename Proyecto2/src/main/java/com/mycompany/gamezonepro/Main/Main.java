
package com.mycompany.gamezonepro.Main;
import com.mycompany.gamezonepro.controlador.*;
import com.mycompany.gamezonepro.modelo.*;
import com.mycompany.gamezonepro.modelo.estructuras.MallaOrtogonal;
import com.mycompany.gamezonepro.vista.*;

public class Main {

    public static void main(String[] args) {
        ControlUsuarios sisu = new ControlUsuarios(10);
        sisu.cargarUsuariosInicial();
        for (int i=0; i<sisu.getTotalUsuarios(); i++) {
        Usuario u = sisu.getUsuarios()[i];
            u.getAlbum().cargarArchivo(u.getId());
        }
       
         Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            sisu.guardarTodos();
            
        }));
        java.awt.EventQueue.invokeLater(() -> {
        new Login(sisu).setVisible(true);
    });
        
    }
}
