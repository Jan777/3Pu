package main.java.mergame.individuos.personajes.impl;

import main.java.mergame.casta.EsDeCasta;
import main.java.mergame.individuos.Individuo;
import main.java.mergame.individuos.personajes.Personaje;
import main.java.mergame.interfaz.Mundo;
import main.java.mergame.items_con_decorator.PersonajeDecorator;
import main.java.mergame.skill.Habilidad;

public abstract class PersonajeImpl implements Personaje {
	protected String tipo;
	protected int salud = 100;
	protected int estamina = 100;	
	protected int nivel = 1;	
	protected int experiencia = 0;
	protected int defensa = 0;
	protected int poderMagico = 10;
	protected int poderFisico = 10;
	protected boolean congelado;
	protected String nombre;
	public EsDeCasta casta;
	protected int cantidadAtaquesRecibidos;
	
	public PersonajeImpl(){
		
	}
	
	public PersonajeImpl(int nivel, int experiencia){
		this.nivel = nivel;
		this.experiencia = experiencia;
	}

	
	/* METODOS INTERFAZ PERSONAJE */
	@Override
	public EsDeCasta getCasta() {
		return casta;
	}

	@Override
	public int getSalud() {
		return this.salud;
	}

	@Override
	public void setCasta(EsDeCasta casta) {
		this.casta = casta;

		// VER DANIOS SEGUN NIVEL E ITEMS EQUIPADOS
		this.poderFisico = this.casta.getPoderFisicoBase();
		this.poderMagico = this.casta.getPoderMagicoBase();
	}

	//ESTE METODO SE UTILIZA CUANDO EL PERSONAJE NO TIENE NADA EQUIPADO
	@Override
	public void lanzarSkill(Individuo atacado, String nombreSkill) {
		Habilidad skill = this.casta.getSkill(nombreSkill);

		skill.realizarHabilidad(this, atacado);
	}

	@Override
	public void consumoElixir(int puntos) {

	}
	/* FIN METODOS INTERFAZ PERSONAJE */

	/* METODOS INTERFAZ INDIVIDUO */
	@Override
	public boolean estaVivo() {
		return this.salud > 0 ? true : false;
	}
	
	@Override
	public int getPuntosDeAtaqueFisico() {
		return this.poderFisico;
	}
	
	@Override
	public int getPuntosDeAtaqueMagico() {
		return this.poderMagico;
	}
	
	@Override
	public int getPuntosDeDefensa() {
		return this.defensa;
	}
	/* FIN METODOS INTERFAZ INDIVIDUO */
	
	protected void despuesDeAtacar() {
	}

	public void atacar(Individuo victima) {
		if (puedeAtacar()) {
			if(this.tipo == "ORCO" && this.cantidadAtaquesRecibidos >3){
				victima.serAtacado(this.getPuntosDeAtaqueFisico()*2);
				this.cantidadAtaquesRecibidos = 0;
			}
			victima.serAtacado(this.getPuntosDeAtaqueFisico());
			this.estamina--;
		}
	}
	
	public void reestablecerEstamina(){
		estamina = 100;
	}
	
	public void serHechizado(int vidaCurada, int vidaQuitada, int turnosCongelado) {
		// TODO Auto-generated method stub		
	}
	
	public void serAtacado(int danio) {
		// TODO Auto-generated method stub
		
	}
	
	public boolean entrarAlMundo(Mundo mundo){
		return mundo.verificarAccesoPersonaje(this);
	}
	
	public int getCantidadAtaquesRecibidos() {
		return cantidadAtaquesRecibidos;
	}

	public void setCantidadAtaquesRecibidos(int cantidadAtaquesRecibidos) {
		this.cantidadAtaquesRecibidos = cantidadAtaquesRecibidos;
	}

	public void setDefensa(int defensa) {
		this.defensa = defensa;
	}

	public void setPoderMagico(int poderMagico) {
		this.poderMagico = poderMagico;
	}

	public void setPoderFisico(int poderFisico) {
		this.poderFisico = poderFisico;
	}

	public boolean puedeAtacar() {
		return estamina > 0;
	}

	public void serCurado() {
		this.salud = 100;
	}

	public void serEnergizado() {
		this.estamina = 100;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public int getNivel() {
		return nivel;
	}

	public int getEstamina() {
		return estamina;
	}

	public void setEstamina(int estamina) {
		this.estamina = estamina;
	}

	public int getExperiencia() {
		return experiencia;
	}

	public void setExperiencia(int experiencia) {
		this.experiencia = experiencia;
	}

	public int getDefensa() {
		return defensa;
	}

	public int getPoderMagico() {
		return poderMagico;
	}

	public int getPoderFisico() {
		return poderFisico;
	}

	public void setSalud(int salud) {
		this.salud = salud;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

}
