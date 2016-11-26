package cliente.mundo.partida;

import cliente.mundo.ui.ControladorMouse;
import cliente.mundo.ui.UIService;
import cliente.mundo.ui.entidades.Mapa;
import cliente.mundo.ui.entidades.Relieve;
import cliente.mundo.ui.entidades.TextInfo;
import cliente.usuario.Usuario;

import java.awt.*;
import java.util.ArrayList;

public class Partida {

	//Jugadores en la partida, incluido vos.
	private ArrayList<Jugador> jugadoresPartida;
	private Jugador jugadorLocal;
	private CoordinadorEventos coordEventos;
	private Usuario usuario;

	public Partida(Usuario usuario){
		this.usuario = usuario;
		jugadoresPartida = new ArrayList<Jugador>();
		coordEventos = new CoordinadorEventos(this);
		ControladorMouse.getInstance().bindCoordinador(coordEventos);

		//Registra componentes visuales de la partida
        UIService.getInstance().registrarComponente(new Mapa());
        UIService.getInstance().registrarComponente(new Relieve(new Point(96,96)));
        //Informaci�n de texto
    	UIService.getInstance().registrarComponente(TextInfo.getInstance());
	}

	/**
	 * Registra al jugador local en la partida. Mantiene una referencia especifica del mismo.
	 * @param jugadorNuevo
	 */
	public void RegistrarJugador(Jugador jugadorNuevo, Boolean esLocal){


		if(jugadoresPartida.contains(jugadorNuevo))
			return;
		jugadoresPartida.add(jugadorNuevo);
		jugadorNuevo.bindCoordinador(this.coordEventos);
		//Registra al jugador nuevo como un personaje visual
		UIService.getInstance().registrarComponente(jugadorNuevo.getPersonajeUI());
		TextInfo.getInstance().Log("[Partida] Ingreso el jugador "+jugadorNuevo.getNombre());

		if(esLocal){
			jugadorLocal = jugadorNuevo;
			ControladorMouse.getInstance().setJugadorControlado(jugadorLocal);
			ControladorMouse.getInstance().setUsuario(usuario);

		}
	}


	/**
	 * Elimina un jugador existente en la partida
	 * @param jugadorAEliminar
	 */
	public void EliminarJugador(Jugador jugadorAEliminar){
		jugadoresPartida.remove(jugadorAEliminar);
	}

	/**
	 * Devuelve la instancia si existe del jugador por nombre.
	 * @param nombre
	 */
	public Jugador ObtenerJugadorPorNombre(String nombre){
		for(Jugador buscado : jugadoresPartida) {
	        if(buscado.getNombre().equals(nombre)) {
	            return buscado;
	        }
	    }
	    return null;
	}

	/**
	 * Devuelve si hay algun jugador en ese punto que no sea el
	 * @param p
	 * @return
	 */
	public boolean HayAlgunJugadorEn(Point p, Jugador j){

		for(Jugador buscado : jugadoresPartida) {
			if (buscado.getPos().equals(p))
				return true;
		}
		return false;
	}

	public void actualizarJugador(String usuario, Point point) {

		for(Jugador buscado : jugadoresPartida){
			if(buscado.getNombre().equals(usuario))
				buscado.setPos(point);
		}
	}
}
