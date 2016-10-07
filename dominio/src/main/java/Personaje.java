package decorator;

public abstract class Personaje implements Atacable, Hechizable{

	protected int energia = 100;
	protected int salud = 100;
	
	public final void atacar(Atacable atacado) {
		if (puedeAtacar()) {
			atacado.serAtacado(calcularPuntosDeAtaque());
			energia -= calcularPuntosDeAtaque();
			despuesDeAtacar();
		}
	}

	protected void despuesDeAtacar() { }
	
	protected abstract boolean puedeAtacar();
	protected abstract int calcularPuntosDeAtaque();
	
	public boolean estaVivo() {
		return this.salud > 0;
	}
	
	@Override
	public void serAtacado(int danio) {
		this.salud -= danio;
	}

	public void serCurado() {
		this.salud = 100;
	}

	public void serEnergizado() {
		this.energia = 100;
	}
	
	public int getSalud() {
		return this.salud;
	}

	public int obtenerPuntosDeAtaque() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public int obtenerPuntosDeDefensa() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public void consumoElixir(int puntos){
		
	}
	
	public void serHechizable(int fuerza, int vida){

	}
}
