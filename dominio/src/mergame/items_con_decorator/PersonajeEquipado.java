package mergame.items_con_decorator;

import mergame.individuos.personajes.Personaje;

public abstract class PersonajeEquipado extends Personaje{

	protected Personaje personajeEquipado;
	
	public PersonajeEquipado(Personaje pj){
		personajeEquipado = pj;
	}
	
	@Override
	protected int calcularPuntosDeAtaque() {
		// TODO Auto-generated method stub
		return 0;
	}

}
