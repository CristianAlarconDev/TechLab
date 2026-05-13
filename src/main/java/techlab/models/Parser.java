package techlab.models;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//esta clase aún no sé si debería ser como tal porque solo actua como generador y no tiene estado propio, es una clase wrapper.
public class Parser {
    private final ObjectMapper mapper;
    public Parser() {
        this.mapper = new ObjectMapper();
    }
    public List<Producto> leerProductos(String ruta) throws IOException {
        List<Producto> listaFinal = new ArrayList<>();
        File archivo = new File(ruta);

        JsonNode rootNode = mapper.readTree(archivo);


        for (JsonNode node : rootNode) {
            int id = node.get("id").asInt();
            String nombre = node.get("nombre").asText();
            String descripcion = node.get("descripcion").asText();
            int precio = node.get("precio").asInt();
            int stock = node.get("stock").asInt();

            Producto p = new Producto(nombre, descripcion, precio, id, stock);
            listaFinal.add(p);
        }

        return listaFinal;
    }
}
