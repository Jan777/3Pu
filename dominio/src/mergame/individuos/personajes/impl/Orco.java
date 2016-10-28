package mergame.individuos.personajes.impl;

public class Orco extends PersonajeImpl {
	
	public Orco(){
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
