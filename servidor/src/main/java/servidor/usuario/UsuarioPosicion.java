package servidor.usuario;

import main.java.mergame.individuos.personajes.Personaje;

import java.awt.*;
import java.io.Serializable;

public class UsuarioPosicion implements Serializable{
    private String usuario;
    private Personaje personaje;
    private int x;
    private int y;

    public UsuarioPosicion(Usuario usuario) {
        this.usuario = usuario.getUsuario();
        this.personaje = usuario.getPersonaje();
        this.x = 0;
        this.y = 0;
    }

    public UsuarioPosicion() {}

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setPersonaje(Personaje personaje) {
        this.personaje = personaje;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Personaje getPersonaje() {
        return this.personaje;
    }

    public String getUsuario() {
        return usuario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsuarioPosicion that = (UsuarioPosicion) o;

        if (x != that.x) return false;
        if (y != that.y) return false;
        if (usuario != null ? !usuario.equals(that.usuario) : that.usuario != null) return false;
        return personaje != null ? personaje.equals(that.personaje) : that.personaje == null;

    }

    @Override
    public int hashCode() {
        int result = usuario != null ? usuario.hashCode() : 0;
        result = 31 * result + (personaje != null ? personaje.hashCode() : 0);
        result = 31 * result + x;
        result = 31 * result + y;
        return result;
    }
}
