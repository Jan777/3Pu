package main.java.mergame.items_con_decorator.impl;

import main.java.mergame.individuos.personajes.Personaje;
import main.java.mergame.items_con_decorator.PersonajeDecorator;

public class ConItemGenerico extends PersonajeDecorator{

	public ConItemGenerico(Personaje pj) {
		super(pj);
		
	}

	@Override
	public int getPuntosDeAtaqueFisico() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getPuntosDeAtaqueMagico() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getPuntosDeDefensa() {
		// TODO Auto-generated method stub
		return 0;
	}

}
