/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/AWTForms/Dialog.java to edit this template
 */
package modelo.vista;

import controlador.DAO.AnimeDao;
import controlador.DAO.GeneroDAO;
import controlador.ed.lista.ListaEnlazada;
import javax.swing.JOptionPane;
import modelo.Anime;
import modelo.tabla.ModeloTabla;
import modelo.vista.util.Utilidades;

/**
 *
 * @author andy
 */
public class Jframe extends java.awt.Dialog {

    private ModeloTabla modelo = new ModeloTabla();
    private GeneroDAO gd = new GeneroDAO();
    private AnimeDao ad = new AnimeDao();
    private Integer fila = -1;

    /**
     * Creates new form Jframe
     */
    public Jframe(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
//        jPanel2.setOpaque(false);
//        jPanel3.setOpaque(false);
//        jPanel4.setOpaque(false);
        setLocationRelativeTo(null);
        cargarTabla();
        cargarCombo();
    }

    public void limpiar() {
        txtNombre.setText("");
        txtTemp.setText("");
        txtEstado.setText("");
        cargarTabla();
        cargarCombo();
        ad.setAnime(null);
        fila = -1;
        txtBuscar.setText("");
        comboBuscar.setSelectedIndex(0);
    }

    public void cargarTabla() {
        modelo.setLista(ad.listar());
        tblTabla.setModel(modelo);
        tblTabla.updateUI();
    }

    public void cargarCombo() {
        try {
            Utilidades.cargarGenero(comboGenero1, gd);
        } catch (Exception ex) {
        }
    }

