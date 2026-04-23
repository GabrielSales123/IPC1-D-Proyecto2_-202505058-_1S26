
package com.mycompany.gamezonepro.controlador;
import com.mycompany.gamezonepro.modelo.*;

public class ControlAutenticacion {
    private ControlUsuarios controlUsuarios;
    
     public ControlAutenticacion(ControlUsuarios controlUsuarios) {
        this.controlUsuarios = controlUsuarios;
    }

    public Usuario login(String id, String password) {
        return controlUsuarios.login(id, password);
    }   
    
    public ControlUsuarios getSistema(){
        return controlUsuarios;
    }
}
