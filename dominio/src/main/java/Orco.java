package decorator;

public class Orco extends Personaje {

	int cantidadDeAtaques;
	
	@Override
	protected void despuesDeAtacar() {
		cantidadDeAtaques++;
	}
	
	@Override
	protected int calcularPuntosDeAtaque() {
		return 10 + cantidadDeAtaques;
	}

	@Override
	protected boolean puedeAtacar() {
		return energia >= calcularPuntosDeAtaque();
	}

	@Override
	public void serHechizado(int fuerza, int vida) {
		// TODO Auto-generated method stub
		
	}
}
