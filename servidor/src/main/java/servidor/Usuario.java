package servidor;

import java.net.Socket;

import main.java.mergame.individuos.personajes.Personaje;

public class Usuario {
	private Socket socket;
	private Personaje personaje;
	
	public Usuario(Socket socket){
		this.socket = socket;
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

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
