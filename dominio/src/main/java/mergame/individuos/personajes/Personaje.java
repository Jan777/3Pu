package main.java.mergame.individuos.personajes;

import main.java.mergame.casta.EsDeCasta;
import main.java.mergame.individuos.Individuo;

public interface Personaje extends Individuo{
	
	void setCasta(EsDeCasta casta);
	
	EsDeCasta getCasta();
	
	int getSalud();
	
	void consumoElixir(int puntos);
	
	void lanzarSkill(Individuo atacado, String nombreSkill);
}
