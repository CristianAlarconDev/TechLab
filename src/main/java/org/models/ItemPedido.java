package org.models;

public class ItemPedido {
    private int ID;
    private int cantidad;
    private Vendible item;

    public ItemPedido( int cantidad, Vendible item)
    {
        this.ID = item.obtenerID();
        this.cantidad = cantidad;
        this.item = item;
    }
    public String mostrarDescripcion()
    {
        return String.format("| %-5d | %-10s | %-5d |",
                ID, item.obtenerNombre(),cantidad);
    }
    public int obtenerSubtotal()
    {
        return cantidad* item.obtenerPrecio();
    }
}
