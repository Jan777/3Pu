package main.java.mergame.skill.melee;

import main.java.mergame.individuos.Individuo;
import main.java.mergame.skill.Habilidad;


public abstract class Melee extends Habilidad{
	String Nombre;
	
	public void realizarHabilidad(Individuo individuo){
		this.atacar(individuo);
	}
	
	public abstract void atacar(Individuo individuo);
}
