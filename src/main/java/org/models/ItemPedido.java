package org.models;

import org.exceptions.CantidadInvalidaException;

public class ItemPedido {
    private int ID;
    private int cantidad;
    private Vendible item;

    public ItemPedido( int cantidad, Vendible item)
    {
        if(cantidad<=0){
            throw new CantidadInvalidaException("Cantidad invalida, debe ser mayor a 0.");
        }
        this.ID = item.obtenerID();
        this.cantidad = cantidad;
        this.item = item;
    }
    public String mostrarDescripcion()
    {
        return String.format("| %-5d | %-10s | %-5d |",
                ID, item.obtenerNombre(),cantidad);
    }
    public double obtenerSubtotal()
    {
        return cantidad* item.obtenerPrecio();
    }
    public boolean tieneId(int idProducto)
    {
        return ID == idProducto;
    }
}
