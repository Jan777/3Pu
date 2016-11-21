package cliente;

import java.net.Socket;
import java.util.*;

import main.java.mergame.individuos.personajes.Personaje;


public class MensajeCrear extends Mensaje {
	
	public MensajeCrear(Personaje personaje) {
		super(personaje);
	}

	@Override
	public void ejecutarMensaje(ArrayList<Usuario> arrayUsuario) {
		for(Usuario u : arrayUsuario){
			if(u.getSocket() == usuario.getSocket()){
				 u.setPersonaje(usuario.getPersonaje());
				}
			}
	}

}
