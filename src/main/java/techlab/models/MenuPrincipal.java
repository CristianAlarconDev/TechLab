package techlab.models;


import techlab.services.ProductoService;
import techlab.services.PedidoService;

import java.util.List;
import java.util.Scanner;

public class MenuPrincipal {
    private final ProductoService productoService;
    private final PedidoService pedidoService;
    private final Scanner scanner;
    private boolean seguirMostrando;
    private final ConsolaUI consolaUI;
    private final List<String> opciones;

    public MenuPrincipal(ProductoService ps, PedidoService pedS) {
        this.productoService = ps;
        this.pedidoService = pedS;
        this.scanner = new Scanner(System.in);
        this.seguirMostrando = true;
        this.consolaUI = new ConsolaUI();
        this.opciones = List.of(
                "Agregar producto", "Listar productos", "Buscar producto", "Actualizar producto",
                "Eliminar producto", "Crear un pedido", "Listar pedidos (Resumen)", "Salir"
        );
    }

    public void lanzar() {
        while (seguirMostrando) {
            mostrarOpciones();
            int opcion = capturarEntero("Elija una opción");
            procesarOpcion(opcion);
        }
    }

    private void mostrarOpciones() {
        consolaUI.mostrarMensaje("\n=== SISTEMA DE GESTIÓN - TECHLAB ===");
        for (int i = 0; i < opciones.size(); i++) {
            consolaUI.mostrarMensaje((i + 1) + ") " + opciones.get(i));
        }
        consolaUI.mostrarMensaje("Elija una opción: ");
    }

    private void procesarOpcion(int opcion) {
        switch (opcion) {
            case 1 -> subMenuAgregarProducto();
            case 2 -> productoService.mostrarProductos(new ConsolaUI());
            case 3 -> subMenuBuscarProducto();
            case 4 -> subMenuActualizarProducto();
            case 5 -> subMenuEliminarProducto();
            case 6 -> subMenuIniciarPedido();
            case 7 -> pedidoService.mostrarHistorialDePedidos(new ConsolaUI());
            case 8 -> {
                consolaUI.mostrarMensaje("Cerrando sistema...");
                seguirMostrando = false;
            }
            default -> consolaUI.mostrarMensaje("Opción no válida.");
        }
    }

    private void subMenuAgregarProducto() {
        consolaUI.mostrarMensaje("\n--- ALTA DE PRODUCTO ---");
        String nombre = capturarTexto("Nombre");
        String descripcion = capturarTexto("Descripción");
        int precio = capturarEntero("Precio");
        int id = capturarEntero("ID");
        int stock = capturarEntero("Stock");
        try {
            productoService.agregarProducto(new Producto(nombre, descripcion, precio, id, stock));
            consolaUI.mostrarMensaje("Éxito: Producto registrado.");
        } catch (Exception e) {
            consolaUI.mostrarMensaje("No se pudo agregar: " + e.getMessage());
        }
    }
    //desacoplar luego del print
    private void subMenuBuscarProducto() {
        consolaUI.mostrarMensaje("\n--- BÚSQUEDA DE PRODUCTO ---");
        consolaUI.mostrarMensaje("1. Buscar por ID\n2. Buscar por Nombre");
        int modo = capturarEntero("Seleccione una opción");
        try {
            switch (modo) {
                case 1 -> {
                    int id = capturarEntero("Ingrese ID a buscar");
                    consolaUI.mostrarMensaje("Producto encontrado: " + productoService.mostrarInformacionPorID(id));
                }
                case 2 -> {
                    String nombre = capturarTexto("Ingrese nombre del producto");
                    consolaUI.mostrarMensaje("Producto encontrado: " + productoService.buscarPorNombre(nombre));
                }
                default -> consolaUI.mostrarMensaje("Opción de búsqueda no válida.");
            }
        } catch (Exception e) {
            consolaUI.mostrarMensaje("Error: " + e.getMessage());
        }
    }
    private void subMenuActualizarProducto() {
        int id = capturarEntero("Ingrese el ID del producto a modificar");
        try {
            consolaUI.mostrarMensaje("¿Qué desea modificar? (1: Precio, 2: Stock, 0: Cancelar)");
            int opt = capturarEntero("Opción");
            String resultado;
            switch (opt) {
                case 1 -> {
                    int nuevoPrecio = capturarEntero("Ingrese el nuevo Precio");
                    resultado = productoService.actualizarPrecioDe(id, nuevoPrecio);
                    consolaUI.mostrarMensaje(resultado);
                }
                case 2 -> {
                    int cantidadStock = capturarEntero("Ingrese el nuevo Stock");
                    resultado = productoService.actualizarStockDe(id, cantidadStock);
                    consolaUI.mostrarMensaje(resultado);
                }
                case 0 -> consolaUI.mostrarMensaje("Se ha cancelado la actualización correctamente.");
                default -> consolaUI.mostrarMensaje("Opción no válida.");
            }

        } catch (Exception e) {
            consolaUI.mostrarMensaje("Error: " + e.getMessage());
        }
    }
    private void subMenuEliminarProducto() {
        int id = capturarEntero("Ingrese ID a eliminar");
        productoService.eliminarProducto(id, consolaUI);
    }
    private void subMenuIniciarPedido() {
        pedidoService.iniciarPedido();
        boolean agregando = true;
        while (agregando) {
            int id = capturarEntero("ID del producto a agregar (0 para terminar)");
            if (noContinuaElPedido(id)) {
                agregando = false;
            } else {
                int cant = capturarEntero("Cantidad");
                pedidoService.agregarItemAlPedido(id, cant, this.consolaUI);
            }
        }
        consolaUI.mostrarMensaje("\nTotal del pedido acumulado: $" + pedidoService.obtenerTotal());
        String respuesta = capturarTexto("¿Desea (C)onfirmar la compra o (A)nular el pedido?");
        if (respuesta.equalsIgnoreCase("C")) {
            pedidoService.finalizarPedido(this.consolaUI);
        } else {
            pedidoService.cancelarPedido();
            consolaUI.mostrarMensaje("Pedido cancelado. El inventario no ha sido modificado.");
        }
    }
    private boolean noContinuaElPedido(int opcion) {
        return opcion==0;
    }
    private int capturarEntero(String campo) {
        while (true) {
            try {
                consolaUI.mostrarMensaje(campo + ": ");
                String input = scanner.nextLine();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                consolaUI.mostrarMensaje("Error: Debe ingresar un número entero válido.");
            }
        }
    }
    private String capturarTexto(String campo) {
        System.out.print(campo + ": ");
        return scanner.nextLine().trim();
    }

}