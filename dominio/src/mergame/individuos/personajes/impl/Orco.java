package mergame.individuos.personajes.impl;

public class Orco extends PersonajeImpl {
	int cantidadDeAtaquesRecibidos;
	
	@Override
	protected void despuesDeAtacar() {
		cantidadDeAtaquesRecibidos++;
	}
	
	@Override
	protected int calcularPuntosDeAtaque() {
		return 10 + cantidadDeAtaquesRecibidos;
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
}
