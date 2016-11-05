package main.java.mergame.itemsConDecorator.impl;

import main.java.mergame.individuos.personajes.Personaje;
import main.java.mergame.individuos.personajes.impl.PersonajeImpl;
import main.java.mergame.itemsConDecorator.PersonajeDecorator;

public class ConAnilloDraupnir extends PersonajeDecorator {
	
	public ConAnilloDraupnir(Personaje pj) {
		super(pj);
		this.tipoDelItemEquipado = "ANILLO";
	}

	@Override
	public int getPuntosDeAtaqueFisico() {
		return this.getPersonaje().getPuntosDeAtaqueFisico() * 2;
	}
	
	@Override
	public int getPuntosDeAtaqueMagico() {
		return this.getPersonaje().getPuntosDeAtaqueMagico() *2;
	}

	@Override
	public int getPuntosDeDefensa() {
		return this.getPersonaje().getPuntosDeDefensa() * 2;
	}

	

	


}
