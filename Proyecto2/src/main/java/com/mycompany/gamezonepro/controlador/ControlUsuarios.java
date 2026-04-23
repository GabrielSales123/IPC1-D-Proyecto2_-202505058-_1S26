
package com.mycompany.gamezonepro.controlador;

import com.mycompany.gamezonepro.modelo.Usuario;


public class ControlUsuarios {
    private Usuario[] usuarios; 
    private int totUsuarios; 
    
    public ControlUsuarios(int capacidad){
      this.usuarios = new Usuario[capacidad];
      this.totUsuarios = 0; 
  }
  
    public Usuario login(String id, String password){
        for (int i = 0; i < totUsuarios; i++){
            if (usuarios[i].getId().equals(id) &&
            usuarios[i].getPass().equals(password)){
            return usuarios[i];
            }
        }
        return null;
    }
  
  
    public int getTotalUsuarios() {
        return totUsuarios;
    }
  
  
    public void agregarUsuario(Usuario u){
        if (totUsuarios < usuarios.length){
            usuarios[totUsuarios] = u;
            totUsuarios++;
        }
    }
    
}
