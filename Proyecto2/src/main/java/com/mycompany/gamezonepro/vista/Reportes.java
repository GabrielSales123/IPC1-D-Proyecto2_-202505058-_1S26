
package com.mycompany.gamezonepro.vista;

import com.mycompany.gamezonepro.controlador.ControlTienda;
import com.mycompany.gamezonepro.controlador.ControlTorneos;
import com.mycompany.gamezonepro.controlador.ControlUsuarios;
import com.mycompany.gamezonepro.modelo.Carta;
import com.mycompany.gamezonepro.modelo.Usuario;
import com.mycompany.gamezonepro.modelo.estructuras.Cola;
import com.mycompany.gamezonepro.modelo.estructuras.ListaSimple;
import com.mycompany.gamezonepro.modelo.estructuras.MallaOrtogonal;
import com.mycompany.gamezonepro.modelo.estructuras.NodoMatriz;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.Desktop;



public class Reportes extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Reportes.class.getName());
    private ControlUsuarios sisu;
    private ControlTienda sisr; 
    private Usuario usuarioActual;
    private ControlTorneos sisto;
    private Cola<String> cola; 
    String fecha = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss").format(new Date());
    String nombreArchivo = fecha + "_Inventario.html";
    /**
     * Creates new form Reportes
     */
    public Reportes(ControlUsuarios sisu, Usuario usuarioActual) {
        this.sisr = new ControlTienda(4,6);
        this.usuarioActual = usuarioActual;
        this.sisu = sisu;
        this.cola = new Cola<>();
        this.sisto = new ControlTorneos(this.cola); 
        this.sisto.cargarTorneos();
        initComponents();
    }

    
    public void generarReporteInventario(ListaSimple<Carta> catalogo) {
        try {
        String fecha = new java.text.SimpleDateFormat("dd_MM_yyyy_HH_mm_ss")
                        .format(new java.util.Date());
        String nombreArchivo = fecha + "_Inventario.html";
        FileWriter writer = new FileWriter(nombreArchivo);
        writer.write("<html>");
        writer.write("<head><title>Inventario de Tienda</title></head>");
        writer.write("<body>");
        writer.write("<h1>Reporte de Inventario</h1>");
        writer.write("<table border='1'>");
        writer.write("<tr>");
        writer.write("<th>Nombre</th>");
        writer.write("<th>Stock</th>");
        writer.write("<th>Precio</th>");
        writer.write("<th>Plataforma</th>");
        writer.write("</tr>");
        for (int i = 0; i < catalogo.tamanio(); i++) {
            Carta c = catalogo.buscar(i);
            writer.write("<tr>");
            writer.write("<td>" + c.getNombre() + "</td>");
            writer.write("<td>" + c.getStock() + "</td>");
            writer.write("<td>Q" + c.getPrecio() + "</td>");
            writer.write("<td>" + c.getTipo() + "</td>");
            writer.write("</tr>");
        }
        writer.write("</table>");
        writer.write("</body>");
        writer.write("</html>");
        writer.close();
        Desktop.getDesktop().browse(new File(nombreArchivo).toURI());

        }   catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void generarReporteVentas(Usuario usuario) {
        try {
            String fecha = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss").format(new Date());
            String nombreArchivo = fecha + "_Ventas.html";
            FileWriter writer = new FileWriter(nombreArchivo);
            writer.write("<html>");
            writer.write("<head><title>Reporte de Ventas</title></head>");
            writer.write("<body>");
            writer.write("<h1>Reporte de Ventas</h1>");
            writer.write("<h2>Usuario: " + usuario.getId() + "</h2>");
            writer.write("<table border='1'>");
            writer.write("<tr>");
            writer.write("<th>#</th>");
            writer.write("<th>Nombre</th>");
            writer.write("<th>Tipo</th>");
            writer.write("<th>Rareza</th>");
            writer.write("<th>Precio</th>");
            writer.write("</tr>");
        double total = 0;
        for (int i = 0; i < usuario.getHistorialCompras().tamanio(); i++) {
            Carta c = usuario.getHistorialCompras().buscar(i);
            if (c == null) continue;
            writer.write("<tr>");
            writer.write("<td>" + (i + 1) + "</td>");
            writer.write("<td>" + c.getNombre() + "</td>");
            writer.write("<td>" + c.getTipo() + "</td>");
            writer.write("<td>" + c.getRareza() + "</td>");
            writer.write("<td>Q" + c.getPrecio() + "</td>");
            writer.write("</tr>");
            total += c.getPrecio();
        }
        writer.write("</table>");
        writer.write("<h3>Total gastado: Q" + total + "</h3>");
        writer.write("</body>");
        writer.write("</html>");
        writer.close();
        File archivo = new File(nombreArchivo);
        Desktop.getDesktop().browse(archivo.toURI());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void generarReporteTorneos(ControlTorneos sisto) {
            try {
            String fecha = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss")
                .format(new Date());
        String nombreArchivo = fecha + "_Torneos.html";
        FileWriter writer = new FileWriter(nombreArchivo);
        writer.write("<html>");
        writer.write("<head><title>Reporte Torneos</title></head>");
        writer.write("<body>");
        writer.write("<h1>Lista de Torneos</h1>");
        writer.write("<table border='1'>");
        writer.write("<tr>");
        writer.write("<th>Nombre Torneo</th>");
        writer.write("<th>Juego</th>");
        writer.write("<th>Fecha</th>");
        writer.write("<th>Hora</th>");
        writer.write("</tr>");
        for (int i = 0; i < sisto.getTotalTorneos(); i++) {
            var t = sisto.getTorneos()[i];
            writer.write("<tr>");
            writer.write("<td>" + t.getNombre() + "</td>");
            writer.write("<td>" + t.getJuego() + "</td>");
            writer.write("<td>" + t.getFecha() + "</td>");
            writer.write("<td>" + t.getHora() + "</td>");
            StringBuilder tickets = new StringBuilder();
            var cola = t.getCola();
            var nodo = cola.getFrenteNodo();
            while (nodo != null) {
                tickets.append(nodo.dato).append(" ");
                nodo = nodo.siguiente;
            }
                writer.write("<td>" + tickets.toString() + "</td>");
                writer.write("</tr>");
            }
            writer.write("</table>");
            writer.write("</body>");
            writer.write("</html>");
            writer.close();
            Desktop.getDesktop().browse(new File(nombreArchivo).toURI());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void generarReporteAlbum(Usuario usuarioActual) {
        try {
            String fecha = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss")
                .format(new Date());
        String nombreArchivo = fecha + "_Album.html";
        FileWriter writer = new FileWriter(nombreArchivo);
        MallaOrtogonal album = usuarioActual.getAlbum().getMalla();
        writer.write("<html>");
        writer.write("<head>");
        writer.write("<title>Reporte del Álbum</title>");
        writer.write("<style>");
        writer.write("table { border-collapse: collapse; }");
        writer.write("td { width: 140px; height: 120px; text-align: center; border: 1px solid black; }");
        writer.write(".vacia { background-color: #cfcfcf; }");
        writer.write(".legendaria { background-color: gold; }");
        writer.write(".comun { background-color: white; }");
        writer.write("</style>");
        writer.write("</head>");
        writer.write("<body>");
        writer.write("<h1>Álbum del Usuario: " + usuarioActual.getId() + "</h1>");
        writer.write("<table>");
        NodoMatriz fila = album.getNodo(0, 0);
        while (fila != null) {
            writer.write("<tr>");
            NodoMatriz actual = fila;
            while (actual != null) {
                Carta c = actual.getDato();
                if (c == null) {
                    writer.write("<td class='vacia'>VACÍA</td>");
                } else {
                    String clase = "comun";
                    if (c.getRareza().equalsIgnoreCase("Legendaria")) {
                        clase = "legendaria";
                    }
                        writer.write("<td class='" + clase + "'>");
                        writer.write("<b>" + c.getNombre() + "</b><br>");
                        writer.write(c.getTipo() + "<br>");
                        writer.write(c.getRareza());
                        writer.write("</td>");
                    }
                    actual = actual.getDerecha();
                }
                writer.write("</tr>");
                fila = fila.getAbajo();
            }
            writer.write("</table>");
            writer.write("</body>");
            writer.write("</html>");
            writer.close();
            Desktop.getDesktop().browse(new File(nombreArchivo).toURI());
        } catch (Exception e) {
            e.printStackTrace();
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
        jLabel1 = new javax.swing.JLabel();
        ReturnBtn = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setText("Reportes");

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ReturnBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(276, 276, 276)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(ReturnBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        jButton1.setText("Reporte de inventario");
        jButton1.addActionListener(this::jButton1ActionPerformed);

        jButton2.setText("Reporte de Ventas");
        jButton2.addActionListener(this::jButton2ActionPerformed);

        jButton3.setText("Reporte de album");
        jButton3.addActionListener(this::jButton3ActionPerformed);

        jButton4.setText("Reporte Torneo");
        jButton4.addActionListener(this::jButton4ActionPerformed);

        jLabel3.setText("Seleccione tipo de reporte: ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE))
                        .addGap(90, 90, 90)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                            .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(jLabel3)))
                .addContainerGap(118, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 21, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ReturnBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ReturnBtnMouseClicked
        java.awt.EventQueue.invokeLater(() -> {
            this.dispose();
            new MenuPrincipal(sisu, usuarioActual).setVisible(true);
        });
    }//GEN-LAST:event_ReturnBtnMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        sisr.stock();
        sisr.inventariar();
        generarReporteInventario(sisr.getInventario());
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        generarReporteVentas(usuarioActual);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        generarReporteTorneos(sisto);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        generarReporteAlbum( usuarioActual);
    }//GEN-LAST:event_jButton3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel ReturnBtn;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
