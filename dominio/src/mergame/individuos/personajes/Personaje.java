package mergame.individuos.personajes;

import mergame.casta.EsDeCasta;
import mergame.individuos.Individuo;

public interface Personaje extends Individuo{
	
	void setCasta(EsDeCasta casta);
	
	EsDeCasta getCasta();
	
	int getSalud();
	
	void consumoElixir(int puntos);
	
	void lanzarSkill(Individuo atacado, String nombreSkill);
}
