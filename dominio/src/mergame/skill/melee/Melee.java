package mergame.skill.melee;

import mergame.individuos.Individuo;
import mergame.skill.Habilidad;


public abstract class Melee extends Habilidad{
	String Nombre;
	
	public void realizarHabilidad(Individuo individuo){
		this.atacar(individuo);
	}
	
	public abstract void atacar(Individuo individuo);
}
