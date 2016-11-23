package servidor;

import java.io.Serializable;
import java.util.List;
import java.util.Observable;

public class MensajeListaDePersonajesConectados implements Serializable {
	List<String> listaPersonajes;

	public MensajeListaDePersonajesConectados() {

	}

	public MensajeListaDePersonajesConectados(List<String> nombresPersonajeConectado) {
		this.listaPersonajes = nombresPersonajeConectado;
	}

	public List<String> getListaPersonajes() {
		return listaPersonajes;
	}

	public void setListaPersonajes(List<String> listaPersonajes) {
		this.listaPersonajes = listaPersonajes;
	}

	
}
