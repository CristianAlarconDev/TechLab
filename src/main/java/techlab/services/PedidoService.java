package techlab.services;

import techlab.exceptions.IdNoEncontradoException;
import techlab.exceptions.PedidoNoIniciadoException;
import techlab.models.*;

import java.util.ArrayList;
import java.util.List;

public class PedidoService {
    private ProductoService productoService;
    private GeneradorID generador;
    private Pedido pedidoEnProceso;
    private List<Pedido> pedidosHechos;
    public PedidoService( ProductoService productoService)
    {
        this.generador = new GeneradorID();
        this.pedidoEnProceso = null;
        this.productoService = productoService;
        this.pedidosHechos = new ArrayList<Pedido>();
    }
    public void iniciarPedido()
    {
        pedidoEnProceso = new Pedido(generador.obtenerId());

    }
    public void agregarItemAlPedido(int idProducto, int cantidad, ConsolaUI ui){
        checkPedidoEnProceso();
        try {
            if(productoService.hayStockDeProducto(idProducto, cantidad)){
                Vendible vendible = productoService.obtenerVendible(idProducto);
                ItemPedido nuevoItem = new ItemPedido(cantidad, vendible);
                pedidoEnProceso.agregarItem(nuevoItem);
                ui.mostrarMensaje("Agregado: " + vendible.obtenerNombre() + " x" + cantidad);
            }

        }
        catch (Exception error)
            {
            ui.mostrarMensaje("Error al agregar el item al pedido." + error.getMessage());
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
    public void cancelarPedido(){
        checkPedidoEnProceso();
        this.pedidoEnProceso=null;
    }
    public void finalizarPedido(ConsolaUI ui){
        checkPedidoEnProceso();
        try {
            for (ItemPedido item : pedidoEnProceso.obtenerItems()) {
                productoService.descontarStock(item.obtenerId(), item.obtenerCantidad());
            }
            ui.mostrarMensaje("Pedido finalizado con éxito.");
            ui.mostrarMensaje(pedidoEnProceso.obtenerResumen());
            pedidosHechos.add(pedidoEnProceso);
            pedidoEnProceso = null;
        } catch (Exception e) {
            ui.mostrarMensaje("Error crítico al procesar el pedido: " + e.getMessage());
        }
    }
    public void mostrarHistorialDePedidos(ConsolaUI ui) {
        if (pedidosHechos.isEmpty()) {
            ui.mostrarMensaje("Sin historial de pedidos aún.");
            return;
        }
        ui.mostrarMensaje("--- HISTORIAL DE VENTAS ---");
        for (Pedido pedido : pedidosHechos) {
            ui.mostrarMensaje(pedido.obtenerResumen());
        }
    }
    private void checkPedidoEnProceso(){
        if(pedidoEnProceso == null)
        {   throw new PedidoNoIniciadoException("Pedido no iniciado.");
        }
    }

}
