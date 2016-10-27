package mergame.individuos.personajes.impl;

import mergame.individuos.personajes.Personaje;

public class Elfo extends Personaje{

	@Override
	protected boolean puedeAtacar() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected int calcularPuntosDeAtaque() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void serHechizado(int vidaCurada, int vidaQuitada, int turnosCongelado) {
		this.salud += vidaCurada;
		this.salud -= vidaQuitada;
		if(turnosCongelado!=0)
			this.congelado = true;
		//me cura 10 en caso de seguir vivo
		this.sanar();
		
	}

	@Override
	public void serAtacado(int danio) {
		
	}
	
	//Ver si siempre va a ser 10, o hacer algun otro cálculo
	public void sanar(){
		if(this.salud < 20){
			if(this.estaVivo())
				this.salud += 10;
		}
	}

}
