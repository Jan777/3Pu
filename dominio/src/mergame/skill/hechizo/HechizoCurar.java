package mergame.skill.hechizo;

import mergame.individuos.Individuo;


public class HechizoCurar extends Hechizo{

	public HechizoCurar(){
		this.valorBase = 50;
	}
	
	@Override
	public void hechizar(Individuo hechicero, Individuo hechizado){
		int daño = hechicero.getPoderMagico() * this.valorBase;
		
		hechizado.serHechizado(daño, 0, 0);
	}

}
