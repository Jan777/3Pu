package main.java.mergame.individuos.personajes.impl;

public class Elfo extends PersonajeImpl{
	@Override
	public void serHechizado(int vidaCurada, int vidaQuitada, int turnosCongelado) {
		this.salud += vidaCurada;
		this.salud -= vidaQuitada - (this.getPuntosDeDefensa() * 20) /100;
		if(turnosCongelado!=0)
			this.congelado = true;
		//me cura 10 en caso de seguir vivo
		this.sanar();
		
	}

	@Override
	public void serAtacado(int danio) {
		
		this.salud -= danio - (this.getPuntosDeDefensa() * 20) /100;
		this.sanar();
	}
	
	//Ver si siempre va a ser 10, o hacer algun otro c�lculo
	public void sanar(){
		if(this.salud < 20){
			if(this.estaVivo())
				this.salud += 10;
		}
	}

}
