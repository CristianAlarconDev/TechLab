package techlab;

import techlab.models.MenuPrincipal;
import techlab.models.Parser;
import techlab.models.Producto;
import techlab.services.ProductoService;
import techlab.services.PedidoService;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ProductoService productoService = new ProductoService();
        PedidoService pedidoService = new PedidoService(productoService);
        Parser parser = new Parser();
        try {
            List<Producto> productosJson = parser.leerProductos("src/main/resources/productos.json");
            for (Producto p : productosJson) {
                productoService.agregarProducto(p);
            }
            System.out.println("Sistema iniciado: " + productosJson.size() + " productos cargados.");
        } catch (Exception e) {
            System.err.println("Aviso: No se detectó archivo inicial. Iniciando con inventario vacío.");
        }

        MenuPrincipal menu = new MenuPrincipal(productoService, pedidoService);
        menu.lanzar();
    }
}