package main.java.mergame.individuos.personajes.impl;

import main.java.mergame.individuos.personajes.Personaje;

public class Humano extends PersonajeImpl implements Personaje{
	
	public Humano() {
		this.tipo = "HUMANO";
	}
	
	public Humano(int nivel, int experiencia){
		this.nivel = nivel;
		this.experiencia = experiencia;
		this.tipo = "HUMANO";
	}
	
	@Override
	public void serHechizado(int vidaCurada, int vidaQuitada, int turnosCongelado) {
		if(turnosCongelado != 0){
			this.congelado = true;
		}
		this.salud += vidaCurada;
		if(this.salud< 20)
			this.salud -= vidaQuitada - (this.getPuntosDeDefensa() * 60 / 100);
		this.salud -= vidaQuitada - (this.getPuntosDeDefensa() * 30 / 100);
	}

	@Override
	public void serAtacado(int danio) {
		
		if(this.salud< 20){
			if((danio - (this.getPuntosDeDefensa() * 30 / 100)) < 0)
				this.salud -= 1;
			else
				this.salud -= danio - (this.getPuntosDeDefensa() * 60 / 100);
		}	
		else
			if((danio - (this.getPuntosDeDefensa() * 30 / 100)) < 0)
				this.salud -= 1;
			else
				this.salud -= danio - (this.getPuntosDeDefensa() * 30 / 100);		
	}

}
