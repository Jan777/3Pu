package servidor.comunicacion;

import java.io.Serializable;

public class Login implements Serializable {
    private String usuario;
    private int password;
    private int mundo;

    public Login(String usuario, String mundo) {
        this.usuario = usuario;
        this.mundo = Integer.parseInt(mundo);
    }

    public Login() {
        
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Login login = (Login) o;

        if (this.password != login.password) return false;
        if (this.mundo != login.mundo) return false;
        return this.usuario != null ? this.usuario.equals(login.usuario) : login.usuario == null;

    }

    @Override
    public int hashCode() {
        int result = this.usuario != null ? this.usuario.hashCode() : 0;
        result = 31 * result + this.password;
        result = 31 * result + this.mundo;
        return result;
    }

    public String getUsuario() {
        return this.usuario;
    }

    public int getPassword() {
        return this.password;
    }

    public int getMundo() {
        return this.mundo;
    }
}