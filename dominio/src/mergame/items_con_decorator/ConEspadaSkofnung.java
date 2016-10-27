package mergame.items_con_decorator;

import mergame.individuos.personajes.Personaje;

public class ConEspadaSkofnung extends PersonajeEquipado {

	public ConEspadaSkofnung(Personaje pj){
		super(pj);
	}
	
	public int getPuntosDeAtaqueFisico() {
		return personajeEquipado.getPuntosDeAtaqueFisico() * 2;
	}
	
	public int getPuntosDeAtaqueMagico() {
		return personajeEquipado.getPuntosDeAtaqueMagico() *2;
	}
	
	public int getPuntosDeDefensa(){
		return personajeEquipado.getPuntosDeDefensa();
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
