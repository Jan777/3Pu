package mergame.individuos.personajes.impl;

import mergame.individuos.Individuo;
import mergame.individuos.personajes.Personaje;
import mergame.skill.Habilidad;

public class Orco extends Personaje {

	int cantidadDeAtaquesRecibidos;
	
	public Orco(){
		this.poderFisico = 10;
		this.poderFisico = 5;
	}
	@Override
	protected void despuesDeAtacar() {
		cantidadDeAtaquesRecibidos++;
	}
	
	@Override
	protected int calcularPuntosDeAtaque() {
		return 10 + cantidadDeAtaquesRecibidos;
	}

	@Override
	protected boolean puedeAtacar() {
		return energia >= calcularPuntosDeAtaque();
	}

	@Override
	public void serHechizado(int vidaCurada, int vidaQuitada, int turnosCongelado) {
		
		this.cantidadDeAtaquesRecibidos ++;
		if(turnosCongelado != 0){
			this.congelado = true;
		}
		this.salud += vidaCurada;
		this.salud -= vidaQuitada;
	}

	@Override
	public void serAtacado(int danio) {
		this.salud -=danio;
	}
	
	public void lanzarSkill(Individuo atacado, String nombreSkill){
		Habilidad skill = this.casta.getSkill(nombreSkill);
		//cuando le pegan 4 veces, el siguiente ataquue es por el doble de daño.
		if(this.cantidadDeAtaquesRecibidos >= 4){
			this.poderMagico = this.obtenerPuntosDeAtaqueMagico() * 2;
			this.poderFisico = this.obtenerPuntosDeAtaqueFisico() * 2;
			this.cantidadDeAtaquesRecibidos = 0;
			skill.realizarHabilidad(this, atacado);
			this.poderMagico = this.obtenerPuntosDeAtaqueMagico() / 2;
			this.poderFisico = this.obtenerPuntosDeAtaqueFisico() / 2;
		}
		else
			skill.realizarHabilidad(this, atacado);
	}
}
