
package com.mycompany.gamezonepro.vista;

import com.mycompany.gamezonepro.controlador.ControlUsuarios;
import com.mycompany.gamezonepro.modelo.Carta;
import com.mycompany.gamezonepro.modelo.Usuario;
import com.mycompany.gamezonepro.modelo.estructuras.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;


public class Album extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Album.class.getName());
    private ControlUsuarios sisu;
    private Usuario usuarioActual; 
    private NodoMatriz seleccion1 = null; 
    private NodoMatriz seleccion2 = null; 
    private boolean intercambio = false;
    /**
     * Creates new form Album
     */
    public Album(ControlUsuarios sisu, Usuario usuarioActual) {
        this.usuarioActual = usuarioActual;
        this.sisu = sisu;
        initComponents();
        inicializarObjetos();
        inicializarAlbum();
        renderizarAlbum();
    }
    
    public void inicializarAlbum() {
    panelAlbum.removeAll();
    panelAlbum.setPreferredSize(new Dimension(600, 400));
    panelAlbum.setLayout(new GridLayout(4, 6, 10, 10));
    for (int i = 0; i < 24; i++) {
        JPanel celda = new JPanel();
        celda.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(Color.BLACK),
        BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        JLabel texto = new JLabel("Vacía");
        celda.add(texto);
        panelAlbum.add(celda);
    }
    panelAlbum.revalidate();
    panelAlbum.repaint();
}

    public void renderizarAlbum() {
        panelAlbum.removeAll();
        panelAlbum.setLayout(new GridLayout(4, 6, 6, 6));
        NodoMatriz fila = usuarioActual.getAlbum().getMalla().getNodo(0, 0);
        while (fila != null) {
            NodoMatriz actual = fila;
            while (actual != null) {
                JPanel celda = new JPanel();
                celda.setLayout(new BorderLayout());
                if (actual.isResaltada()) {
                    celda.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 3));
                } else {
                    celda.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                }
                if (actual.dato == null) {
                    celda.setBackground(Color.LIGHT_GRAY);
                    celda.setOpaque(true);
                    JLabel texto = new JLabel("Vacía", SwingConstants.CENTER);
                    celda.add(texto, BorderLayout.CENTER);
                } else {
                    Carta carta = actual.dato;
                    celda.setBackground(Color.WHITE);
                    celda.setOpaque(true);
                    try {
                        ImageIcon icono = new ImageIcon(getClass().getResource(carta.getImagen()));
                        Image img = icono.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
                        JLabel lblImagen = new JLabel(new ImageIcon(img));
                        lblImagen.setHorizontalAlignment(SwingConstants.CENTER);
                        JLabel nombre = new JLabel(carta.getNombre(), SwingConstants.CENTER);
                        celda.add(lblImagen, BorderLayout.CENTER);
                        celda.add(nombre, BorderLayout.SOUTH);
                    } catch (Exception e) {
                        JLabel error = new JLabel("Sin img", SwingConstants.CENTER);
                        celda.add(error, BorderLayout.CENTER);
                    }
                    final NodoMatriz nodoActual = actual;
                    celda.addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent e) {
                    mostrarCarta(carta);
                    manejoClick(nodoActual);
                    }
                    });
                }
                panelAlbum.add(celda);
                actual = actual.derecha;
            }
            fila = fila.abajo;
        }
        panelAlbum.revalidate();
        panelAlbum.repaint();
    }
    
    public void mostrarCarta(Carta carta){
        nomcartaTxt.setText(carta.getNombre());
        CodigoTxt.setText("Código: "+carta.getCodigo());
        TipoTxt.setText("Tipo: "+ carta.getTipo());
        RarezaTxt.setText("Rareza: "+carta.getRareza());
        atkBar.setValue(carta.getAtaque());
        atkValue.setText(String.valueOf(carta.getAtaque()));
        defBar.setValue(carta.getDefensa());
        defValue.setText(String.valueOf(carta.getDefensa()));
        psBar.setValue(carta.getPS());
        psValue.setText(String.valueOf(carta.getPS()));
        try {
        ImageIcon icono = new ImageIcon(getClass().getResource(carta.getImagen()));
        Image img = icono.getImage().getScaledInstance(130, 130, Image.SCALE_SMOOTH);
        imgCarta.setIcon(new ImageIcon(img));
         } catch (Exception e) {
                        imgCarta.setText("Sin imagen");
                        imgCarta.setIcon(null);
                    }
    }
    
    public void inicializarObjetos(){
       atkBar.setMaximum(200);
       defBar.setMaximum(200);
       psBar.setMaximum(200);
       tipoBox.setModel(new DefaultComboBoxModel<>(new String[]{
        "Todos", "Fuego", "Agua", "Planta", "Electrico", "Psiquico", "Normal", "Oscuro", "Acero"
        }));

        rarezaBox.setModel(new DefaultComboBoxModel<>(new String[]{
        "Todas", "Comun", "Poco Comun", "Rara", "Ultra Rara", "Legendaria"
        }));
    
    }
    
    public void buscarCartas(String texto, String tipo, String rareza) {
        NodoMatriz fila = usuarioActual.getAlbum().getMalla().getNodo(0, 0);
        while (fila != null) {
            NodoMatriz actual = fila;
            while (actual != null) {
                if (actual.dato != null) {
                    Carta carta = actual.dato;
                    boolean coincide = true;
                        if (!texto.isEmpty() && !carta.getNombre().toLowerCase().contains(texto.toLowerCase())) {
                            coincide = false;
                        }
                        if (!tipo.equals("Todos") && !carta.getTipo().equals(tipo)) {
                            coincide = false;
                        }
                        if (!rareza.equals("Todas") && !carta.getRareza().equals(rareza)) {
                            coincide = false;
                        }
                    actual.setResaltada(coincide); 
                }else {
                actual.setResaltada(false);
            }

                actual = actual.derecha;
            }
            fila = fila.abajo;
        }
        renderizarAlbum();
    }
    
    private void manejoClick(NodoMatriz nodo){
        if(nodo.getDato() == null){
            return;
        }
        if(intercambio == false){
            seleccion1 = nodo; 
            mostrarCarta(nodo.getDato());
            nodo.setResaltada(true);
            renderizarAlbum();
            
        }
        else{
            seleccion2 = nodo; 
            if(seleccion1 == seleccion2){
                return;   
            }
            usuarioActual.getAlbum().getMalla().intercambiar(seleccion1, seleccion2);
            seleccion1.setResaltada(false);
            seleccion2.setResaltada(false);
            seleccion1 = null;
            seleccion2 = null;
            intercambio = false;
            renderizarAlbum();
            
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        ReturnBtn = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        busquedaTxt = new javax.swing.JTextField();
        tipoBox = new javax.swing.JComboBox<>();
        rarezaBox = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        BuscarBtn = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        AtributosTxt = new javax.swing.JLabel();
        CodigoTxt = new javax.swing.JLabel();
        TipoTxt = new javax.swing.JLabel();
        RarezaTxt = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        interTxt = new javax.swing.JButton();
        atkBar = new javax.swing.JProgressBar();
        defBar = new javax.swing.JProgressBar();
        psBar = new javax.swing.JProgressBar();
        atkValue = new javax.swing.JLabel();
        defValue = new javax.swing.JLabel();
        psValue = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        imgCarta = new javax.swing.JLabel();
        nomcartaTxt = new javax.swing.JLabel();
        scrollAlbum = new javax.swing.JScrollPane();
        panelAlbum = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        ReturnBtn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        ReturnBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ReturnBtnMouseClicked(evt);
            }
        });

        jLabel2.setText("<-");

        javax.swing.GroupLayout ReturnBtnLayout = new javax.swing.GroupLayout(ReturnBtn);
        ReturnBtn.setLayout(ReturnBtnLayout);
        ReturnBtnLayout.setHorizontalGroup(
            ReturnBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ReturnBtnLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        ReturnBtnLayout.setVerticalGroup(
            ReturnBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ReturnBtnLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setText("Album");

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        busquedaTxt.addActionListener(this::busquedaTxtActionPerformed);

        tipoBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        rarezaBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel3.setText("Buscar:");

        BuscarBtn.setText("Buscar");
        BuscarBtn.addActionListener(this::BuscarBtnActionPerformed);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(busquedaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(tipoBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rarezaBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(BuscarBtn)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(busquedaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tipoBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rarezaBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(BuscarBtn))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ReturnBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(266, 266, 266)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(ReturnBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        AtributosTxt.setText("Atributos");

        CodigoTxt.setText("Codigo");

        TipoTxt.setText("Tipo");

        RarezaTxt.setText("Rareza");

        jLabel7.setText("ATK");

        jLabel8.setText("DEF");

        jLabel9.setText("PS");

        jLabel10.setText("Posicion");

        interTxt.setText("Intercambiar");
        interTxt.addActionListener(this::interTxtActionPerformed);

        atkBar.setMaximum(200);
        atkBar.setMaximumSize(new java.awt.Dimension(200, 16));
        atkBar.setMinimumSize(new java.awt.Dimension(200, 16));
        atkBar.setPreferredSize(new java.awt.Dimension(200, 16));

        defBar.setMaximum(200);
        defBar.setPreferredSize(new java.awt.Dimension(200, 16));

        psBar.setMaximum(200);
        psBar.setPreferredSize(new java.awt.Dimension(200, 16));

        atkValue.setText("0");

        defValue.setText("0");

        psValue.setText("0");

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel4.setPreferredSize(new java.awt.Dimension(130, 130));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(imgCarta, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(imgCarta, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
        );

        nomcartaTxt.setText("Nombre");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(RarezaTxt)
                    .addComponent(interTxt)
                    .addComponent(jLabel10)
                    .addComponent(TipoTxt)
                    .addComponent(CodigoTxt)
                    .addComponent(AtributosTxt)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(psBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(defBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(atkBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(defValue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(atkValue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(psValue, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(80, 80, 80))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(116, 116, 116)
                .addComponent(nomcartaTxt)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nomcartaTxt)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(AtributosTxt)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CodigoTxt)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TipoTxt)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(RarezaTxt)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7)
                            .addComponent(atkBar, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8)
                            .addComponent(defBar, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel9)
                            .addComponent(psBar, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(atkValue)
                                .addGap(22, 22, 22))
                            .addComponent(defValue))
                        .addGap(22, 22, 22))
                    .addComponent(psValue))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addGap(18, 18, 18)
                .addComponent(interTxt)
                .addGap(20, 20, 20))
        );

        panelAlbum.setLayout(new GridLayout(4, 6));
        panelAlbum.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panelAlbum.setPreferredSize(new java.awt.Dimension(600, 400));

        javax.swing.GroupLayout panelAlbumLayout = new javax.swing.GroupLayout(panelAlbum);
        panelAlbum.setLayout(panelAlbumLayout);
        panelAlbumLayout.setHorizontalGroup(
            panelAlbumLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 598, Short.MAX_VALUE)
        );
        panelAlbumLayout.setVerticalGroup(
            panelAlbumLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 398, Short.MAX_VALUE)
        );

        scrollAlbum.setViewportView(panelAlbum);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(scrollAlbum, javax.swing.GroupLayout.PREFERRED_SIZE, 599, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(scrollAlbum))
                .addContainerGap(46, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ReturnBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ReturnBtnMouseClicked
        java.awt.EventQueue.invokeLater(() -> {
        this.dispose();
        new MenuPrincipal(sisu, usuarioActual).setVisible(true);
    });
    }//GEN-LAST:event_ReturnBtnMouseClicked

    private void interTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_interTxtActionPerformed
        if(seleccion1 != null){
            intercambio = true;
            JOptionPane.showMessageDialog(this, "Seleccione la segunda carta");
        } else {
            JOptionPane.showMessageDialog(this, "NO se ha seleccionado carta");
        }
    }//GEN-LAST:event_interTxtActionPerformed

    private void busquedaTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_busquedaTxtActionPerformed
        
    }//GEN-LAST:event_busquedaTxtActionPerformed

    private void BuscarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuscarBtnActionPerformed
        String texto = busquedaTxt.getText();
        String tipo = tipoBox.getSelectedItem().toString();
        String rareza = rarezaBox.getSelectedItem().toString();
        buscarCartas(texto, tipo, rareza);
    }//GEN-LAST:event_BuscarBtnActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
  
    }

    public void cargarDesdeArchivo(String albumtxt) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel AtributosTxt;
    private javax.swing.JButton BuscarBtn;
    private javax.swing.JLabel CodigoTxt;
    private javax.swing.JLabel RarezaTxt;
    private javax.swing.JPanel ReturnBtn;
    private javax.swing.JLabel TipoTxt;
    private javax.swing.JProgressBar atkBar;
    private javax.swing.JLabel atkValue;
    private javax.swing.JTextField busquedaTxt;
    private javax.swing.JProgressBar defBar;
    private javax.swing.JLabel defValue;
    private javax.swing.JLabel imgCarta;
    private javax.swing.JButton interTxt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel nomcartaTxt;
    private javax.swing.JPanel panelAlbum;
    private javax.swing.JProgressBar psBar;
    private javax.swing.JLabel psValue;
    private javax.swing.JComboBox<String> rarezaBox;
    private javax.swing.JScrollPane scrollAlbum;
    private javax.swing.JComboBox<String> tipoBox;
    // End of variables declaration//GEN-END:variables
}
