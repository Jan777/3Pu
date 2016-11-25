package servidor.comunicacion;

import java.io.Serializable;
import java.util.List;
import java.util.Observable;

public class PersonajesConectados implements Serializable {
    List<String> listaPersonajes;

    public PersonajesConectados(List<String> nombresPersonajeConectado) {
        this.listaPersonajes = nombresPersonajeConectado;
    }

    public List<String> getListaPersonajes() {
        return listaPersonajes;
    }

    public void setListaPersonajes(List<String> listaPersonajes) {
        this.listaPersonajes = listaPersonajes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PersonajesConectados that = (PersonajesConectados) o;

        return listaPersonajes != null ? listaPersonajes.equals(that.listaPersonajes) : that.listaPersonajes == null;

    }

    @Override
    public int hashCode() {
        return listaPersonajes != null ? listaPersonajes.hashCode() : 0;
    }
}
