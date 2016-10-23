package mergame.individuos.personajes.impl;

import mergame.individuos.personajes.Personaje;

public class Orco extends Personaje {

	int cantidadDeAtaques;
	
	@Override
	protected void despuesDeAtacar() {
		cantidadDeAtaques++;
	}
	
	@Override
	protected int calcularPuntosDeAtaque() {
		return 10 + cantidadDeAtaques;
	}

	@Override
	protected boolean puedeAtacar() {
		return energia >= calcularPuntosDeAtaque();
	}
}
