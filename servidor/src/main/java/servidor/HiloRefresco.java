package servidor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Scanner;

import org.codehaus.jackson.map.ObjectMapper;

public class HiloRefresco implements Runnable {

	private UsuarioEnServidor usuario;
	private Scanner input;
	private String mensaje = "";
	private ArrayList<UsuarioEnServidor> listaDeConexiones = new ArrayList<>();
	private String nickName;
	private ObjectMapper mapper;
	private PrintWriter out;

	public HiloRefresco() {

	}

	public HiloRefresco(ArrayList<UsuarioEnServidor> listaDeConexiones, UsuarioEnServidor nuevoUsuario)
			throws IOException {
		this.listaDeConexiones = listaDeConexiones;
		this.usuario = nuevoUsuario;

	}

	@Override
	public void run() {
		mapper = new ObjectMapper();
		List<String> nombresPersonajesConectados = new ArrayList<>();

		while (true) {
			for (UsuarioEnServidor personajesConectados : this.listaDeConexiones) {
				nombresPersonajesConectados.add(personajesConectados.getPersonaje().getNombre());
			}

			MensajeListaDePersonajesConectados listaDeConectados = new MensajeListaDePersonajesConectados(
					nombresPersonajesConectados);

			try {
				String jsonInString = mapper.writeValueAsString(listaDeConectados);
				for (UsuarioEnServidor personajesConectados : this.listaDeConexiones) {
					this.out = new PrintWriter(personajesConectados.getSocket().getOutputStream());
					out.println(jsonInString);
					out.flush();
				}
				listaDeConectados.avisar();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
