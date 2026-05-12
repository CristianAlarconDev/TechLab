package org.services;

import org.exceptions.IdNoEncontradoException;
import org.exceptions.PedidoNoIniciadoException;
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
        checkPedidoEnProceso();
        try {
            if(productoService.hayStockDeProducto(idProducto, cantidad)){
                Vendible vendible = productoService.obtenerVendible(idProducto);
                ItemPedido nuevoItem = new ItemPedido(cantidad, vendible);
                pedidoEnProceso.agregarItem(nuevoItem);
                System.out.println("Agregado: " + vendible.obtenerNombre() + " x" + cantidad);
            }

        }
        catch (Exception error)
            {
            System.err.println("Error al agregar el item al pedido." + error.getMessage());
            }
    }
    public void eliminarItemAlPedido(int idVendible){
        checkPedidoEnProceso();
        try {
            pedidoEnProceso.removerItem(idVendible);
        } catch (IdNoEncontradoException e) {
            System.err.println(e.getMessage());
        }
    }
    public double obtenerTotal(){
        checkPedidoEnProceso();
        return pedidoEnProceso.obtenerTotal();
    }
    public void finalizarPedido(){
        checkPedidoEnProceso();
        try {
            for (ItemPedido item : pedidoEnProceso.obtenerItems()) {
                productoService.descontarStock(item.obtenerId(), item.obtenerCantidad());
            }
            System.out.println("Pedido finalizado con éxito.");
            System.out.println(pedidoEnProceso.obtenerResumen());

            pedidoEnProceso = null;
        } catch (Exception e) {
            System.err.println("Error crítico al procesar el pedido: " + e.getMessage());
        }
    }
    private void checkPedidoEnProceso(){
        if(pedidoEnProceso == null)
        {   throw new PedidoNoIniciadoException("Pedido no iniciado.");
        }
    }

}
