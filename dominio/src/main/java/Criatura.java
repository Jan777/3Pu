package decorator;

import java.util.HashMap;
import java.util.Map;

public class Criatura implements Atacable, Hechizable {
	private Map <String, Hechizo> libro;
	
	public Criatura(){
		this.libro = new HashMap<String, Hechizo>();
		libro.put("Curar", new HechizoCurar());
	}
	
	public void lanzarHechizo(String hechizo, Hechizable hechizable){
	
		Hechizo hechizoALanzar = libro.get(hechizo);
		hechizoALanzar.hechizar(hechizable);
	}
	@Override
	public void serHechizado(int fuerza, int vida) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void serAtacado(int danio) {
		// TODO Auto-generated method stub
		
	}

}
