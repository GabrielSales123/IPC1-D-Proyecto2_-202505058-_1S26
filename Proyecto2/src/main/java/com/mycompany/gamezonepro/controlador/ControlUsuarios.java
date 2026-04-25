
package com.mycompany.gamezonepro.controlador;

import com.mycompany.gamezonepro.modelo.Carta;
import com.mycompany.gamezonepro.modelo.Usuario;
import com.mycompany.gamezonepro.modelo.estructuras.NodoMatriz;
import java.io.*;


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
  
    public Usuario buscarUsuario(String id) {

    for (int i = 0; i < totUsuarios; i++) {

        if (usuarios[i] != null && usuarios[i].getId().equals(id)) {
            return usuarios[i];
        }
    }

    return null;
}
  
    public void agregarUsuario(Usuario u){
        if (totUsuarios < usuarios.length){
            usuarios[totUsuarios] = u;
            totUsuarios++;
        }
    }
    public Usuario[] getUsuarios(){
        return usuarios;
    }
    
    public Usuario[] getListaUsuarios() {
    Usuario[] lista = new Usuario[totUsuarios];
    for (int i = 0; i < totUsuarios; i++) {
        lista[i] = usuarios[i];
    }
    return lista;
}
    
    public void guardarTodos() {
    try (BufferedWriter bw = new BufferedWriter(new FileWriter("album.txt"))) {
    for (int i = 0; i < totUsuarios; i++) {
        Usuario u = usuarios[i];
        if (u == null) continue; 
        bw.write("/// " + u.getId() + "|" + u.getXp() + "|" + u.getPass());
        bw.newLine();
        NodoMatriz fila = u.getAlbum().getMalla().getNodo(0, 0);
        while (fila != null) {
            NodoMatriz actual = fila;
            while (actual != null) {
                if (actual.getDato() != null) {
                    Carta c = actual.getDato();
                    bw.write(
                        c.getCodigo() + "," +
                        c.getNombre() + "," +
                        c.getTipo() + "," +
                        c.getRareza() + "," +
                        c.getAtaque() + "," +
                        c.getDefensa() + "," +
                        c.getPS() + "," +
                        c.getImagen()
                        );
                        bw.newLine();
                    }
                    actual = actual.derecha;
                }
                fila = fila.abajo;
            }

            bw.newLine();
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void cargarUsuariosInicial() {
        try (BufferedReader br = new BufferedReader(new FileReader("album.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.startsWith("///")) {
                    String data = linea.substring(3).trim();
                    String[] partes = data.split("\\|");
                    String id = partes[0];
                    String pass = partes[2];
                    int xp = Integer.parseInt(partes[1]);
                    Usuario u = buscarUsuario(id);
                    if (u == null) {
                        u = new Usuario(id, pass, xp);
                        usuarios[totUsuarios++] = u;
                    } else {
                        u.setXp(xp);
                        u.setPass(pass); 
                    }  
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
