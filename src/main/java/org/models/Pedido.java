package org.models;

import org.exceptions.IdNoEncontradoException;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private int id;
    private List<ItemPedido> items;
    public Pedido(int id) {
        this.id = id;
        this.items = new ArrayList<>();
    }

    public double obtenerTotal(){
        double total = 0;
        for(ItemPedido item: items){
            total+=item.obtenerSubtotal();
        }
        return total;
    }
    public void agregarItem(ItemPedido item){
        items.add(item);
    }
    public void removerItem(int idProducto){
        ItemPedido item = obtenerItem(idProducto);
        items.remove(item);
    }
    public String obtenerResumen() {
        StringBuilder resumen = new StringBuilder();
        resumen.append("--- RESUMEN DE PEDIDO N° ").append(id).append(" ---\n");
        for (ItemPedido item : items) {
            resumen.append(item.mostrarDescripcion()).append("\n");
        }
        resumen.append("---------------------------------\n");
        resumen.append(String.format("TOTAL A PAGAR: $%.2f", obtenerTotal()));
        return resumen.toString();
    }
    private ItemPedido obtenerItem(int idProducto){
        for(ItemPedido item: items){
            if (item.tieneId(idProducto)){
                return item;
            }
        }
        throw new IdNoEncontradoException("No se encontró id a remover del pedido." + idProducto);
    }
}
