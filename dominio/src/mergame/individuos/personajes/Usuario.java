package mergame.individuos.personajes;

import java.util.HashMap;
import java.util.Map;

import mergame.individuos.personajes.impl.PersonajeImpl;

public class Usuario {
	private String nombre;
	private String password;
	private Map<String,PersonajeImpl> personajes = new HashMap<String, PersonajeImpl>();
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Usuario(String nombre, String password) {
		super();
		this.nombre = nombre;
		this.password = password;
	}
	
	public void agregarPesonaje(PersonajeImpl personaje){
		if(personajes.size() < 3){
			this.personajes.put(personaje.getNombre(), personaje);
		}
	}
	
	public PersonajeImpl getPersonaje(String nombre){
		return this.personajes.get(nombre);
	}
}
