package main.java.mergame.individuos.personajes.impl;

import java.util.Random;

public class Enano extends PersonajeImpl {
	//Duda de si implementar un constructor propio por defecto o usar los valores preseteados de PersonajeImpl
//	public Enano(int experiencia, int nivel){
//		this.salud = 100;
//		this.estamina = 100;	
//		this.nivel = nivel;
//		this.experiencia = experiencia;
//		this.defensa = 10;
//		this.poderMagico = 10;
//		this.poderFisico = 10;
//	}
//	
	
	public Enano() {
		this.tipo = "ENANO";
	}
	
	public Enano(int nivel, int experiencia){
		this.nivel = nivel;
		this.experiencia = experiencia;
		this.tipo = "ENANO";
	}
	
	@Override
	protected void despuesDeAtacar() {
		salud++;
	}

	@Override
	public void serHechizado(int vidaCurada, int vidaQuitada, int turnosCongelado) {
		//Hago el calculo de probabilidad de esquibar un golpe.
		if(!this.ProbabilidadEsquivar()){
			if(vidaQuitada != 0)
				this.salud -= vidaQuitada - (this.getPuntosDeDefensa() * 20) /100;
			if( turnosCongelado != 0){
				this.congelado = true;
			}
		}else{
			this.salud += vidaCurada;
			this.congelado = true;
			//me aseguro que no me pase de 100 de vida.
			if(this.salud > 100)
				this.salud = 100;
		}
	}

	@Override
	public void serAtacado(int danio) {
		if (!this.ProbabilidadEsquivar()){
			this.salud -= danio - (this.getPuntosDeDefensa() * 30) /100;
		}
	}

	public boolean ProbabilidadEsquivar() {
		Random r = new Random();
		int probabilidad = r.nextInt(10);
		if (probabilidad == 1 || probabilidad == 5 || probabilidad == 8)
			return true;
		else
			return false;
	}
}
