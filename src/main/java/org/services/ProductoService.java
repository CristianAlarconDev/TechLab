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
    public void  eliminarProducto(Producto producto)
    {
        productos.remove(producto);
    }
    public Vendible obtenerVendible(int idProducto)
    {
        if(idProducto <= 0){
            throw new CantidadInvalidaException("El id es invalido");
        }

        Vendible vendible = null;
        for (Producto producto: productos)
        {   /*delegar luego a producto la comparación*/
            if (producto.obtenerID()==idProducto){
                vendible=producto;
            }
        }
        if (vendible==null){
            throw new IdNoEncontradoException("No existe el producto con el id: "+idProducto);
        }
        return vendible;
    }
}
