package org.services;

import org.exceptions.IdNoEncontradoException;
import org.models.GeneradorID;
import org.models.Pedido;
import org.models.Vendible;
import org.models.ItemPedido;

public class PedidoService {
    private ProductoService productoService;
    private GeneradorID generador;
    private Pedido pedidoEnProceso;
    public PedidoService( ProductoService productoService)
    {
        this.generador = new GeneradorID();
        this.pedidoEnProceso = null;
        this.productoService = productoService;
    }
    public void iniciarPedido()
    {
        pedidoEnProceso = new Pedido(generador.obtenerId());

    }
    public void agregarItemAlPedido(int idProducto, int cantidad){
        if (pedidoEnProceso == null)
        {   return;
        }
        try {
            Vendible vendible = productoService.obtenerVendible(idProducto);
            ItemPedido nuevoItem = new ItemPedido(cantidad, vendible);
            pedidoEnProceso.agregarItem(nuevoItem);
            System.out.println("Agregado: " + vendible.obtenerNombre() + " x" + cantidad);
        }
        catch (Exception error)
            {
            System.err.println("Error al agregar el item al pedido." + error.getMessage());
            }
    }
    public void eliminarItemAlPedido(int idVendible){
        if (pedidoEnProceso == null)
        {   return;
        }
        try {
            pedidoEnProceso.removerItem(idVendible);
        } catch (IdNoEncontradoException e) {
            System.err.println(e.getMessage());
        }
    }
    public double obtenerTotal(){
        //validar luego con un pedido no iniciado exception al igual que todos los null en esta clase
        if (pedidoEnProceso == null)
        {   return 0;
        }
        return pedidoEnProceso.obtenerTotal();
    }
    public void finalizarPedido(){
        pedidoEnProceso = null;
    }

}
