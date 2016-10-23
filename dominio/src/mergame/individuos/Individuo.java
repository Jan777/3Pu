package mergame.individuos;

public abstract class Individuo implements Hechizable, Atacable {
	protected int salud = 100;
	protected int poderMagico;
	protected int poderFisico;
	
	public abstract void lanzarSkill(Individuo atacado, String skill);
	
	public void serHechizado(int vidaCurada, int vidaQuitada, int turnosCongelado){
		this.salud += vidaCurada;
		this.salud -= vidaQuitada;
	}
	
	public void serAtacado(int danio){
		
	}

	public int getPoderMagico() {
		return poderMagico;
	}

	public int getPoderFisico() {
		return poderFisico;
	}
	
	
}
