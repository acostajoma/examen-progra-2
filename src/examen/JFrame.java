/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package examen;

import examen.GestorInventario;
import examen.ModeloTablaInventario;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author joseacosta y victor
 */
public class JFrame extends javax.swing.JFrame {

    private GestorInventario gestor; // Declaramos la clase de gestor
    private ModeloTablaInventario modeloTabla; // Declaramos la clase de la tabla
    private TableRowSorter<ModeloTablaInventario> filtro;
    /**
     * Creates new form JFrame
     */
    public JFrame() {
        gestor = new GestorInventario("inventario.txt");

        initComponents();
        
        modeloTabla = new ModeloTablaInventario(gestor.getInventario());
        tablaInventario.setModel(modeloTabla);
        // Permitir seleccionar solo una fila a la vez
        tablaInventario.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        filtro = new TableRowSorter<>(modeloTabla);
        tablaInventario.setRowSorter(filtro);
        botonEliminarFiltro.setVisible(false);

        this.setTitle("Gestor de Inventario Principal");
        this.setLocationRelativeTo(null);
        
        actualizarTabla();
    }


    public final void actualizarTabla() {
        modeloTabla.fireTableDataChanged();
    }
    
    /**
     * Lógica para editar un producto. Se activa desde el botón de la tabla.
     *
     * @param rowIndex La fila del modelo que se va a editar.
     */
    public void editarProducto(int rowIndex) {
        Producto productoAEditar = gestor.getInventario().get(rowIndex);

        JTextField nombreField = new JTextField(productoAEditar.getNombre(), 15);
        JTextField cantidadField = new JTextField(String.valueOf(productoAEditar.getCantidad()), 5);
        JTextField precioField = new JTextField(String.valueOf(productoAEditar.getPrecio()), 10);
        JTextField categoriaField = new JTextField(productoAEditar.getCategoria(), 15);

        JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));
        panel.add(new JLabel("Nombre:"));
        panel.add(nombreField);
        panel.add(new JLabel("Cantidad:"));
        panel.add(cantidadField);
        panel.add(new JLabel("Precio:"));
        panel.add(precioField);
        panel.add(new JLabel("Categoría:"));
        panel.add(categoriaField);

        int result = JOptionPane.showConfirmDialog(this, panel, "Editar Producto",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            try {
                // Validación similar a la de agregar...
                Producto productoModificado = new Producto(
                        nombreField.getText(),
                        categoriaField.getText(),
                        Double.parseDouble(precioField.getText()),
                        Integer.parseInt(cantidadField.getText())
                );
                gestor.modificarProducto(productoAEditar.getNombre(), productoModificado);
                actualizarTabla();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Valores numéricos inválidos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void editarProductoAccion() {
        int viewRow = tablaInventario.getSelectedRow();

        if (viewRow == -1) {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione una fila para editar.", "Ninguna Fila Seleccionada", JOptionPane.WARNING_MESSAGE);
        } else {
            // Es importante convertir el índice de la vista al del modelo, por si la tabla se ordena.
            int modelRow = tablaInventario.convertRowIndexToModel(viewRow);
            editarProducto(modelRow);
        }
    }

    /**
     * Lógica para eliminar un producto. Se activa desde el botón de la tabla.
     *
     * @param rowIndex La fila del modelo que se va a eliminar.
     */
    public void eliminarProducto(int rowIndex) {
        Producto productoAEliminar = gestor.getInventario().get(rowIndex);
        int confirm = JOptionPane.showConfirmDialog(this,
                "¿Estás seguro de que quieres eliminar el producto '" + productoAEliminar.getNombre() + "'?",
                "Confirmar Eliminación",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            gestor.eliminarProducto(productoAEliminar.getNombre());
            actualizarTabla();
        }
    }

    public void eliminarProductoAccion() {
        int viewRow = tablaInventario.getSelectedRow();

        if (viewRow == -1) {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione una fila para eliminar.", "Ninguna Fila Seleccionada", JOptionPane.WARNING_MESSAGE);
        } else {
            int modelRow = tablaInventario.convertRowIndexToModel(viewRow);
            eliminarProducto(modelRow);
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

        panel = new javax.swing.JScrollPane();
        tablaInventario = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        botonPanelModificar = new javax.swing.JButton();
        botonPanelEliminar = new javax.swing.JButton();
        botonEliminarFiltro = new javax.swing.JButton();
        barraSuperior = new javax.swing.JMenuBar();
        botonArchivo = new javax.swing.JMenu();
        botonSalir = new javax.swing.JMenuItem();
        botonOperaciones = new javax.swing.JMenu();
        botonAgregar = new javax.swing.JMenuItem();
        botonModificar = new javax.swing.JMenuItem();
        botonEliminar = new javax.swing.JMenuItem();
        botonBuscar = new javax.swing.JMenuItem();
        botonListar = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tablaInventario.setModel(new javax.swing.table.DefaultTableModel(
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
        panel.setViewportView(tablaInventario);

        botonPanelModificar.setText("Editar Producto");
        botonPanelModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonPanelModificarActionPerformed(evt);
            }
        });

        botonPanelEliminar.setText("Eliminar Producto");
        botonPanelEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonPanelEliminarActionPerformed(evt);
            }
        });

        botonEliminarFiltro.setText("Eliminar Filtro");
        botonEliminarFiltro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEliminarFiltroActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(botonPanelModificar)
                .addGap(18, 18, 18)
                .addComponent(botonPanelEliminar)
                .addGap(18, 18, 18)
                .addComponent(botonEliminarFiltro)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonPanelModificar)
                    .addComponent(botonPanelEliminar)
                    .addComponent(botonEliminarFiltro))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        botonArchivo.setText("Archivo");

        botonSalir.setText("Salir");
        botonSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonSalirActionPerformed(evt);
            }
        });
        botonArchivo.add(botonSalir);

        barraSuperior.add(botonArchivo);

        botonOperaciones.setText("Operaciones");

        botonAgregar.setText("Agregar");
        botonAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAgregarActionPerformed(evt);
            }
        });
        botonOperaciones.add(botonAgregar);

        botonModificar.setText("Modificar");
        botonModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonModificarActionPerformed(evt);
            }
        });
        botonOperaciones.add(botonModificar);

        botonEliminar.setText("Eliminar");
        botonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEliminarActionPerformed(evt);
            }
        });
        botonOperaciones.add(botonEliminar);

        botonBuscar.setText("Buscar Por Nombre");
        botonBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonBuscarActionPerformed(evt);
            }
        });
        botonOperaciones.add(botonBuscar);

        botonListar.setText("Listar por Categoría");
        botonListar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonListarActionPerformed(evt);
            }
        });
        botonOperaciones.add(botonListar);

        barraSuperior.add(botonOperaciones);

        setJMenuBar(barraSuperior);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 626, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, 461, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAgregarActionPerformed
        // Creamos paneles y campos de texto para el diálogo
        JTextField nombreField = new JTextField(15);
        JTextField cantidadField = new JTextField(5);
        JTextField precioField = new JTextField(10);
        JTextField categoriaField = new JTextField(15);

        JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));
        panel.add(new JLabel("Nombre:"));
        panel.add(nombreField);
        panel.add(new JLabel("Cantidad:"));
        panel.add(cantidadField);
        panel.add(new JLabel("Precio:"));
        panel.add(precioField);
        panel.add(new JLabel("Categoría:"));
        panel.add(categoriaField);

        int result = JOptionPane.showConfirmDialog(this, panel, "Agregar Nuevo Producto",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            try {
                String nombre = nombreField.getText();
                int cantidad = Integer.parseInt(cantidadField.getText());
                double precio = Double.parseDouble(precioField.getText());
                String categoria = categoriaField.getText();

                if (nombre.trim().isEmpty() || categoria.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Nombre y categoría no pueden estar vacíos.", "Error de Entrada", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (cantidad < 0 || precio < 0) {
                    JOptionPane.showMessageDialog(this, "Cantidad y precio no pueden ser negativos.", "Error de Entrada", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Producto nuevoProducto = new Producto(nombre, categoria, precio, cantidad);
                gestor.agregarProducto(nuevoProducto);
                actualizarTabla();
                JOptionPane.showMessageDialog(this, "Producto agregado exitosamente.");

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Por favor, ingrese valores numéricos válidos para cantidad y precio.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_botonAgregarActionPerformed

    private void botonListarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonListarActionPerformed
        Listar ventanaListar = new Listar(
                this,
                true,
                gestor, 
                filtro,
                botonEliminarFiltro
        );
        ventanaListar.setVisible(true);
    }//GEN-LAST:event_botonListarActionPerformed

    private void botonPanelEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonPanelEliminarActionPerformed
        eliminarProductoAccion();
    }//GEN-LAST:event_botonPanelEliminarActionPerformed

    private void botonPanelModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonPanelModificarActionPerformed
        editarProductoAccion();
    }//GEN-LAST:event_botonPanelModificarActionPerformed

    private void botonModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonModificarActionPerformed
        editarProductoAccion();
    }//GEN-LAST:event_botonModificarActionPerformed

    private void botonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEliminarActionPerformed
        eliminarProductoAccion();
    }//GEN-LAST:event_botonEliminarActionPerformed

    private void botonBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonBuscarActionPerformed
        Buscar ventanaBuscar = new Buscar(
                this,
                true,
                gestor,
                modeloTabla,
                tablaInventario
        ); // 'this' es la ventana Main
        ventanaBuscar.setVisible(true);
    }//GEN-LAST:event_botonBuscarActionPerformed

    private void botonEliminarFiltroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEliminarFiltroActionPerformed
        filtro.setRowFilter(null);
        botonEliminarFiltro.setVisible(false);
    }//GEN-LAST:event_botonEliminarFiltroActionPerformed

    private void botonSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonSalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_botonSalirActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar barraSuperior;
    private javax.swing.JMenuItem botonAgregar;
    private javax.swing.JMenu botonArchivo;
    private javax.swing.JMenuItem botonBuscar;
    private javax.swing.JMenuItem botonEliminar;
    private javax.swing.JButton botonEliminarFiltro;
    private javax.swing.JMenuItem botonListar;
    private javax.swing.JMenuItem botonModificar;
    private javax.swing.JMenu botonOperaciones;
    private javax.swing.JButton botonPanelEliminar;
    private javax.swing.JButton botonPanelModificar;
    private javax.swing.JMenuItem botonSalir;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane panel;
    private javax.swing.JTable tablaInventario;
    // End of variables declaration//GEN-END:variables
}
