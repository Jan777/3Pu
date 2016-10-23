package mergame.casta.impl;

import mergame.casta.EsDeCasta;
import mergame.skill.Habilidad;

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
