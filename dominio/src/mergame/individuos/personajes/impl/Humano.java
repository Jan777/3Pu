package mergame.individuos.personajes.impl;

import mergame.individuos.personajes.Personaje;

public class Humano extends Personaje {
	
	private int puntosDeAtaque = 1;
	private int puntosDeDefensa = 0;
	
	@Override
	protected int calcularPuntosDeAtaque() {
		return 10;
	}
	
	public int getPuntosDeDefensa(){
		return this.puntosDeDefensa;
	}

	@Override
	public void serHechizado(int vidaCurada, int vidaQuitada, int turnosCongelado) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void serAtacado(int danio) {
		// TODO Auto-generated method stub
		
	}
	
}
