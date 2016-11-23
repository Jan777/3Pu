package servidor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Scanner;

import org.codehaus.jackson.map.ObjectMapper;

public class HiloRefresco implements Runnable {

	private Usuario usuario;
	private Scanner input;
	private String mensaje = "";
	private ArrayList<Usuario> listaDeConexiones = new ArrayList<>();
	private String nickName;
	private ObjectMapper mapper;
	private PrintWriter out;

	public HiloRefresco() {

	}

	public HiloRefresco(ArrayList<Usuario> listaDeConexiones, Usuario nuevoUsuario)
			throws IOException {
		this.listaDeConexiones = listaDeConexiones;
		this.usuario = nuevoUsuario;

	}

	@Override
	public void run() {
		mapper = new ObjectMapper();
		List<String> nombresPersonajesConectados = new ArrayList<>();
		int bandera=0;
		while (bandera < this.listaDeConexiones.size()) {
			bandera ++;
			for (Usuario personajesConectados : this.listaDeConexiones) {
				nombresPersonajesConectados.add(personajesConectados.getPersonaje().getNombre());
			}

			MensajeListaDePersonajesConectados listaDeConectados = new MensajeListaDePersonajesConectados(
					nombresPersonajesConectados);

			try {
				String jsonInString = mapper.writeValueAsString(listaDeConectados);
				for (Usuario personajesConectados : this.listaDeConexiones) {
					this.out = new PrintWriter(personajesConectados.getSocket().getOutputStream());
					out.println(jsonInString);
					out.flush();
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
