package techlab.models;


import techlab.services.ProductoService;
import techlab.services.PedidoService;

import java.util.Scanner;

public class MenuPrincipal {
    private final ProductoService productoService;
    private final PedidoService pedidoService;
    private final Scanner scanner;
    private boolean seguirMostrando;

    public MenuPrincipal(ProductoService ps, PedidoService pedS) {
        this.productoService = ps;
        this.pedidoService = pedS;
        this.scanner = new Scanner(System.in);
        this.seguirMostrando = true;
    }

    public void lanzar() {
        while (seguirMostrando) {
            mostrarOpciones();
            int opcion = capturarEntero();
            procesarOpcion(opcion);
        }
    }

    private void mostrarOpciones() {
        System.out.println("\n=== SISTEMA DE GESTIÓN - TECHLAB ===");
        System.out.println("1) Agregar producto");
        System.out.println("2) Listar productos");
        System.out.println("3) Buscar producto" );
        System.out.println("4) Actualizar producto" );
        System.out.println("5) Eliminar producto");
        System.out.println("6) Crear un pedido");
        System.out.println("7) Listar pedidos (Resumen)");
        System.out.println("8) Salir");
        System.out.print("Elija una opción: ");
    }

    private void procesarOpcion(int opcion) {
        switch (opcion) {
            case 1 -> subMenuAgregarProducto();
            case 2 -> productoService.mostrarProductos();
            case 3 -> subMenuBuscarProducto();
            case 4 -> subMenuActualizarProducto();
            case 5 -> subMenuEliminarProducto();
            case 6 -> subMenuIniciarPedido();
            case 7 -> pedidoService.mostrarHistorialDePedidos();
            case 8 -> {
                System.out.println("Cerrando sistema...");
                seguirMostrando = false;
            }
            default -> System.out.println("Opción no válida.");
        }
    }
    //desacoplar luego del print
    private void subMenuAgregarProducto() {
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Descripción: ");
        String descripcion = scanner.nextLine();
        System.out.print("Precio: ");
        int precio = scanner.nextInt();
        System.out.print("ID: ");
        int id = scanner.nextInt();
        System.out.print("Stock: ");
        int stock = scanner.nextInt();
        productoService.agregarProducto(new Producto(nombre, descripcion, precio, id, stock));
    }
    //desacoplar luego del print
    private void subMenuBuscarProducto() {
        System.out.println("\n--- BÚSQUEDA DE PRODUCTO ---");
        System.out.println("1. Buscar por ID");
        System.out.println("2. Buscar por Nombre");
        System.out.print("Seleccione una opción: ");
        int modo = capturarEntero();
        try {
            switch (modo) {
                case 1 -> {
                    System.out.print("Ingrese ID a buscar: ");
                    int id = capturarEntero();
                    System.out.println("Producto encontrado: " + productoService.mostrarInformacionPorID(id));
                }
                case 2 -> {
                    System.out.print("Ingrese nombre del producto: ");
                    String nombre = scanner.nextLine();
                    System.out.println("Producto encontrado: " + productoService.buscarPorNombre(nombre));
                }
                default -> System.out.println("Opción de búsqueda no válida.");
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
    private void subMenuActualizarProducto() {
        System.out.print("Ingrese el ID del producto a modificar: ");
        int id = capturarEntero();
        try {
            System.out.println("¿Qué desea modificar? (1: Precio, 2: Stock, 0: Cancelar)");
            int opt = capturarEntero();
            String resultado = "";
            switch (opt) {
                case 1 -> {
                    System.out.print("Ingrese el nuevo Precio: ");
                    int nuevoPrecio = capturarEntero();
                    resultado = productoService.actualizarPrecioDe(id, nuevoPrecio);
                    System.out.println(resultado);
                }
                case 2 -> {
                    System.out.print("Ingrese la cantidad: ");
                    int cantidadStock = capturarEntero();
                    resultado = productoService.actualizarStockDe(id, cantidadStock);
                    System.out.println(resultado);
                }
                case 0 -> System.out.println("Se ha cancelado la actualización correctamente.");
                default -> System.out.println("Opción no válida.");
            }

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
    private void subMenuEliminarProducto() {
        System.out.print("Ingrese ID a eliminar: ");
        int id = scanner.nextInt();
        productoService.eliminarProducto(id);
    }
    private void subMenuIniciarPedido() {
        pedidoService.iniciarPedido();
        boolean agregando = true;

        while (agregando) {
            System.out.print("ID del producto a agregar (0 para terminar): ");
            int id = scanner.nextInt();

            if (noContinuaElPedido(id)) {
                agregando = false;
            } else {
                System.out.print("Cantidad: ");
                int cant = scanner.nextInt();
                pedidoService.agregarItemAlPedido(id, cant);
            }
        }

        System.out.println("\nTotal del pedido acumulado: $" + pedidoService.obtenerTotal());
        System.out.print("¿Desea (C)onfirmar la compra o (A)nular el pedido? ");
        String respuesta = scanner.next();

        if (respuesta.equalsIgnoreCase("C")) {
            pedidoService.finalizarPedido();
        } else {
            pedidoService.cancelarPedido();
            System.out.println("Pedido cancelado. El inventario no ha sido modificado.");
        }
    }
    private boolean noContinuaElPedido(int opcion) {
        return opcion==0;
    }
    private int capturarEntero() {
        try {
            int num = scanner.nextInt();
            scanner.nextLine();
            return num;
        } catch (Exception e) {
            scanner.nextLine();
            return -1;
        }
    }

}