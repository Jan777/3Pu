package main.java.mergame.itemsConDecorator.impl;

import main.java.mergame.individuos.personajes.Personaje;
import main.java.mergame.individuos.personajes.impl.PersonajeImpl;
import main.java.mergame.itemsConDecorator.PersonajeDecorator;

public class ConItemGenerico extends PersonajeDecorator{

	public ConItemGenerico(Personaje pj) {
		super(pj);
		
	}

	@Override
	public int getPuntosDeAtaqueFisico() {
		return 0;
	}

	@Override
	public int getPuntosDeAtaqueMagico() {
		return 0;
	}

	@Override
	public int getPuntosDeDefensa() {
		return 0;
	}

	

}
