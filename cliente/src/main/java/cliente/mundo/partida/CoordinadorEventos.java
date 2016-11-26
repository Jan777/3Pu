package cliente.mundo.partida;

import java.awt.*;

public class CoordinadorEventos implements IObservadorEventosJuego {

	private Partida partida;

	public CoordinadorEventos(Partida partida){
		this.partida = partida;
	}

	@Override
	public void NuevaPosicion(Jugador jugador) {

	}

	@Override
	public boolean PuedoNuevaPosicion(Point p, Jugador j)
	{
		return !(this.partida.HayAlgunJugadorEn(p,j));
	}


}
