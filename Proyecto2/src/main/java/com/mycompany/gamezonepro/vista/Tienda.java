package com.mycompany.gamezonepro.vista;

import com.mycompany.gamezonepro.controlador.*;
import com.mycompany.gamezonepro.modelo.*;
import com.mycompany.gamezonepro.modelo.estructuras.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;
import javax.swing.*;


public class Tienda extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Tienda.class.getName());
    private ControlUsuarios sisu;
    private ControlRecompensas sisr; 
    private Usuario usuarioActual; 
    private MallaOrtogonal tienda; 
    private ControlTienda sisa; 
    private NodoMatriz seleccion1 = null; 
    private NodoMatriz seleccion2 = null; 
    private boolean intercambio = false;
    /**
     * Creates new form Tienda
     */
    public Tienda(ControlUsuarios sisu, Usuario usuarioActual, ControlRecompensas sisr) {
        ControlTienda sisa = new ControlTienda(4,6);
        this.sisa = sisa;
        this.sisr = sisr; 
        this.usuarioActual = usuarioActual;
        this.sisu = sisu;
        sisa.stock();
        initComponents();
        modoCompra(false);
        inicializarObjetos();
        inicializarTienda();
        renderizarTienda();
    }
    
    public void inicializarTienda() {
    panelTienda.removeAll();
    panelTienda.setPreferredSize(new Dimension(600, 400));
    panelTienda.setLayout(new GridLayout(4, 6, 10, 10));
    for (int i = 0; i < 24; i++) {
        JPanel celda = new JPanel();
        celda.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(Color.BLACK),
        BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        JLabel texto = new JLabel("Vacía");
        celda.add(texto);
        panelTienda.add(celda);
    }
    panelTienda.revalidate();
    panelTienda.repaint();
}

    public void renderizarTienda() {
        panelTienda.removeAll();
        panelTienda.setLayout(new GridLayout(4, 6, 6, 6));
        NodoMatriz fila = sisa.getTienda().getNodo(0, 0);
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
                panelTienda.add(celda);
                actual = actual.derecha;
            }
            fila = fila.abajo;
        }
        panelTienda.revalidate();
        panelTienda.repaint();
    }
    
    public void mostrarCarta(Carta carta){
        modoCompra(false);
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
        precioTxt.setText("Precio: "+String.valueOf(carta.getPrecio()));
       
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
        NodoMatriz fila = sisa.getTienda().getNodo(0, 0);
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
        renderizarTienda();
    }
    
    private void manejoClick(NodoMatriz nodo){
        if(nodo.getDato() == null){
            return;
        }
        if(intercambio == false){
            seleccion1 = nodo; 
            mostrarCarta(nodo.getDato());
            nodo.setResaltada(true);
            renderizarTienda();
            
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
            renderizarTienda();
            
        }
    }
    
    public void modoCompra(boolean estado){
        if (estado == true){
            carritoTxt.setVisible(true);
            carritoTxt.setText("Carrito de compras");
            comprasTxt.setVisible(true);
            String texto = "<html>Compras:<br>";
            double total = 0; 
            Carta aux;
            for(int i = 0; i<sisa.getContador(); i++){
                aux = sisa.buscarCarrito(i);
                texto = texto+"Nombre: "+aux.getNombre()+" Precio: "+aux.getPrecio()+"<br>";
                total = total + aux.getPrecio();
            }
            texto = texto +"</html>";
            comprasTxt.setText(texto);
            confirmarTxt.setVisible(true);
            agregarTxt.setVisible(false);
            nomcartaTxt.setVisible(false);
            CodigoTxt.setVisible(false);
            TipoTxt.setVisible(false);
            RarezaTxt.setVisible(false);
            atkBar.setVisible(false);
            atkValue.setVisible(false);
            defBar.setVisible(false);
            defValue.setVisible(false);
            psBar.setVisible(false);
            psValue.setVisible(false);
            panelImg.setVisible(false);
            precioTxt.setVisible(false);
            atkTxt.setVisible(false);
            defTxt.setVisible(false);
            psTxt.setVisible(false);
            AtributosTxt.setVisible(false);
            totalTxt.setVisible(true);
            totalTxt.setText("Total: "+String.valueOf(total));
            
        }
        else{
            carritoTxt.setVisible(false);
            comprasTxt.setVisible(false);
            confirmarTxt.setVisible(false);
            agregarTxt.setVisible(true);
            nomcartaTxt.setVisible(true);
            CodigoTxt.setVisible(true);
            TipoTxt.setVisible(true);
            RarezaTxt.setVisible(true);
            atkBar.setVisible(true);
            atkValue.setVisible(true);
            defBar.setVisible(true);
            defValue.setVisible(true);
            psBar.setVisible(true);
            psValue.setVisible(true);
            panelImg.setVisible(true);
            precioTxt.setVisible(true);
            atkTxt.setVisible(true);
            defTxt.setVisible(true);
            psTxt.setVisible(true);
            AtributosTxt.setVisible(true);
            totalTxt.setVisible(false);
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
        jPanel2 = new javax.swing.JPanel();
        rarezaBox = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        BuscarBtn = new javax.swing.JButton();
        busquedaTxt = new javax.swing.JTextField();
        tipoBox = new javax.swing.JComboBox<>();
        CarritoBtn = new javax.swing.JButton();
        scrollTienda = new javax.swing.JScrollPane();
        panelTienda = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        AtributosTxt = new javax.swing.JLabel();
        CodigoTxt = new javax.swing.JLabel();
        TipoTxt = new javax.swing.JLabel();
        RarezaTxt = new javax.swing.JLabel();
        atkTxt = new javax.swing.JLabel();
        defTxt = new javax.swing.JLabel();
        psTxt = new javax.swing.JLabel();
        agregarTxt = new javax.swing.JButton();
        atkBar = new javax.swing.JProgressBar();
        defBar = new javax.swing.JProgressBar();
        psBar = new javax.swing.JProgressBar();
        atkValue = new javax.swing.JLabel();
        defValue = new javax.swing.JLabel();
        psValue = new javax.swing.JLabel();
        panelImg = new javax.swing.JPanel();
        imgCarta = new javax.swing.JLabel();
        nomcartaTxt = new javax.swing.JLabel();
        carritoTxt = new javax.swing.JLabel();
        comprasTxt = new javax.swing.JLabel();
        precioTxt = new javax.swing.JLabel();
        confirmarTxt = new javax.swing.JButton();
        totalTxt = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setText("Tienda");

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

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        rarezaBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel3.setText("Buscar:");

        BuscarBtn.setText("Buscar");
        BuscarBtn.addActionListener(this::BuscarBtnActionPerformed);

        busquedaTxt.addActionListener(this::busquedaTxtActionPerformed);

        tipoBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        CarritoBtn.setText("Carrito");
        CarritoBtn.addActionListener(this::CarritoBtnActionPerformed);

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(CarritoBtn)
                .addGap(46, 46, 46))
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
                    .addComponent(BuscarBtn)
                    .addComponent(CarritoBtn))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ReturnBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(297, 297, 297)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        panelTienda.setLayout(new GridLayout(4, 6));
        panelTienda.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panelTienda.setPreferredSize(new java.awt.Dimension(600, 400));

        javax.swing.GroupLayout panelTiendaLayout = new javax.swing.GroupLayout(panelTienda);
        panelTienda.setLayout(panelTiendaLayout);
        panelTiendaLayout.setHorizontalGroup(
            panelTiendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 598, Short.MAX_VALUE)
        );
        panelTiendaLayout.setVerticalGroup(
            panelTiendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 398, Short.MAX_VALUE)
        );

        scrollTienda.setViewportView(panelTienda);

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        AtributosTxt.setText("Atributos");

        CodigoTxt.setText("Codigo");

        TipoTxt.setText("Tipo");

        RarezaTxt.setText("Rareza");

        atkTxt.setText("ATK");

        defTxt.setText("DEF");

        psTxt.setText("PS");

        agregarTxt.setText("Agregar al carrito ");
        agregarTxt.addActionListener(this::agregarTxtActionPerformed);

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

        panelImg.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panelImg.setPreferredSize(new java.awt.Dimension(130, 130));

        javax.swing.GroupLayout panelImgLayout = new javax.swing.GroupLayout(panelImg);
        panelImg.setLayout(panelImgLayout);
        panelImgLayout.setHorizontalGroup(
            panelImgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(imgCarta, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
        );
        panelImgLayout.setVerticalGroup(
            panelImgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(imgCarta, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
        );

        nomcartaTxt.setText("Nombre");

        carritoTxt.setText("C");

        comprasTxt.setText("com");

        precioTxt.setText("Precio");

        confirmarTxt.setText("Confirmar compra");
        confirmarTxt.addActionListener(this::confirmarTxtActionPerformed);

        totalTxt.setText("Total:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(carritoTxt, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(comprasTxt, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addComponent(panelImg, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(80, 80, 80))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(116, 116, 116)
                .addComponent(nomcartaTxt)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(RarezaTxt)
                    .addComponent(TipoTxt)
                    .addComponent(CodigoTxt)
                    .addComponent(AtributosTxt)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(atkTxt)
                            .addComponent(defTxt)
                            .addComponent(psTxt))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(psBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(defBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(atkBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(defValue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(atkValue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(psValue, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(agregarTxt)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(confirmarTxt))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(precioTxt)
                        .addGap(78, 78, 78)
                        .addComponent(totalTxt)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelImg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(carritoTxt)
                        .addGap(18, 18, 18)
                        .addComponent(comprasTxt)))
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
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(atkTxt)
                                    .addComponent(atkBar, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(defTxt)
                                    .addComponent(defBar, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(psTxt)
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
                        .addComponent(precioTxt)
                        .addGap(32, 32, 32)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(agregarTxt)
                            .addComponent(confirmarTxt))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(totalTxt)
                        .addGap(49, 49, 49))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollTienda, javax.swing.GroupLayout.PREFERRED_SIZE, 599, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(scrollTienda, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ReturnBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ReturnBtnMouseClicked
        java.awt.EventQueue.invokeLater(() -> {
            this.dispose();
            new MenuPrincipal(sisu, usuarioActual).setVisible(true);
        });
    }//GEN-LAST:event_ReturnBtnMouseClicked

    private void BuscarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuscarBtnActionPerformed
        String texto = busquedaTxt.getText();
        String tipo = tipoBox.getSelectedItem().toString();
        String rareza = rarezaBox.getSelectedItem().toString();
        buscarCartas(texto, tipo, rareza);
    }//GEN-LAST:event_BuscarBtnActionPerformed

    private void busquedaTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_busquedaTxtActionPerformed

    }//GEN-LAST:event_busquedaTxtActionPerformed

    private void agregarTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarTxtActionPerformed
        if(seleccion1 != null){
            sisa.agregarCarrito(seleccion1.getDato());
            JOptionPane.showMessageDialog(this, "Carta agregada al carrito");
        } else {
            JOptionPane.showMessageDialog(this, "NO se ha seleccionado carta");
        }
    }//GEN-LAST:event_agregarTxtActionPerformed

    private void confirmarTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmarTxtActionPerformed
        Carta aux;
            for(int i = 0; i<sisa.getContador(); i++){
                aux = sisa.buscarCarrito(i);
                if (aux == null) continue;
                usuarioActual.getHistorialCompras().agregar(aux);
                usuarioActual.sumarXp(50);
                if (aux.getRareza() == "Legendaria"){
                    usuarioActual.sumarXp(200);
                    sisr.gestionLogros(usuarioActual.getCompras(), usuarioActual.getAlbum().getMalla().contarCartas(usuarioActual.getAlbum().getMalla().getNodo(0, 0)), 
                    usuarioActual.getXp(), usuarioActual.getGastos(), aux);
                    
                }
                usuarioActual.sumarCompra();
                usuarioActual.sumaGasto(aux.getPrecio());
                sisr.gestionLogros(usuarioActual.getCompras(), usuarioActual.getAlbum().getMalla().contarCartas(usuarioActual.getAlbum().getMalla().getNodo(0, 0)), 
                usuarioActual.getXp(), usuarioActual.getGastos(), null);
                usuarioActual.getAlbum().agregarCarta(aux);
                sisa.eliminarCarrito(i);
            }
            sisa.setContador(0);
            JOptionPane.showMessageDialog(this, "Compra realizada con exito");
            modoCompra(true);
    }//GEN-LAST:event_confirmarTxtActionPerformed

    private void CarritoBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CarritoBtnActionPerformed
        modoCompra(true);
    }//GEN-LAST:event_CarritoBtnActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel AtributosTxt;
    private javax.swing.JButton BuscarBtn;
    private javax.swing.JButton CarritoBtn;
    private javax.swing.JLabel CodigoTxt;
    private javax.swing.JLabel RarezaTxt;
    private javax.swing.JPanel ReturnBtn;
    private javax.swing.JLabel TipoTxt;
    private javax.swing.JButton agregarTxt;
    private javax.swing.JProgressBar atkBar;
    private javax.swing.JLabel atkTxt;
    private javax.swing.JLabel atkValue;
    private javax.swing.JTextField busquedaTxt;
    private javax.swing.JLabel carritoTxt;
    private javax.swing.JLabel comprasTxt;
    private javax.swing.JButton confirmarTxt;
    private javax.swing.JProgressBar defBar;
    private javax.swing.JLabel defTxt;
    private javax.swing.JLabel defValue;
    private javax.swing.JLabel imgCarta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel nomcartaTxt;
    private javax.swing.JPanel panelImg;
    private javax.swing.JPanel panelTienda;
    private javax.swing.JLabel precioTxt;
    private javax.swing.JProgressBar psBar;
    private javax.swing.JLabel psTxt;
    private javax.swing.JLabel psValue;
    private javax.swing.JComboBox<String> rarezaBox;
    private javax.swing.JScrollPane scrollTienda;
    private javax.swing.JComboBox<String> tipoBox;
    private javax.swing.JLabel totalTxt;
    // End of variables declaration//GEN-END:variables
}
