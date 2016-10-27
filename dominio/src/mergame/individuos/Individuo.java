package mergame.individuos;

public abstract class Individuo implements Hechizable, Atacable {
	protected int salud = 100;
	protected int energia = 15;
	protected int poderMagico;
	protected int poderFisico;
	protected boolean congelado;
	
	public abstract void lanzarSkill(Individuo atacado, String skill);
	
	public abstract void serHechizado(int vidaCurada, int vidaQuitada, int turnosCongelado);
	
	public abstract void serAtacado(int danio);

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
