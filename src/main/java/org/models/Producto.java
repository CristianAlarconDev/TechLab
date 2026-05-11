package org.models;
import org.exceptions.PrecioInvalidoException;
import org.exceptions.StockInvalidoException;

public class Producto implements Vendible {
    private String nombre;
    private String descripcion;
    private int precio;
    private int ID;
    private int stock;

    public Producto(String nombre, String descripcion, int precio, int ID, int stock) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.ID = ID;
        this.stock = stock;
    }
    public int obtenerID() {
        return ID;
    }
    public String obtenerNombre() {
        return nombre;
    }
    public String obtenerDescripcion() {
        return descripcion;
    }
    public int obtenerPrecio() {
        return precio;
    }
    public int actualizarPrecio(int precio) {
        if(precio<0){
            throw new PrecioInvalidoException("Precio invalido");
        }
        return this.precio = precio;
    }
     public void aumentarStock(int stock) {
        if(stock<0){
            throw new StockInvalidoException("Cantidad invalida a aumentar");
        }
        this.stock += stock;
     }
     public void reducirStock(int stock) {
        if(stock<0){
            throw new StockInvalidoException("Precio invalida a reducir");
        }
         if (stock > this.stock) {
             throw new StockInvalidoException("Stock insuficiente. Disponible: " + this.stock);
         }
        this.stock -= stock;
     }
     public String mostrarInformacion(){
         return String.format("| %-5d | %-10s | %-10d | %-10s | %-4s |",
                 ID, obtenerNombre(), obtenerPrecio(), obtenerDescripcion(), stock);
     }
}
