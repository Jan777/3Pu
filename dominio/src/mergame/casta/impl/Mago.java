package mergame.casta.impl;

import java.util.HashMap;
import java.util.Map;

import mergame.afectables.Afectable;
import mergame.afectables.Atacable;
import mergame.casta.EsDeCasta;
import mergame.hechizos.Hechizo;
import mergame.hechizos.HechizoCongelar;
import mergame.hechizos.HechizoCurar;
import mergame.hechizos.HechizoFuego;

public class Mago implements EsDeCasta{
	private Map <String, Hechizo> libro;
	
	public Mago(){
		this.libro = new HashMap<String, Hechizo>();
		libro.put("Curar", new HechizoCurar());
		libro.put("Congelar", new HechizoCongelar());
		libro.put("Fuego",new HechizoFuego());
	}
	@Override
	public void lanzarSkill1(Afectable a) {
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public void lanzarSkill2(Afectable a) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void lanzarSkill3(Afectable a) {
		// TODO Auto-generated method stub
		
	}

}
