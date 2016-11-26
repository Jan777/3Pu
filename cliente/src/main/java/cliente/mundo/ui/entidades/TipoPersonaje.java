package cliente.mundo.ui.entidades;

import main.java.mergame.individuos.personajes.Personaje;
import main.java.mergame.individuos.personajes.impl.Elfo;
import main.java.mergame.individuos.personajes.impl.Humano;
import main.java.mergame.individuos.personajes.impl.Orco;

public class TipoPersonaje {

	public static final String PJ_ORCO = "Orco";
	public static final String PJ_HUMANO = "Humano";
	public static final String PJ_ELFO = "Elfo";

    public static String GetByName(Personaje personaje) {
		if(personaje instanceof Orco)
			return TipoPersonaje.PJ_ORCO;
		if(personaje instanceof Humano)
			return TipoPersonaje.PJ_HUMANO;
		if(personaje instanceof Elfo)
			return TipoPersonaje.PJ_ELFO;
		return null;
    }
}
