package main.java.mergame.skill.hechizo;

import main.java.mergame.individuos.Individuo;

public class HechizoCongelar extends Hechizo{

	public HechizoCongelar(){
		this.valorBase = 1;
	}
	
	@Override
	public void hechizar(Individuo hechicero, Individuo hechizado) {
		//VER TIEMPO STUNEO
		hechizado.serHechizado(0, 0, this.valorBase);
	}

}
