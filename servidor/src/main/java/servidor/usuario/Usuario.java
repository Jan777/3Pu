package servidor.usuario;

import main.java.mergame.individuos.personajes.Personaje;

import java.net.Socket;

public class Usuario {
    private String usuario;
    private Socket socket;
    private Personaje personaje;
    private UsuarioPosicion posicion;

    public Usuario(Socket socket, String usuario){
        this.socket = socket;
        this.usuario = usuario;
        this.posicion = new UsuarioPosicion();
    }

    public void setPersonaje(Personaje personaje){
        this.personaje = personaje;
    }

    public Personaje getPersonaje() {
        return personaje;
    }

    public Socket getSocket() {
        return socket;
    }

    public String getUsuario() {
        return usuario;
    }

    public UsuarioPosicion getPosicion() {
        return posicion;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setPosicion(UsuarioPosicion posicion) {
        this.posicion = posicion;
    }
}
