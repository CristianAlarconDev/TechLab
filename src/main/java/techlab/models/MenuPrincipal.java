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
        System.out.println("4) Eliminar producto");
        System.out.println("5) Crear un pedido");
        System.out.println("6) Listar pedidos (Resumen)");
        System.out.println("7) Salir");
        System.out.print("Elija una opción: ");
    }

    private void procesarOpcion(int opcion) {
        switch (opcion) {
            case 1 -> subMenuAgregarProducto();
            case 2 -> productoService.mostrarProductos();
            case 3 -> subMenuBuscarProducto();
            case 4 -> subMenuEliminarProducto();
            case 5 -> subMenuIniciarPedido();
            case 6 -> System.out.println("todavia no impl porque pedido service no enlista todos los pedidos hechos");
            case 7 -> {
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
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese ID a buscar: ");
        int id = scanner.nextInt();
        try {
            System.out.println("Producto encontrado: " + productoService.mostrarInformacionPorID(id));

        } catch (Exception e) {
            System.err.println(e.getMessage());
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

        System.out.print("¿Confirmar pedido y descontar stock? (S/N): ");
        if (scanner.next().equalsIgnoreCase("S")) {
            pedidoService.finalizarPedido();
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