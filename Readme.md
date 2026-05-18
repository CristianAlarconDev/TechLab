# TechLab - Gestión de Inventario

Sistema de gestión de productos y pedidos desarrollado en Java. Este proyecto aplica principios de Programación Orientada a Objetos, principios de diseño y persistencia mediante archivos JSON.

##  Funcionalidades
- **Inventario**: ABM (Alta, Baja, Modificación) de productos con validación de stock y precio.
- **Pedidos**: Creación de pedidos con cálculo automático de totales y actualización de stock.
- **Persistencia**: Carga de datos iniciales desde `productos.json`.
- **IDs Automáticos**: Generación de identificadores únicos sincronizados con el inventario existente.

##  Tecnologías
- Java 21+
- Jackson (Procesamiento de JSON)

##  Requisitos
Antes de proceder con la compilación y ejecución se debe tener las siguientes herramientas instaladas y configuradas:
- **Java JDK 21** o superior.
- **Apache Maven 3.x** o superior.

##  Estructura
- `services`: Lógica de negocio (ProductoService, PedidoService).
- `models`: Entidades y utilidades (Producto, Pedido, GeneradorID, Parser).
- `ui`: Interfaz de usuario por consola (MenuPrincipal, ConsolaUI).

##  Compilación y Ejecución

Para compilar y correr el proyecto desde la terminal utilizando el **JDK** y **Maven**, en la raíz del proyecto ejecuta los siguientes comandos:

1. **Limpiar el entorno y compilar:**
   ```bash
   mvn clean compile
    ```
2. **Ejecutar:**
   ``` bash
   mvn exec:java
   ```
[ Informe Técnico del Proyecto (PDF)](docs/Informe-Implementacion-TechLab.pdf)
[Mi Portafolio](https://cristian-alarcon-developer.netlify.app/)