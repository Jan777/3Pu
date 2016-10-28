package mergame.skill.hechizo;

import mergame.individuos.Individuo;

public class HechizoFuego extends Hechizo{

	public HechizoFuego(){
		this.valorBase = 1;
	}
	
	@Override
	public void hechizar(Individuo hechicero, Individuo hechizado) {
		int danio = hechicero.getPuntosDeAtaqueMagico() * this.valorBase;
		
		hechizado.serHechizado(0, danio, 0);
	}

}
