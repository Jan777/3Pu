package mergame.individuos.personajes.impl;

import mergame.individuos.personajes.Personaje;

public class Humano extends Personaje {
	
	private int puntosDeAtaque = 1;
	private int puntosDeDefensa = 0;
	
	@Override
	protected int calcularPuntosDeAtaque() {
		return 10;
	}

	@Override
	protected boolean puedeAtacar() {
		return energia >= 10;
	}

	@Override
	public int obtenerPuntosDeAtaque() {
		return this.puntosDeAtaque;
	}
	
	public int obtenerPuntosDeDefensa(){
		return this.puntosDeDefensa;
	}
	
}
