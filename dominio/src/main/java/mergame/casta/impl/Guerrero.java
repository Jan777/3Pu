package main.java.mergame.casta.impl;

import main.java.mergame.casta.EsDeCasta;
import main.java.mergame.skill.Habilidad;

public class Guerrero extends EsDeCasta{
	public Guerrero(){
		this.poderFisicoBase = 5;
		this.poderMagicoBase = 1;
	}

	@Override
	public Habilidad getSkill(String skill) {
		return null;
	}

}