    public void guardar() {
        if (txtNombre.getText().trim().isEmpty() || txtTemp.getText().trim().isEmpty() || txtEstado.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Llene los datos", "ERROR", JOptionPane.ERROR_MESSAGE);

        } else {
            try {
                ad.getAnime().setNombre(txtNombre.getText());
                ad.getAnime().setNumTemp(txtTemp.getText());
                ad.getAnime().setEstado(txtEstado.getText());

                ad.getAnime().setGeneroA(gd.buscarPorNombre(comboGenero1.getSelectedItem().toString()).getId());
                if (ad.getAnime().getId() != null) {
                    ad.modificar(fila);
                    limpiar();
                } else {
                    ad.guardar();
                    limpiar();
                }
                JOptionPane.showMessageDialog(null, "Se a Guardado de forma correcta", "OK", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void seleccionar() {
        fila = tblTabla.getSelectedRow();
        if (fila >= 0) {
            try {
                Integer id = modelo.getLista().get(fila).getId();
                ad.setAnime(ad.obtener(id));
                txtNombre.setText(ad.getAnime().getNombre());
                txtTemp.setText(ad.getAnime().getNumTemp());
                txtEstado.setText(ad.getAnime().getEstado());
                comboGenero1.setSelectedItem(gd.obtener(ad.getAnime().getGeneroA()).getNombre().toUpperCase());
            } catch (Exception e) {
            }
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione algo", "ERROR", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void buscarElementoLineal() {
        String por = comboBuscar.getSelectedItem().toString();
        String texto = txtBuscar.getText();
        try {
            if (por.equalsIgnoreCase("Nombre")) {
                modelo.setLista(ad.busquedaLineal(texto, "Nombre"));
            }
            if (por.equalsIgnoreCase("Genero")) {
                modelo.setLista(ad.busquedaLineal(texto, "Genero"));
            }
            if (por.equalsIgnoreCase("Temporada")) {
                modelo.setLista(ad.busquedaLineal(texto, "Temporada"));
            }
            if (por.equalsIgnoreCase("Estado")) {
                modelo.setLista(ad.busquedaLineal(texto, "Estado"));
            }
            tblTabla.setModel(modelo);
            tblTabla.updateUI();

        } catch (Exception e) {
            System.out.println("xd");
        }
    }

    public void buscarElementoBinario() {

        String por = comboBuscar.getSelectedItem().toString();
        String texto = txtBuscar.getText();
        try {
            if (por.equalsIgnoreCase("Nombre")) {
                Anime anime = ad.buscarPorNombreBinaria(texto, "Nombre");
                if (anime != null) {
                    ListaEnlazada<Anime> listaAnime = new ListaEnlazada<>();
                    listaAnime.insertar(anime);
                    modelo.setLista(listaAnime);
                } else {
                    modelo.setLista(new ListaEnlazada<>()); // Si no se encontró el anime, se asigna una lista vacía al modelo
                }
            }
            if (por.equalsIgnoreCase("Genero")) {
                Anime anime = ad.buscarPorNombreBinaria(texto, "Genero");
                if (anime != null) {
                    ListaEnlazada<Anime> listaAnime = new ListaEnlazada<>();
                    listaAnime.insertar(anime);
                    modelo.setLista(listaAnime);
                } else {
                    modelo.setLista(new ListaEnlazada<>()); // Si no se encontró el anime, se asigna una lista vacía al modelo
                }
            }
            if (por.equalsIgnoreCase("Temporada")) {
                Anime anime = ad.buscarPorNombreBinaria(texto, "Temporada");
                if (anime != null) {
                    ListaEnlazada<Anime> listaAnime = new ListaEnlazada<>();
                    listaAnime.insertar(anime);
                    modelo.setLista(listaAnime);
                } else {
                    modelo.setLista(new ListaEnlazada<>()); // Si no se encontró el anime, se asigna una lista vacía al modelo
                }
            }
            if (por.equalsIgnoreCase("Estado")) {
                Anime anime = ad.buscarPorNombreBinaria(texto, "Estado");
                if (anime != null) {
                    ListaEnlazada<Anime> listaAnime = new ListaEnlazada<>();
                    listaAnime.insertar(anime);
                    modelo.setLista(listaAnime);
                } else {
                    modelo.setLista(new ListaEnlazada<>()); // Si no se encontró el anime, se asigna una lista vacía al modelo
                }
            }
            tblTabla.setModel(modelo);
            tblTabla.updateUI();

        } catch (Exception e) {
            System.out.println("xd");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSpinner1 = new javax.swing.JSpinner();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTabla = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtEstado = new javax.swing.JTextField();
        comboGenero1 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtTemp = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        comboBuscar = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        txtBuscar = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(null);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tabla", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Liberation Sans", 1, 15), new java.awt.Color(102, 102, 102))); // NOI18N
        jPanel2.setOpaque(false);

        tblTabla.setBackground(new java.awt.Color(255, 255, 255));
        tblTabla.setForeground(new java.awt.Color(102, 102, 102));
        tblTabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblTabla);

        jButton3.setBackground(new java.awt.Color(255, 255, 255));
        jButton3.setForeground(new java.awt.Color(102, 102, 102));
        jButton3.setText("Seleccionar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton3)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2);
        jPanel2.setBounds(10, 250, 600, 292);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Anime", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Liberation Sans", 1, 15), new java.awt.Color(102, 102, 102))); // NOI18N
        jPanel3.setOpaque(false);

        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setText("Nombre:");

        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setText("Estado:");

        jLabel3.setForeground(new java.awt.Color(102, 102, 102));
        jLabel3.setText("Genero:");

        txtNombre.setBackground(new java.awt.Color(255, 255, 255));
        txtNombre.setForeground(new java.awt.Color(102, 102, 102));

        txtEstado.setBackground(new java.awt.Color(255, 255, 255));
        txtEstado.setForeground(new java.awt.Color(102, 102, 102));

        comboGenero1.setBackground(new java.awt.Color(255, 255, 255));
        comboGenero1.setForeground(new java.awt.Color(102, 102, 102));
        comboGenero1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setForeground(new java.awt.Color(102, 102, 102));
        jButton1.setText("Guardar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setText("Temporadas:");

        txtTemp.setBackground(new java.awt.Color(255, 255, 255));
        txtTemp.setForeground(new java.awt.Color(102, 102, 102));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(comboGenero1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTemp, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(9, 9, 9))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(comboGenero1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtTemp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap())
        );

        jPanel1.add(jPanel3);
        jPanel3.setBounds(10, 10, 600, 131);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Busqueda", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Liberation Sans", 1, 15), new java.awt.Color(102, 102, 102))); // NOI18N
        jPanel4.setOpaque(false);

        comboBuscar.setBackground(new java.awt.Color(255, 255, 255));
        comboBuscar.setForeground(new java.awt.Color(102, 102, 102));
        comboBuscar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nombre", "Temporada", "Genero", "Estado" }));

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setForeground(new java.awt.Color(102, 102, 102));
        jButton2.setText("Lineal");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setText("Buscar por:");

        jButton4.setBackground(new java.awt.Color(255, 255, 255));
        jButton4.setForeground(new java.awt.Color(102, 102, 102));
        jButton4.setText("Binaria");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        txtBuscar.setBackground(new java.awt.Color(255, 255, 255));
        txtBuscar.setForeground(new java.awt.Color(102, 102, 102));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtBuscar))
                .addGap(9, 9, 9))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(jButton2))
                .addContainerGap())
        );

        jPanel1.add(jPanel4);
        jPanel4.setBounds(10, 150, 600, 91);

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/modelo/vista/img/anime-speed-line-background-cartoon-comic-or-manga-effect-style-backdrop-template-graphic-design-element-for-social-media-banner-or-advertising-free-abstract-wallpaper-illustration-free-vector.jpg"))); // NOI18N
        jPanel1.add(jLabel6);
        jLabel6.setBounds(0, 10, 620, 540);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 621, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 551, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Closes the dialog
     */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        setVisible(false);
        dispose();
    }//GEN-LAST:event_closeDialog

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        seleccionar();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        guardar();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        buscarElementoBinario();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        buscarElementoLineal();
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Jframe dialog = new Jframe(new java.awt.Frame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> comboBuscar;
    private javax.swing.JComboBox<String> comboGenero1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JTable tblTabla;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtEstado;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtTemp;
    // End of variables declaration//GEN-END:variables
}
