package servidor.comunicacion;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

public class Clazz {
    private static ObjectMapper mapper = new ObjectMapper();
    private static final String PACKAGE = "servidor.comunicacion";

    public static Object get(String mensaje) {
        Object o = null;
        o = mapper.convertValue(mensaje, Object.class);

        try {
            Class<?> aClass = mapper.readTree(mensaje).getClass();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return o;
    }
}
