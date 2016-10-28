package mergame.individuos.personajes.impl;

import mergame.casta.EsDeCasta;
import mergame.individuos.Individuo;
import mergame.individuos.personajes.Personaje;
import mergame.skill.Habilidad;

public abstract class PersonajeImpl implements Personaje {
	protected String tipo;
	protected int salud;
	protected int estamina;	
	protected int nivel;
	protected int experiencia;
	protected int defensa;
	protected int poderMagico;
	protected int poderFisico;
	protected boolean congelado;
	protected String nombre;
	public EsDeCasta casta;
	protected int cantidadAtaquesRecibidos;
	
	public PersonajeImpl(){
		this.salud = 100;
		this.estamina = 100;	
		this.nivel = 1;
		this.experiencia = 0;
		this.defensa = 10;
		this.poderMagico = 10;
		this.poderFisico = 10;
		this.experiencia = 0;
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
}
