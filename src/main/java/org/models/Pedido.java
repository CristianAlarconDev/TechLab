package org.models;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private int id;
    private List<ItemPedido> items;
    public Pedido(int id) {
        this.id = id;
        this.items = new ArrayList<>();
    }
    public int obtenerId() {
        return id;
    }

    public int obtenerTotal(){
        int total = 0;
        for(ItemPedido item: items){
            total+=item.obtenerSubtotal();
        }
        return total;
    }
    public void agregarItem(ItemPedido item){
        items.add(item);
    }
    public void removerItem(ItemPedido item){
        items.remove(item);
    }
}
