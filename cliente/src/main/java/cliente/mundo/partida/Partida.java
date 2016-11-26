package cliente.mundo.partida;

import cliente.comunicacion.Batalla;
import cliente.mundo.ui.ControladorMouse;
import cliente.mundo.ui.ControladorTeclado;
import cliente.mundo.ui.UIService;
import cliente.mundo.ui.entidades.*;
import cliente.usuario.Usuario;
import org.codehaus.jackson.map.ObjectMapper;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Partida {

	//Jugadores en la partida, incluido vos.
	private ArrayList<Jugador> jugadoresPartida;
	private Jugador jugadorLocal;
	private CoordinadorEventos coordEventos;
	private Usuario usuario;
	private ModoBatalla modoBatalla;
	private Golpe golpe;

	public Partida(Usuario usuario){
		this.usuario = usuario;
		this.modoBatalla = new ModoBatalla();
		this.golpe = new Golpe();
		jugadoresPartida = new ArrayList<Jugador>();
		coordEventos = new CoordinadorEventos(this);
		ControladorMouse.getInstance().bindCoordinador(coordEventos);
		ControladorTeclado.getInstance().bindCoordinador(coordEventos);

		//Registra componentes visuales de la partida
        UIService.getInstance().registrarComponente(new Mapa());
        UIService.getInstance().registrarComponente(new Relieve(new Point(96,96)));
		UIService.getInstance().registrarComponente(modoBatalla);
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

	public Jugador ObtenerJugadorPorPosicion(Point pt){
		for(Jugador buscado : jugadoresPartida) {
			if(buscado.getPos().equals(pt)) {
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

	public void iniciarBatalla() {
		Point posicionLocal = this.jugadorLocal.getPos();

		int x = (int)posicionLocal.getX();
		int y = (int)posicionLocal.getY();
		if(this.HayAlgunJugadorEn(new Point(x-1,y-1),this.jugadorLocal)){
			IniciarBatallaServer(jugadorLocal,this.ObtenerJugadorPorPosicion(new Point(x-1,y-1)));
			return;
		}
		if(this.HayAlgunJugadorEn(new Point(x,y-1),this.jugadorLocal)){
			IniciarBatallaServer(jugadorLocal,this.ObtenerJugadorPorPosicion(new Point(x,y-1)));
			return;
		}
		if(this.HayAlgunJugadorEn(new Point(x+1,y-1),this.jugadorLocal)){
			IniciarBatallaServer(jugadorLocal,this.ObtenerJugadorPorPosicion(new Point(x+1,y-1)));
			return;
		}
		if(this.HayAlgunJugadorEn(new Point(x-1,y),this.jugadorLocal)){
			IniciarBatallaServer(jugadorLocal,this.ObtenerJugadorPorPosicion(new Point(x-1,y)));
			return;
		}
		if(this.HayAlgunJugadorEn(new Point(x+1,y),this.jugadorLocal)){
			IniciarBatallaServer(jugadorLocal,this.ObtenerJugadorPorPosicion(new Point(x+1,y)));
			return;
		}
		if(this.HayAlgunJugadorEn(new Point(x-1,y+1),this.jugadorLocal)){
			IniciarBatallaServer(jugadorLocal,this.ObtenerJugadorPorPosicion(new Point(x-1,y+1)));
			return;
		}
		if(this.HayAlgunJugadorEn(new Point(x,y+1),this.jugadorLocal)){
			IniciarBatallaServer(jugadorLocal,this.ObtenerJugadorPorPosicion(new Point(x,y+1)));
			return;
		}
		if(this.HayAlgunJugadorEn(new Point(x+1,y+1),this.jugadorLocal)){
			IniciarBatallaServer(jugadorLocal,this.ObtenerJugadorPorPosicion(new Point(x+1,y+1)));
			return;
		}

	}

	public void IniciarBatallaServer(Jugador jugadorLocal, Jugador jugador){
		try {
			PrintWriter pw = new PrintWriter(usuario.getSocket().getOutputStream());
			ObjectMapper mapper = new ObjectMapper();
			Batalla batalla = new Batalla();
			batalla.setPersonajeAtacante(jugadorLocal.getNombre());
			batalla.setPersonajeAtacado(jugador.getNombre());

			String mensaje = mapper.writeValueAsString(batalla);

			pw.println("BATA" + mensaje);
			pw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void IniciarBatalla(Jugador jugadorLocal, Jugador jugador) {

		UIService.getInstance().registrarComponente(golpe);
		golpe.Golpe(jugador.getPos());
		jugador.downVida();


		if( jugador.getVida()<= 0) {
			jugador.setPos(new Point(1,1));
			jugadorLocal.setVida(100);

			TextInfo.getInstance().Log("Has muerto. Vas a revivir ...");
		}

		/*ControladorTeclado.getInstance().setInvalidador(true);
		TextInfo.getInstance().Log("Iniciando batalla: "+jugadorLocal.getNombre()+" vs "+jugador.getNombre());
		this.modoBatalla.setJugadorLocal(jugadorLocal.getNombre());
		this.modoBatalla.setJugadorRemoto(jugador.getNombre());
		this
		this.modoBatalla.setVisible(true);
*/

	}


}
