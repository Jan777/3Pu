package cliente.mundo.partida;

import java.awt.*;

public interface IObservadorEventosJuego {
	void NuevaPosicion(Jugador jugador);
	boolean PuedoNuevaPosicion(Point p, Jugador j);
}
