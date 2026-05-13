package org.models;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//esta clase aún no sé si debería ser como tal porque solo actua como generador y no tiene estado propio, es una clase wrapper.
public class Parser<T> {
    private final ObjectMapper mapper;

    public Parser() {
        this.mapper = new ObjectMapper();
    }

    public List<T> leerLista(String ruta, Class<T> clase) throws IOException {
        File archivo = new File(ruta);
        CollectionType tipoLista = mapper.getTypeFactory()
                .constructCollectionType(ArrayList.class, clase);
        return mapper.readValue(archivo, tipoLista);
    }
}
