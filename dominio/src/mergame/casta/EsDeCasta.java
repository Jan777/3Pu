package mergame.casta;

import java.util.Map;

import mergame.skill.Habilidad;

public abstract class EsDeCasta {

	protected Map <String, Habilidad> habilidades;
	protected int poderMagicoBase;
	protected int poderFisicoBase;
	
	public abstract Habilidad getSkill(String skill);

	public int getPoderMagicoBase() {
		return poderMagicoBase;
	}

	public int getPoderFisicoBase() {
		return poderFisicoBase;
	}
	
}