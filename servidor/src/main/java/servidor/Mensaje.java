package servidor;

import java.net.Socket;
import java.util.ArrayList;

import main.java.mergame.individuos.personajes.Personaje;

public class Mensaje {
	protected Personaje personajeDelUsuario;
	
	public Mensaje(){
		
	}
	protected Mensaje(Personaje personaje){
		this.personajeDelUsuario = personaje;
	}
	
	public Personaje getPersonajeDelUsuario() {
		return personajeDelUsuario;
	}

	public void setPersonajeDelUsuario(Personaje personajeDelUsuario) {
		this.personajeDelUsuario = personajeDelUsuario;
	}

	protected void ejecutarMensaje(ArrayList<UsuarioEnServidor> arrayUsuario){
	}
	
}
