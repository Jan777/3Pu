package main.java.mergame.individuos.personajes.impl;

import main.java.mergame.individuos.personajes.Personaje;

public class Orco extends PersonajeImpl implements Personaje {
	
	//Duda de si implementar un constructor propio por defecto o usar los valores preseteados de PersonajeImpl
//	public Orco(){
//		this.salud = 100;
//		this.estamina = 100;	
//		this.nivel = 1;
//		this.experiencia = 0;
//		this.defensa = 10;
//		this.poderMagico = 10;
//		this.poderFisico = 10;
//		this.tipo = "ORCO";
//	}
	
	public Orco() {
		this.tipo = "ORCO";
	}
	
	public Orco(int nivel, int experiencia){
		this.nivel = nivel;
		this.experiencia = experiencia;
		this.tipo = "ORCO";
	}
		
	@Override
	protected void despuesDeAtacar() {
		this.cantidadAtaquesRecibidos++;
	}
	

	@Override
	public void serHechizado(int vidaCurada, int vidaQuitada, int turnosCongelado) {
		
		this.cantidadAtaquesRecibidos ++;
		if(turnosCongelado != 0){
			this.congelado = true;
		}
		this.salud += vidaCurada;
		this.salud -= vidaQuitada;
	}

	@Override
	public void serAtacado(int danio) {
		if((danio - (this.getPuntosDeDefensa() * 30 / 100)) < 0)
			this.salud -= 1;
		else
			this.salud -=danio - (this.getPuntosDeDefensa() * 30 / 100);
		this.cantidadAtaquesRecibidos ++;
	}
}
