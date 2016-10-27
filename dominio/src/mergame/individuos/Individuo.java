package mergame.individuos;

public abstract class Individuo implements Hechizable, Atacable {
	protected int salud = 100;
	protected int estamina = 100;	
	protected int nivel = 1;
	protected int experiencia = 0;
	protected int defensa = 10;
	protected int poderMagico = 10;
	protected int poderFisico = 10;
	protected boolean congelado;
	
	
	public abstract void lanzarSkill(Individuo atacado, String skill);
	
	public abstract void serHechizado(int vidaCurada, int vidaQuitada, int turnosCongelado);
	
	public abstract void serAtacado(int danio);
	
	public abstract void atacar(Individuo victima);
	
	
	
	

	public boolean estaVivo() {
		return this.salud > 0;
	}
	
	public int getPoderMagico() {
		return poderMagico;
	}

	public int getPoderFisico() {
		return poderFisico;
	}
	
	
}
