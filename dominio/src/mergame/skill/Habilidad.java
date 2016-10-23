package mergame.skill;


import mergame.individuos.Individuo;

public abstract class Habilidad {
	
	public static final String CURAR = "Curar";
	public static final String FUEGO = "Fuego";
	public static final String CONGELAR = "Congelar";
	public static final String ESPADA = "Espada";
	
	public abstract void realizarHabilidad(Individuo atacante, Individuo atacado);
	
}
