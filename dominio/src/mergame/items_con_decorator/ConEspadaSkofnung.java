package mergame.items_con_decorator;

import mergame.individuos.Individuo;
import mergame.individuos.personajes.Personaje;
import mergame.skill.Habilidad;

public class ConEspadaSkofnung extends PersonajeEquipado {

	public ConEspadaSkofnung(Personaje pj){
		super(pj);
	}
	
	public int getPuntosDeAtaqueFisico() {
		return personajeEquipado.getPuntosDeAtaqueFisico() * 2;
	}
	
	public int getPuntosDeAtaqueMagico() {
		return personajeEquipado.getPuntosDeAtaqueMagico() * 2;
	}
	
	public int getPuntosDeDefensa(){
		return personajeEquipado.getPuntosDeDefensa();
	}

	@Override
	public void serHechizado(int vidaCurada, int vidaQuitada, int turnosCongelado) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void serAtacado(int danio) {
		// TODO Auto-generated method stub
		
	}
	
	public void lanzarSkill(Individuo atacado, String nombreSkill) {
		
		//LO PUSE ASI PORQUE EL THIS.PERSONAJEEQUIPADO ES EL QUE 
		//TIENE EL LANZARSKILL DEL ORCO, SINO REALIZA EL
		//LANZAR SKIL POR DEFECTO, QUE NO ME SIRVE PORQUE EL ORCO TIENE
		//ESE METODO ESPECIAL.
		this.personajeEquipado.lanzarSkill(atacado, nombreSkill);
	
	}
	

}
