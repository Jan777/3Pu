package servidor.comunicacion;

import servidor.usuario.UsuarioPosicion;

import java.io.Serializable;
import java.util.Map;

public class Posiciones implements Serializable{
    private Map<String, UsuarioPosicion> posicionMap;

    public Map<String, UsuarioPosicion> getPosicionMap() {
        return posicionMap;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Posiciones that = (Posiciones) o;

        return posicionMap != null ? posicionMap.equals(that.posicionMap) : that.posicionMap == null;

    }

    @Override
    public int hashCode() {
        return posicionMap != null ? posicionMap.hashCode() : 0;
    }
}
