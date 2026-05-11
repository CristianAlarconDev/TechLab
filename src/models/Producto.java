package models;

public class Producto {
    private String nombre;
    private String descripcion;
    private int precio;
    private int ID;
    public Producto(String nombre, String descripcion, int precio, int ID) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.ID = ID;
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

}
