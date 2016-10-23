package mergame.individuos;

import java.util.HashMap;
import java.util.Map;

import mergame.skill.Habilidad;
import mergame.skill.hechizo.Hechizo;
import mergame.skill.hechizo.HechizoFuego;

public class Criatura extends Individuo {
	private Map <String, Hechizo> libro;
	
	public Criatura(){
		this.libro = new HashMap<String, Hechizo>();
		libro.put("Fuego", new HechizoFuego());
		//VER VALORES
		this.poderFisico = 2;
		this.poderMagico = 3;
	}
	
	@Override
	public void lanzarSkill(Individuo atacado, String nombreSkill){
		Habilidad skill = this.libro.get(nombreSkill);
		
		skill.realizarHabilidad(this, atacado);
	}

}
