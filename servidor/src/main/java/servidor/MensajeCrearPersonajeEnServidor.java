package servidor;

import java.io.Serializable;

import main.java.mergame.individuos.personajes.Personaje;

public class MensajeCrearPersonajeEnServidor implements Serializable {
	private String raza;
	private String casta;
	private String nombre;

	public MensajeCrearPersonajeEnServidor() {
		
	}

	public MensajeCrearPersonajeEnServidor(String raza, String casta, String nombre) {
		this.raza = raza;
		this.casta = casta;
		this.nombre = nombre;
	}

	public String getRaza() {
		return raza;
	}

	public void setRaza(String raza) {
		this.raza = raza;
	}

	public String getCasta() {
		return casta;
	}

	public void setCasta(String casta) {
		this.casta = casta;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
		
}
