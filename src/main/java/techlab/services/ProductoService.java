package techlab.services;

import techlab.exceptions.IdNoEncontradoException;
import techlab.models.Producto;
import techlab.models.Vendible;

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
    public String mostrarInformacionPorID(int idProducto){
        return obtenerProducto(idProducto).mostrarInformacion();
    }
    public String actualizarPrecioDe(int idProducto, int precio)
    {
        Producto producto = obtenerProducto(idProducto);
        producto.actualizarPrecio(precio);
        return mostrarInformacionPorID(idProducto);
    }
    public String actualizarStockDe(int idProducto, int stock){
        Producto producto = obtenerProducto(idProducto);
        producto.cambiarStock(stock);
        return mostrarInformacionPorID(idProducto);
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
    public void mostrarProductos() {
        if (productos.isEmpty()) {
            System.out.println("El inventario está vacío.");
            return;
        }
        System.out.println("=============================================================================");
        System.out.println("| ID    | Nombre     | Precio     | Descripción          | Stock |");
        System.out.println("=============================================================================");
        for (Producto producto : productos) {
            System.out.println(producto.mostrarInformacion());
        }
        System.out.println("=============================================================================");
    }
}
