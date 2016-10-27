package mergame.individuos.personajes;

import mergame.casta.EsDeCasta;
import mergame.individuos.Individuo;
import mergame.skill.Habilidad;

public abstract class Personaje extends Individuo{
	protected double experiencia =0;
	protected String nombre;
	protected EsDeCasta casta;
	
	public EsDeCasta getCasta() {
		return casta;
	}

	public void setCasta(EsDeCasta casta) {
		this.casta = casta;
		
		//VER DAÑOS SEGUN NIVEL E ITEMS EQUIPADOS
		this.poderFisico = this.casta.getPoderFisicoBase();
		this.poderMagico = this.casta.getPoderMagicoBase();
	}

	protected void despuesDeAtacar() { }
	
	protected abstract boolean puedeAtacar();
	protected abstract int calcularPuntosDeAtaque();
	
	public void serCurado() {
		this.salud = 100;
	}

	public void serEnergizado() {
		this.energia = 100;
	}
	
	public int getSalud() {
		return this.salud;
	}

	public int obtenerPuntosDeAtaqueFisico() {
		return this.poderFisico;
	}
	
	public int obtenerPuntosDeAtaqueMagico(){
		return this.poderMagico;
	}
	
	public int obtenerPuntosDeDefensa() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public void consumoElixir(int puntos){
		
	}

	public String getNombre() {
		return this.nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void lanzarSkill(Individuo atacado, String nombreSkill){
		Habilidad skill = this.casta.getSkill(nombreSkill);
		
		skill.realizarHabilidad(this, atacado);
	}
	
}
