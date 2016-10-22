package mergame.items_con_decorator;

import mergame.personajes.Personaje;

public class ConEspadaSkofnung extends PersonajeEquipado {

	public ConEspadaSkofnung(Personaje pj){
		super(pj);
	}
	
	@Override
	public int obtenerPuntosDeAtaque() {
		return personajeEquipado.obtenerPuntosDeAtaque() + 5;
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
	public void serHechizado(int fuerza, int vida) {
		// TODO Auto-generated method stub
		
	}

}
