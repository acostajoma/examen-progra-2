/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author joseacosta
 */
public class GestorInventario {

    private ArrayList<Producto> inventario;
    private String nombre;

    public GestorInventario(String nombre) {
        this.inventario = new ArrayList<>();
        this.nombre = nombre;
    }

    public ArrayList<Producto> getInventario() {
        return inventario;
    }

    public void setInventario(ArrayList<Producto> inventario) {
        this.inventario = inventario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void agregarProducto(Producto producto) {
        inventario.add(producto);
        guardarInventario();
    }

    public Producto buscarProductoPorNombre(String nombre) {
        for (Producto producto : inventario) {
            // Compara ignorando diferencias en mayusculas y minusculas
            if (producto.getNombre().equalsIgnoreCase(nombre)) {
                return producto;
            }
        }
        // Si no encuentra el producto devuelve null
        return null;
    }

    public boolean eliminarProducto(String nombreProducto) {
        Producto productoAEliminar = buscarProductoPorNombre(nombreProducto);
        // Si el producto existe
        if (productoAEliminar != null) {
            inventario.remove(productoAEliminar);
            guardarInventario();
            return true;
        }
        // Retorna falso si no se elimina el producto
        return false;
    }

    public void modificarProducto(String nombreProducto, Producto productoModificado) {
        Producto productoAModificar = buscarProductoPorNombre(nombreProducto);
        // Si el producto existe preocedemos a modificar
        if (productoAModificar != null) {
            // Modificamos Usando los setters de Producto
            productoAModificar.setCantidad(productoModificado.getCantidad());
            productoAModificar.setCategoria(productoModificado.getCategoria());
            productoAModificar.setPrecio(productoModificado.getPrecio());
            productoAModificar.setNombre(productoModificado.getNombre());
            guardarInventario();
        }
    }

    private void guardarInventario() {
        try (FileWriter fw = new FileWriter(nombre); BufferedWriter bw = new BufferedWriter(fw)) {

            for (Producto producto : inventario) {
                bw.write(producto.darFormatoProducto());
                bw.newLine();
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar el inventario: " + e.getMessage(), "Error de Archivo", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void cargarInventario() {
        File archivo = new File(nombre);
        if (!archivo.exists()) {
            // Si el archivo no existe, no hay nada que cargar.
            return;
        }

        try (FileReader fr = new FileReader(archivo); BufferedReader br = new BufferedReader(fr)) {

            String linea;
            // Ejemplo de como vienen los datos
            // nombre+";"+categoria+";"+precio+";"+cantidad
            while ((linea = br.readLine()) != null) {
                // Agregamos los datos a una lista
                String[] datos = linea.split(";");
                //Revisamos que el largo sea de 4
                if (datos.length == 4) {
                    String nombreProducto = datos[0];
                    int cantidad = Integer.parseInt(datos[1]);
                    double precio = Double.parseDouble(datos[2]);
                    String categoria = datos[3];
                    inventario.add(new Producto(nombreProducto, categoria, precio, cantidad));
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al leer el archivo de inventario: " + e.getMessage(), "Error de Archivo", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error en el formato de los datos del archivo: " + e.getMessage(), "Error de Formato", JOptionPane.ERROR_MESSAGE);
        }
    }

    public ArrayList<Producto> listarProductosPorCategoria(String categoria) {
        //Lista para los productos que obtenemos del filtro
        ArrayList<Producto> productosFiltrados = new ArrayList<>();
        for (Producto producto : inventario) {
            if (producto.getCategoria().equalsIgnoreCase(categoria)) {
                productosFiltrados.add(producto);
            }
        }
        return productosFiltrados;
    }
}
