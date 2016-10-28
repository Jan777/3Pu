package mergame.items_con_decorator.impl;

import mergame.individuos.personajes.Personaje;
import mergame.items_con_decorator.PersonajeDecorator;

public class ConAnilloDraupnir extends PersonajeDecorator {

	public ConAnilloDraupnir(Personaje pj) {
		super(pj);
	}

	@Override
	public int getPuntosDeAtaqueFisico() {
		return this.getPersonaje().getPuntosDeAtaqueFisico() * 2;
	}
	
	@Override
	public int getPuntosDeAtaqueMagico() {
		return this.getPersonaje().getPuntosDeAtaqueMagico() *2;
	}

	@Override
	public int getPuntosDeDefensa() {
		return this.getPersonaje().getPuntosDeDefensa() * 2;
	}

}
