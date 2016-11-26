package cliente.mundo.partida;

import cliente.mundo.ui.IDibujable;
import cliente.mundo.ui.entidades.*;

import java.awt.*;

public class Jugador{

	private String nombre;
	private IDibujable personajeUI;
	private IObservadorEventosJuego coordEventos;
	private ILife vidapj;
	private int vida;

	public Jugador(String nombre,String pjTipo) {
		this.vida=100;
		this.nombre = nombre;
		instanciarPersonajeByName(pjTipo);
	}

	private void instanciarPersonajeByName(String pjTipo) {
		switch(pjTipo){
		case TipoPersonaje.PJ_ELFO:
			PersonajeElfo elfo = new PersonajeElfo(this.nombre);
			vidapj = elfo;
			setPersonajeUI(elfo);
			break;
		case TipoPersonaje.PJ_HUMANO:
			PersonajeHumano humano = new PersonajeHumano(this.nombre);
			vidapj = humano;
			setPersonajeUI(humano);
			break;
		case TipoPersonaje.PJ_ORCO:
			PersonajeOrco orco = new PersonajeOrco(this.nombre);
			vidapj = orco;
			setPersonajeUI(orco);

			break;
		case TipoPersonaje.PJ_ENANO:
			PersonajeEnano enano = new PersonajeEnano(this.nombre);
			vidapj = enano;
			setPersonajeUI(enano);
				break;
		}

	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public IDibujable getPersonajeUI() {
		return personajeUI;
	}

	public void setPersonajeUI(IDibujable personajeUI) {
		this.personajeUI = personajeUI;
	}

	public Point getPos(){
		return this.getPersonajeUI().getPos();
	}

	public void setPos(Point puntoDestino) {

		this.getPersonajeUI().setPos(puntoDestino);

	}

	public void bindCoordinador(IObservadorEventosJuego coordEventos) {

		this.coordEventos = coordEventos;
	}

    public void downVida() {
		vida -=10;
		vidapj.setVida(vida);
    }

	public int getVida() {
		return this.vida;
	}

	public void setVida(int vida) {
		this.vida = vida;
	}
}
