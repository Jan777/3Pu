package main.java.mergame.individuos.personajes;

import main.java.mergame.casta.EsDeCasta;
import main.java.mergame.individuos.Individuo;
import main.java.mergame.interfaz.Mundo;

public interface Personaje extends Individuo{
	
	void setCasta(EsDeCasta casta);
	
	EsDeCasta getCasta();
	
	int getSalud();
	
	void consumoElixir(int puntos);
	
	void lanzarSkill(Individuo atacado, String nombreSkill);
	int getPoderMagico();
	boolean entrarAlMundo(Mundo mundo);
}
