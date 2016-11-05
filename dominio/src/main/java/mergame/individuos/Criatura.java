package main.java.mergame.individuos;

import java.util.HashMap;
import java.util.Map;

import main.java.mergame.individuos.personajes.Personaje;
import main.java.mergame.individuos.personajes.impl.PersonajeImpl;
import main.java.mergame.itemsConDecorator.PersonajeDecorator;
import main.java.mergame.itemsConDecorator.impl.ConEspadaSkofnung;
import main.java.mergame.skill.Habilidad;
import main.java.mergame.skill.hechizo.Hechizo;
import main.java.mergame.skill.hechizo.HechizoFuego;

public class Criatura implements Individuo {
	private Map <String, Hechizo> libro;
	protected int salud;
	protected int estamina;	
	protected int nivel;
	protected int experiencia;
	protected int defensa;
	protected int poderMagico;
	protected int poderFisico;
	protected boolean congelado;
	
	protected final int expBaseOtorgada = 40;
	
	public Criatura(){
		this.libro = new HashMap<String, Hechizo>();
		libro.put("Fuego", new HechizoFuego());
		//VER VALORES
		this.poderFisico = 2;
		this.poderMagico = 3;
		salud = 100;
		estamina = 100;
		defensa = 150;
		nivel = 1;
	}
	
	public Criatura(int salud){
		this.libro = new HashMap<String, Hechizo>();
		libro.put("Fuego", new HechizoFuego());
		//VER VALORES
		this.poderFisico = 2;
		this.poderMagico = 3;
		this.salud = salud;
	}
	
	public int getSalud() {
		return salud;
	}

	public void setSalud(int salud) {
		this.salud = salud;
	}

	public void lanzarSkill(Individuo atacado, String nombreSkill){
		Habilidad skill = this.libro.get(nombreSkill);
		
		skill.realizarHabilidad(this, atacado);
	}

	@Override
	public void serHechizado(int vidaCurada, int vidaQuitada, int turnosCongelado) {
		
	}

	@Override
	public void serAtacado(int danio) {
		if((danio - (this.getPuntosDeDefensa() * 30 / 100)) < 0)
			this.salud -= 1;
		else
			this.salud -= danio - (this.getPuntosDeDefensa() * 30 / 100);	
		
	}

	@Override
	public void atacar(Individuo victima) {
		
	}

	@Override
	public boolean estaVivo() {
		return this.salud > 0 ? true : false;
	}

	public Personaje droppeo(Personaje asesino) {
		//como prueba solo devuelve un tipo de arma
		asesino = new ConEspadaSkofnung(asesino);
		return asesino;
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
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
		return 0;
	}
	
	@Override
	public int getExpOtorgada() {
		return this.nivel*this.expBaseOtorgada;
	}

	
	@Override
	public int getExperiencia() {
		return experiencia;
	}
	
	

}
