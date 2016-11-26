package cliente.mundo.partida;

import cliente.mundo.ui.IDibujable;
import cliente.mundo.ui.entidades.PersonajeElfo;
import cliente.mundo.ui.entidades.PersonajeHumano;
import cliente.mundo.ui.entidades.PersonajeOrco;
import cliente.mundo.ui.entidades.TipoPersonaje;

import java.awt.*;

public class Jugador{

	private String nombre;
	private IDibujable personajeUI;
	private IObservadorEventosJuego coordEventos;

	public Jugador(String nombre,String pjTipo) {

		this.nombre = nombre;
		instanciarPersonajeByName(pjTipo);
	}

	private void instanciarPersonajeByName(String pjTipo) {
		switch(pjTipo){
		case TipoPersonaje.PJ_ELFO:
			setPersonajeUI(new PersonajeElfo(this.nombre));
			break;
		case TipoPersonaje.PJ_HUMANO:
			setPersonajeUI(new PersonajeHumano(this.nombre));
			break;
		case TipoPersonaje.PJ_ORCO:
			setPersonajeUI(new PersonajeOrco(this.nombre));
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
}
