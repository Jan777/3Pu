package mergame.hechizos;

import mergame.afectables.Hechizable;


public class HechizoCurar extends Hechizo{

	public void hechizar(Hechizable hechizable){
		hechizable.serHechizado(0, 50);
	}
}
