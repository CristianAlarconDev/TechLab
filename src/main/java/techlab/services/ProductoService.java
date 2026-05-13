package techlab.services;

import techlab.exceptions.IdNoEncontradoException;
import techlab.models.ConsolaUI;
import techlab.models.GeneradorID;
import techlab.models.Producto;
import techlab.models.Vendible;

import java.util.ArrayList;
import java.util.List;

public class ProductoService {
    private List<Producto> productos;
    private GeneradorID generador;
    public ProductoService()
    {
        productos = new ArrayList<Producto>();
        this.generador = new GeneradorID();
    }
    public void agregarProducto(Producto p) {
        generador.sincronizarSiEsMayor(p.obtenerID());
        productos.add(p);
    }
    public void agregarProducto(String nombre, String desc, int precio, int stock) {
        int nuevoId = generador.obtenerId();
        Producto nuevo = new Producto(nombre, desc, precio, nuevoId, stock);
        productos.add(nuevo);
    }
    public void  eliminarProducto(int idProducto, ConsolaUI ui) {
        /*luego llevar los print a una clase o delegarlo*/
        try {
            Producto producto = obtenerProducto(idProducto);
            productos.remove(producto);
            ui.mostrarMensaje("Éxito: El producto ha sido eliminado del sistema.");

        } catch (IdNoEncontradoException error) {
            ui.mostrarMensaje("Error: " + error.getMessage());
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
    public String buscarPorNombre(String nombre) throws Exception {
        String nombreParseado = nombre.trim();
        for (Producto producto : productos) {
            if (producto.tieneNombre(nombreParseado)) {
                return producto.mostrarInformacion();
            }
        }
        throw new Exception("No se encontró ningún producto con el nombre: " + nombre);
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
    public void mostrarProductos(ConsolaUI ui) {
        if (productos.isEmpty()) {
            ui.mostrarMensaje("El inventario está vacío.");
            return;
        }
        ui.mostrarCabecera();
        for (Producto producto : productos) {
            ui.mostrar(producto);
        }
    }
}
