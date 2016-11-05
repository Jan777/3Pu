package main.java.mergame.items_con_decorator;

import main.java.mergame.casta.EsDeCasta;
import main.java.mergame.individuos.Individuo;
import main.java.mergame.individuos.personajes.Personaje;
import main.java.mergame.individuos.personajes.impl.PersonajeImpl;
import main.java.mergame.interfaz.Mundo;
import main.java.mergame.skill.Habilidad;

public abstract class PersonajeDecorator implements Personaje{
	
	protected String tipoDelItemEquipado;
	
	private Personaje personajeEquipado;
	
	public PersonajeDecorator(Personaje pj) {
		this.setPersonaje(pj);
	}
	
	public Personaje getPersonaje(){
		return this.personajeEquipado;
	}
	
	public void setPersonaje(Personaje personaje){
		this.personajeEquipado = personaje;
	}
	
	@Override
	public void consumoElixir(int puntos) {
		this.personajeEquipado.consumoElixir(puntos);
	}

	@Override
	public void setCasta(EsDeCasta casta) {
		// NO ME INTERESA IMPLEMENTAR ESTE METODO ACA
	}

	@Override
	public EsDeCasta getCasta() {
		return this.personajeEquipado.getCasta();
	}

	@Override
	public int getSalud() {
		return this.personajeEquipado.getSalud();
	}

	//SE UTILIZA CUANDO EL PERSONAJE TIENE AL MENOS UN ITEM EQUIPADO
	@Override
	public void lanzarSkill(Individuo atacado, String nombreSkill) {
		Habilidad skill = this.personajeEquipado.getCasta().getSkill(nombreSkill);

		skill.realizarHabilidad(this, atacado);
	}
	
	@Override
	public void serHechizado(int vidaCurada, int vidaQuitada, int turnosCongelado) {
		this.personajeEquipado.serHechizado(vidaCurada, vidaQuitada, turnosCongelado);
	}

	@Override
	public void serAtacado(int danio) {
		this.personajeEquipado.serAtacado(danio);
	}

	@Override
	public void atacar(Individuo victima) {
		this.personajeEquipado.atacar(victima);
	}

	@Override
	public boolean estaVivo() {
		return this.personajeEquipado.estaVivo();
	}
	
	@Override
	public int getPoderMagico() {
		return personajeEquipado.getPuntosDeAtaqueMagico();
	}
	
	public boolean entrarAlMundo(Mundo mundo){
		return personajeEquipado.entrarAlMundo(mundo);
	}
	
	
	
	@Override
	public int getExpOtorgada() {
		return personajeEquipado.getExpOtorgada();
	}

	@Override
	public int getNivel() {
		return personajeEquipado.getNivel();
	}
	
	@Override
	public int getExperiencia() {
		return personajeEquipado.getExperiencia();
	}
	
	
	// desequipaaaaaaaaaaaaaaaaaaaar
	@Override
 	public boolean tiene(Class decorado) {
 		return this.getClass() == decorado || this.personajeEquipado.tiene(decorado);
 	}
 
 	@Override
 	public Personaje desequipar(Class decorado) {
 		return this.desequiparEste(decorado);
 	}
 
 	private Personaje desequiparEste(Class decorado) {
 		if (this.getClass() == decorado)
 			return this.personajeEquipado;
 		return this.personajeEquipado.desequipar(decorado);
 	}
	
	
}
