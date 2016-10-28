package mergame.individuos.personajes.impl;

public class Humano extends PersonajeImpl {
	
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
