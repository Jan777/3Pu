package mergame.items_con_decorator;

import mergame.individuos.personajes.Personaje;

public class ConEspadaSkofnung extends PersonajeEquipado {

	public ConEspadaSkofnung(Personaje pj){
		super(pj);
	}
	
	public int obtenerPuntosDeAtaqueFisico() {
		return personajeEquipado.obtenerPuntosDeAtaqueFisico() * 2;
	}
	
	public int obtenerPuntosDeAtaqueMagico() {
		return personajeEquipado.obtenerPuntosDeAtaqueMagico() *2;
	}
	
	public int obtenerPuntosDeDefensa(){
		return personajeEquipado.obtenerPuntosDeDefensa();
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
