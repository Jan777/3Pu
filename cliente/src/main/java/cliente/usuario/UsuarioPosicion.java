package cliente.usuario;

import java.awt.*;
import java.io.Serializable;

/**
 * Created by gparis on 11/23/16.
 */
public class UsuarioPosicion implements Serializable{
    private Point point;
    private String usuario;

    public UsuarioPosicion() {
        this.point = new Point(0, 0);
    }

    public void agregarPunto(int x, int y) {
        this.point.setLocation(x, y);
    }

    public String getUsuario() {
        return usuario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsuarioPosicion that = (UsuarioPosicion) o;

        if (point != null ? !point.equals(that.point) : that.point != null) return false;
        return usuario != null ? usuario.equals(that.usuario) : that.usuario == null;

    }

    @Override
    public int hashCode() {
        int result = point != null ? point.hashCode() : 0;
        result = 31 * result + (usuario != null ? usuario.hashCode() : 0);
        return result;
    }
}
