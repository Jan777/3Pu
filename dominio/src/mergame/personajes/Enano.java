package mergame.personajes;


public class Enano extends Personaje {
	
	int ataquesRecibidos;
	
	@Override
	protected void despuesDeAtacar() {
		salud++;
	}
	
	@Override
	protected int calcularPuntosDeAtaque() {
		return 10 + ataquesRecibidos;
	}

	@Override
	protected boolean puedeAtacar() {
		return energia >= calcularPuntosDeAtaque();
	}

	@Override
	public void serAtacado(int danio) {
		super.serAtacado(danio);
		this.ataquesRecibidos++;
	}

	@Override
	public void serHechizado(int fuerza, int vida) {
		// TODO Auto-generated method stub
		
	}
}
