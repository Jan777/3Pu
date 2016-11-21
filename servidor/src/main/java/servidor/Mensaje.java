package servidor;

import java.net.Socket;
import java.util.ArrayList;

public class Mensaje {
	protected Usuario usuario;
	
	protected Mensaje(Usuario usuario){
		this.usuario = usuario;
	}
	
	protected void ejecutarMensaje(ArrayList<Usuario> arrayUsuario){
	}
	
}
