package learning.models;

public class Producto {
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

        return this.precio = precio;
    }
}
