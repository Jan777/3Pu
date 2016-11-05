package main.java.mergame.interfaz;

import main.java.mergame.individuos.personajes.impl.PersonajeImpl;

public class Mundo {
	
	private String nombre;
	private int nivelRequerido;
	
	public Mundo(){	}
	
	public Mundo(String nombre, int nivelRequerido){
		this.nombre = nombre;
		this.nivelRequerido = nivelRequerido;
	}
	
	public boolean verificarAccesoPersonaje(PersonajeImpl personaje){
		if(personaje.getNivel() < this.nivelRequerido)
			return false;
		return true;
	}

}
