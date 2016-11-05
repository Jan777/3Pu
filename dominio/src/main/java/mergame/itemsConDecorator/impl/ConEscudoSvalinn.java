package main.java.mergame.itemsConDecorator.impl;

import main.java.mergame.individuos.personajes.Personaje;
import main.java.mergame.individuos.personajes.impl.PersonajeImpl;
import main.java.mergame.itemsConDecorator.PersonajeDecorator;

public class ConEscudoSvalinn extends PersonajeDecorator {
	public ConEscudoSvalinn(Personaje pj) {
		super(pj);
		this.tipoDelItemEquipado = "ESCUDO";
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
