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

##  Estructura
- `services`: Lógica de negocio (ProductoService, PedidoService).
- `models`: Entidades y utilidades (Producto, Pedido, GeneradorID, Parser).
- `ui`: Interfaz de usuario por consola (MenuPrincipal, ConsolaUI).

[Mi Portafolio](https://cristian-alarcon-dev.netlify.app/)