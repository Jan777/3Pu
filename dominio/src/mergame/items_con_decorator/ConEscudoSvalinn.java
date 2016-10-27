package mergame.items_con_decorator;

import mergame.individuos.personajes.Personaje;

public class ConEscudoSvalinn extends PersonajeEquipado {

	public ConEscudoSvalinn(Personaje pj) {
		super(pj);
	}
	public int obtenerPuntosDeDefensa() {
		return personajeEquipado.obtenerPuntosDeDefensa() + 10;
	}
	
	public int obtenerPuntosDeAtaqueFisico() {
		return personajeEquipado.obtenerPuntosDeAtaqueFisico() * 2;
	}
	
	public int obtenerPuntosDeAtaqueMagico() {
		return personajeEquipado.obtenerPuntosDeAtaqueMagico() *2;
	}
	@Override
	public void consumoElixir(int puntos) {
		// TODO Auto-generated method stub
		personajeEquipado.consumoElixir(puntos);
	}

	@Override
	public void serHechizado(int vidaCurada, int vidaQuitada, int turnosCongelado) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void serAtacado(int danio) {
		// TODO Auto-generated method stub
		
	}
	
	
}
