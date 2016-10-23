package mergame.casta.impl;

import java.util.HashMap;

import mergame.casta.EsDeCasta;
import mergame.skill.Habilidad;
import mergame.skill.hechizo.HechizoCongelar;
import mergame.skill.hechizo.HechizoCurar;
import mergame.skill.hechizo.HechizoFuego;

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
