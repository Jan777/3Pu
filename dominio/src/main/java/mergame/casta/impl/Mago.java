package main.java.mergame.casta.impl;

import java.util.HashMap;

import main.java.mergame.casta.EsDeCasta;
import main.java.mergame.skill.Habilidad;
import main.java.mergame.skill.hechizo.HechizoCongelar;
import main.java.mergame.skill.hechizo.HechizoCurar;
import main.java.mergame.skill.hechizo.HechizoFuego;

public class Mago extends EsDeCasta{
	
	public Mago(){
		this.poderFisicoBase = 1;
		this.poderMagicoBase = 5;
		this.habilidades = new HashMap<>();
		habilidades.put(Habilidad.CURAR, new HechizoCurar());
		habilidades.put(Habilidad.CONGELAR, new HechizoCongelar());
		habilidades.put(Habilidad.FUEGO, new HechizoFuego());
	}

	@Override
	public Habilidad getSkill(String skill) {
		return this.habilidades.get(skill);
	}
	
}
