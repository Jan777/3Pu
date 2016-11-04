package main.java.mergame.individuos;

public interface Individuo extends Hechizable, Atacable {
	
	int getPuntosDeAtaqueFisico();
	
	int getPuntosDeAtaqueMagico();
	
	int getPuntosDeDefensa();
	
	void serHechizado(int vidaCurada, int vidaQuitada, int turnosCongelado);
	
	void serAtacado(int danio);
	
	void atacar(Individuo victima);
	
	boolean estaVivo();
	
	int getExpOtorgada();
	
	int getNivel();
	
	int getExperiencia();
	
}
