package cliente;

import main.java.mergame.individuos.personajes.Personaje;

import java.net.Socket;

public class Usuario {
    private Socket socket;
    private Personaje personaje;

    public Usuario(Socket socket) {
        this.socket = socket;
    }

    public void setPersonaje(Personaje personaje) {
        this.personaje = personaje;
    }

    public Personaje getPersonaje() {
        return this.personaje;
    }

    public Socket getSocket() {
        return this.socket;
    }

}
