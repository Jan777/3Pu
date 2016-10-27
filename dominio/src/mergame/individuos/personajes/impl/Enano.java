package mergame.individuos.personajes.impl;

import java.util.Random;

import mergame.individuos.personajes.Personaje;

public class Enano extends Personaje {

	public Enano(){
		this.poderFisico = 6;
		this.poderMagico = 6;
	}
	@Override
	protected void despuesDeAtacar() {
		salud++;
	}

	@Override
	protected boolean puedeAtacar() {
		return energia >= calcularPuntosDeAtaque();
	}

	@Override
	public void serHechizado(int vidaCurada, int vidaQuitada, int turnosCongelado) {
		//Hago el calculo de probabilidad de esquibar un golpe.
		if(vidaQuitada != 0 || turnosCongelado != 0){
			if(!this.ProbabilidadEsquivar()){
				this.salud -= vidaQuitada;
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
			this.salud -= danio;
		}
	}

	@Override
	protected int calcularPuntosDeAtaque() {
		// TODO Auto-generated method stub
		return 0;
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
