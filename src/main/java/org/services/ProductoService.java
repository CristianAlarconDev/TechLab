package org.services;

import org.exceptions.CantidadInvalidaException;
import org.exceptions.IdNoEncontradoException;
import org.models.Producto;
import org.models.Vendible;

import java.util.ArrayList;
import java.util.List;

public class ProductoService {
    private List<Producto> productos;
    public ProductoService()
    {
        productos = new ArrayList<Producto>();
    }
    public void agregarProducto(Producto producto)
    {
        productos.add(producto);
    }
    public void  eliminarProducto(int idProducto) {
        /*luego llevar los print a una clase o delegarlo*/
        try {
            Producto producto = obtenerProducto(idProducto);
            productos.remove(producto);
            System.out.println("Éxito: El producto ha sido eliminado del sistema.");

        } catch (IdNoEncontradoException error) {
            System.out.println("Error: " + error.getMessage());
        }
    }
    public Vendible obtenerVendible(int idProducto)
    {   //recordar que quien use vendible debe usar try-catch para el manejo
        return obtenerProducto(idProducto);
    }
    private Producto obtenerProducto(int idProducto)
    {
        if(idProducto <= 0){
            throw new IdNoEncontradoException("El id es invalido");
        }
        for(Producto producto: productos){
            if (producto.poseeElId(idProducto)){
                return  producto;
            }
        }
        throw new IdNoEncontradoException("No existe el producto con ID: " + idProducto);
    }
   public boolean hayStockDeProducto(int idProducto, int cantidad)
    {
        Producto producto = obtenerProducto(idProducto);
        return producto.tieneStockPara(cantidad);
    }
    public void descontarStock(int idProducto, int cantidad)
    {
        Producto producto = obtenerProducto(idProducto);
        producto.reducirStock(cantidad);
    }
}
