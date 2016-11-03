package main.java.mergame.individuos.personajes.impl;

public class Elfo extends PersonajeImpl{
	
	//Duda de si implementar un constructor propio por defecto o usar los valores preseteados de PersonajeImpl
//	public Elfo(int experiencia, int nivel){
//		this.salud = 100;
//		this.estamina = 100;	
//		this.nivel = nivel;
//		this.experiencia = experiencia;
//		this.defensa = 10;
//		this.poderMagico = 10;
//		this.poderFisico = 10;
//	}
	
	public Elfo() {
		this.tipo = "ELFO";
	}
	
	public Elfo(int nivel, int experiencia){
		this.nivel = nivel;
		this.experiencia = experiencia;
		this.tipo = "ELFO";
	}
	
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
