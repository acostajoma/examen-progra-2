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
 * @author joseacosta y victor
 */
public class ModeloTablaInventario extends AbstractTableModel {
    private ArrayList<Producto> inventario;
    private final String[] columnas = {
        "Nombre", "Cantidad", "Precio", "Categoría"
    };
    public ModeloTablaInventario(ArrayList<Producto> inventario) {
        this.inventario = inventario;
    }

    
    /**
    * Busca un producto por su nombre y devuelve su índice en la tabla.
    * @param nombre El nombre del producto a buscar.
    * @return El índice de la fila (0 o más) si se encuentra, o -1 si no existe.
    */
public int encuentraIndiceFilaPorNombre(String nombre) {
    for (int i = 0; i < inventario.size(); i++) {
        if (inventario.get(i).getNombre().equalsIgnoreCase(nombre)) {
            return i; // Devuelve el índice si encuentra el producto
        }
    }
    return -1; // Devuelve -1 si no lo encuentra
}

    @Override
    public int getRowCount() {
        return inventario.size();
    }
    
    // Devuelve el largo de columnas de la tabla
    @Override
    public int getColumnCount() {
        return columnas.length;
    }
    
    // Devuelve el nombre de una columna en base al indice de la columna
    @Override
    public String getColumnName(int columnIndex) {
        return columnas[columnIndex];
    }

    // Devuelve el valor de una celda en base a # de fila y # columna
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
            default -> null;
        };
    }
     
    // Ninguna celda es editable directamente en la tabla.
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
}
