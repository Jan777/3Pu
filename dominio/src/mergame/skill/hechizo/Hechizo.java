package mergame.skill.hechizo;

import mergame.individuos.Individuo;
import mergame.skill.Habilidad;


public abstract class Hechizo extends Habilidad{
		
	protected int valorBase;
	
	public void realizarHabilidad(Individuo atacante, Individuo atacado){
		this.hechizar(atacante, atacado);
	}
	
	public abstract void hechizar(Individuo hechicero, Individuo hechizado);
	
}
