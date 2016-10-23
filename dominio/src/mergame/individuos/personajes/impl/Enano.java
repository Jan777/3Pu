package mergame.individuos.personajes.impl;

import mergame.individuos.personajes.Personaje;

public class Enano extends Personaje {
	
	int ataquesRecibidos;
	
	@Override
	protected void despuesDeAtacar() {
		salud++;
	}
	
	@Override
	protected int calcularPuntosDeAtaque() {
		return 10 + ataquesRecibidos;
	}

	@Override
	protected boolean puedeAtacar() {
		return energia >= calcularPuntosDeAtaque();
	}


}
