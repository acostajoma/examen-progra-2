/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen;

import java.util.ArrayList;

/**
 *
 * @author joseacosta
 */
public class GestorInventario {
    private ArrayList<Producto> inventario;
    private String nombre;

    public GestorInventario(ArrayList<Producto> inventario, String nombre) {
        this.inventario = inventario;
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
  
    public void agregarProducto(Producto producto){
        inventario.add(producto);
        // TO DO: guardar en el archivo
    }
    
    public boolean eliminarProducto(String nombre){
        boolean ok 
        // POR IMPLEMENTAR
    }
    
    public void modificarPro
}
