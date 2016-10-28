package mergame.individuos.personajes;

import mergame.casta.EsDeCasta;
import mergame.individuos.Individuo;
import mergame.skill.Habilidad;

public abstract class Personaje extends Individuo {
	protected double experiencia = 0;
	protected String nombre;
	public EsDeCasta casta;

	/* GETTERs */

	public EsDeCasta getCasta() {
		return casta;
	}

	public int getSalud() {
		return this.salud;
	}

	public int getPuntosDeAtaqueFisico() {
		return this.poderFisico;
	}

	public int getPuntosDeAtaqueMagico() {
		return this.poderMagico;
	}

	public int getPuntosDeDefensa() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/* FIN GETTERs */

	public void setCasta(EsDeCasta casta) {
		this.casta = casta;

		// VER DANIOS SEGUN NIVEL E ITEMS EQUIPADOS
		this.poderFisico = this.casta.getPoderFisicoBase();
		this.poderMagico = this.casta.getPoderMagicoBase();
	}

	protected void despuesDeAtacar() {
	}

	protected abstract int calcularPuntosDeAtaque();

	public void atacar(Individuo victima) {
		if (puedeAtacar()) {
			victima.serAtacado(this.getPoderFisico());
			this.estamina--;
		}
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

	public void consumoElixir(int puntos) {

	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void lanzarSkill(Individuo atacado, String nombreSkill) {
		Habilidad skill = this.casta.getSkill(nombreSkill);

		skill.realizarHabilidad(this, atacado);
	}

}
