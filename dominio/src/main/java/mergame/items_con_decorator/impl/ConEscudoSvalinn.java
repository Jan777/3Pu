package main.java.mergame.items_con_decorator.impl;

import main.java.mergame.individuos.personajes.Personaje;
import main.java.mergame.items_con_decorator.PersonajeDecorator;

public class ConEscudoSvalinn extends PersonajeDecorator {
	public ConEscudoSvalinn(Personaje pj) {
		super(pj);
	}
	
	@Override
	public int getPuntosDeDefensa() {
		return this.getPersonaje().getPuntosDeDefensa() + 10;
	}
	
	@Override
	public int getPuntosDeAtaqueFisico() {
		return this.getPersonaje().getPuntosDeAtaqueFisico();
	}
	
	@Override
	public int getPuntosDeAtaqueMagico() {
		return this.getPersonaje().getPuntosDeAtaqueMagico();
	}
	
}
