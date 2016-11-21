package cliente;

import java.net.Socket;
import java.util.ArrayList;

import main.java.mergame.individuos.personajes.Personaje;

public class Mensaje {
	protected Usuario usuario;
	protected Personaje personajeDelUsuario;
	
	public Mensaje(){
		
	}
	protected Mensaje(Usuario usuario){
		this.usuario = usuario;
	}
	
	protected Mensaje(Personaje personaje){
		this.personajeDelUsuario = personaje;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Personaje getPersonajeDelUsuario() {
		return personajeDelUsuario;
	}

	public void setPersonajeDelUsuario(Personaje personajeDelUsuario) {
		this.personajeDelUsuario = personajeDelUsuario;
	}

	protected void ejecutarMensaje(ArrayList<Usuario> arrayUsuario){
	}
	
}
