package servidor;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.swing.text.GapContent;

import org.codehaus.jackson.map.ObjectMapper;

import com.google.gson.Gson;

import main.java.mergame.casta.EsDeCasta;
import main.java.mergame.casta.impl.Guerrero;
import main.java.mergame.casta.impl.Mago;
import main.java.mergame.individuos.personajes.Personaje;
import main.java.mergame.individuos.personajes.impl.Elfo;
import main.java.mergame.individuos.personajes.impl.Enano;
import main.java.mergame.individuos.personajes.impl.Humano;
import main.java.mergame.individuos.personajes.impl.Orco;

public class ServerThread implements Runnable {// The Runnable interface should
												// be implemented by any class
												// whose instances are intended
												// to be executed by a thread.
	UsuarioEnServidor usuario;
	Scanner input;
	String mensaje = "";
	ArrayList<UsuarioEnServidor> listaDeConexiones = new ArrayList<>();
	String nickName;
	private Map<String, Personaje> personajes;
	private Map<String, EsDeCasta> casta;

	// CONSTRUCTOR DEL CHAT
	public ServerThread(ArrayList<UsuarioEnServidor> listaDeSala, UsuarioEnServidor usuario, String alias) {
		this.usuario = usuario;
		this.listaDeConexiones = listaDeSala;
		this.nickName = alias;
	}

	public boolean estaConectado() throws IOException {
		if (!this.usuario.getSocket().isConnected()) {// SI EL SOCKET ESTA
														// DESCONECTADO LO
														// ELIMINA DE MI LISTA
														// DE CONEXIONES.
			for (int x = 0; x < this.listaDeConexiones.size(); x++) {
				if (this.listaDeConexiones.get(x).getSocket() == this.usuario.getSocket()) {
					this.listaDeConexiones.remove(x);
				}
			}
			return false;
		}
		return true;
	}

	@Override
	public void run() {// SOBRECARGAR DE RUN QUE SE REALIZARA CUANDO INICIE EL
						// THREAD CREADO EN "SERVIDOR"

		try {
			this.input = new Scanner(this.usuario.getSocket().getInputStream()); // OBTENGO
																					// EL
																					// CANAL
																					// DE
																					// ENTRADA
																					// DEL
																					// SOCKET
			ObjectMapper mapper = new ObjectMapper();

			
			//Este mapa ayuda en la instanciación del personaje ya que, según la raza recibida en el jackson, crea el personaje de la raza correspondiente
			//Lo mismo hace con la casta
			personajes = new HashMap<>();
			personajes.put("Humano", new Humano());
			personajes.put("Elfo", new Elfo());
			personajes.put("Enano", new Enano());
			personajes.put("Orco", new Orco());

			casta = new HashMap<>();
			casta.put("Mago", new Mago());
			casta.put("Guerrero", new Guerrero());
			
			// Creamos una bandera para limitar el while a una sola vuelta. De
			// esa manera no se queda escuchando.
			// La idea es implementar esto una vez y que luego se abra otro hilo
			// que sí se quede esperando mensajes.
			// O sea que este sería un hilo que se corre solo una vez para
			// instanciar al personaje del cliente en el servidor.
			int bandera = 0;
			while (bandera == 0) {
				bandera = 1;
				if (this.estaConectado()) { // VERIFICO QUE EL SOCKET ESTE
											// CONECTADO, SI NO LO ESTA CIERRO
											// ESE SOCKET.
					if (!this.input.hasNext()) { // SI NO HA TIENE MENSAJE
													// ACTUAL BUCLEO A LA ESPERA
													// DE UNO
						return;
					}

					// Gson gson = new Gson();
					// MensajeCrear personajeDelUsuario =
					// mapper.readValue(this.mensaje, MensajeCrear.class);
					this.mensaje = this.input.nextLine(); // GUARDO EN MENSAJE
					//Capturamos el mensaje
					System.out.println(this.mensaje);
					//Lo guardamos en el mapa de objetos de jackson
					MensajeCrearPersonajeEnServidor mensajeCrear = mapper.readValue(this.mensaje,
							MensajeCrearPersonajeEnServidor.class);
					//Usando lo recibido por mensaje, instanciamos al personaje y se lo pasamos al usuario.
					Personaje personaje = this.personajes.get(mensajeCrear.getRaza());
					personaje.setCasta(this.casta.get(mensajeCrear.getCasta()));
					personaje.setNombre(mensajeCrear.getNombre());

					//Seteamos el personaje en el usuario (cliente actualmente conectado al juego)
					usuario.setPersonaje(personaje);

					// personajeDelUsuario.ejecutarMensaje(listaDeConexiones);
					System.out.println(usuario.getPersonaje().getNombre());
					
					
					//Esta parte del código se va a utilizar porque es la que "re manda" un mismo mensaje a todos los clientes conectados:
					
					// for (int x = 0; x < this.listaDeConexiones.size(); x++) {
					// // RECORRE TODA LA LISTA DE CONEXIONES DE LA SALA PARA
					// ENVIAR EL MENSAJE RECIBIDO A TODOS.
					// Socket tempSocket = this.listaDeConexiones.get(x);
					// PrintWriter tempOut = new
					// PrintWriter(tempSocket.getOutputStream()); // OBTENGO EL
					// CANAL DE SALIDA PARA ENVIARLE EL MENSAJE A EL SOCKET
					// tempOut.println(this.nickName + ": " + this.mensaje); //
					// ENVIA EL MENSAJE
					// tempOut.flush(); // LIMPIO EL BUFFER DE SALIDA
					// System.out.println("mensaje enviado a: " +
					// tempSocket.getLocalAddress().getHostName());
					// }
				} else {
					this.usuario.getSocket().close();
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
