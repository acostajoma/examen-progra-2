/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen;

import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author joseacosta
 */
public class ModeloTablaInventario extends AbstractTableModel {
    private ArrayList<Producto> inventario;
    private final String[] columnas = {"Nombre", "Cantidad", "Precio", "Categoría", "Editar", "Eliminar"};

    public ModeloTablaInventario(ArrayList<Producto> inventario) {
        this.inventario = inventario;
    }

     /**
     * Permite que las celdas de las columnas donde estan los botones sean clickeables.
     */
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 4 || columnIndex == 5;
    }

    @Override
    public int getRowCount() {
        return inventario.size();
    }

    @Override
    public int getColumnCount() {
        return columnas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        // Obtenemos el producto en base al rowindex
        Producto producto = inventario.get(rowIndex);
        // Retornamos el resultado del switch
        return switch (columnIndex) {
            case 0 ->  producto.getNombre();
            case 1 -> producto.getCantidad();
            case 2 -> producto.getPrecio();
            case 3 -> producto.getCategoria();
            case 4 -> "Editar"; // Texto para el botón de editar
            case 5 -> "Eliminar"; // Texto para el botón de eliminar
            default -> null;
        };
    }
    
     /**
     * Especifica que las columnas 4 y 5 se renderizarán como botones.
     */
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex == 4 || columnIndex == 5) {
            return JButton.class; 
        }
        return super.getColumnClass(columnIndex);
    }
}
