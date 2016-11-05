package main.java.mergame.itemsConDecorator.impl;

import main.java.mergame.individuos.personajes.Personaje;
import main.java.mergame.individuos.personajes.impl.PersonajeImpl;
import main.java.mergame.itemsConDecorator.PersonajeDecorator;

public class ConEspadaSkofnung extends PersonajeDecorator {
	
	public ConEspadaSkofnung(Personaje pj){
		super(pj);
		this.tipoDelItemEquipado = "ESPADA";
	}
	
	@Override
	public int getPuntosDeAtaqueFisico() {
		return this.getPersonaje().getPuntosDeAtaqueFisico() + 10;
	}
	
	@Override
	public int getPuntosDeAtaqueMagico() {
		return this.getPersonaje().getPuntosDeAtaqueMagico() + 10;
	}
	
	@Override
	public int getPuntosDeDefensa(){
		return this.getPersonaje().getPuntosDeDefensa();
	}



}
