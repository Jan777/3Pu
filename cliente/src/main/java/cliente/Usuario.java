package cliente;

import java.net.Socket;
import main.java.mergame.individuos.personajes.Personaje;

public class Usuario {
	private int mundo;
    private String usuario;
    private String pass;
	private Socket socket;
	private Personaje personaje;
	
	public Usuario(){
		
	}
	
	public Usuario(Socket socket){
		this.socket = socket;
	}
	
	public Usuario(Socket socket, int mundo, String usuario, String pass) {
		this.socket = socket;
    	this.mundo = mundo;
    	this.usuario = usuario;
    	this.pass = pass;
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

	public int getMundo() {
        return this.mundo;
    }

    public void setSala(int mundo) {
        this.mundo = mundo;
    }

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

}
