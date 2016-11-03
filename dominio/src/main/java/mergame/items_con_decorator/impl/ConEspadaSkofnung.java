package main.java.mergame.items_con_decorator.impl;

import main.java.mergame.individuos.personajes.Personaje;
import main.java.mergame.items_con_decorator.PersonajeDecorator;

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
		return this.getPersonaje().getPuntosDeAtaqueMagico();
	}
	
	@Override
	public int getPuntosDeDefensa(){
		return this.getPersonaje().getPuntosDeDefensa();
	}

}
