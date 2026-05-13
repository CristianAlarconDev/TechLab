package techlab.models;

public class ConsolaUI {
    public void mostrarCabecera() {
        System.out.println("\n================ LISTADO DE PRODUCTOS ================");
        System.out.println("| ID    | Nombre               |    Precio    |    Descripción |   Stock   |");
        System.out.println("------------------------------------------------------");
    }
    public void mostrarPie() {
        System.out.println("========================================================\n");
    }
    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
    public void mostrar(Producto p) {
        System.out.println(p.mostrarInformacion());
    }

}
