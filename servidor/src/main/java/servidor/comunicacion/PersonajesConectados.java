package servidor.comunicacion;

import servidor.usuario.UsuarioPosicion;

import java.io.Serializable;
import java.util.List;

public class PersonajesConectados implements Serializable {
    List<String> listaPersonajes;
    List<UsuarioPosicion> posicionPersonajes;

    public PersonajesConectados() {
    }

    public PersonajesConectados(List<UsuarioPosicion> posicionPersonajes) {
        this.posicionPersonajes = posicionPersonajes;
    }

    public List<String> getListaPersonajes() {
        return listaPersonajes;
    }

    public void setListaPersonajes(List<String> listaPersonajes) {
        this.listaPersonajes = listaPersonajes;
    }

    public List<UsuarioPosicion> getPosicionPersonajes() {
        return posicionPersonajes;
    }

    public void setPosicionPersonajes(List<UsuarioPosicion> posicionPersonajes) {
        this.posicionPersonajes = posicionPersonajes;
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
