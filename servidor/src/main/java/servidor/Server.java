package servidor;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Server {
	// SALAS DE CHAT DISPONIBLES
	public static ArrayList<UsuarioEnServidor> listaDeConexiones = new ArrayList<>();
	public static ArrayList<UsuarioEnServidor> listaDeConexionesSala2 = new ArrayList<>();
	public static ArrayList<UsuarioEnServidor> listaDeConexionesSala3 = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		try {
			final int PORT = 5553;
			ServerSocket server = new ServerSocket(PORT);
			ObjectMapper mapper = new ObjectMapper();

			while (true) {
				System.out.println("Esperando un cliente");
				Socket socket = server.accept();

				Scanner sc = new Scanner(socket.getInputStream());
				String input = sc.nextLine();

				MensajeLogin login = mapper.readValue(input, MensajeLogin.class);

				// String [] mundoUsuPass = sc.nextLine().split(" ");
				// int numMundoLog = Integer.parseInt(mundoUsuPass[0]);
				// String usuarioLog = mundoUsuPass[1];
				// String passwordLog = mundoUsuPass[2];

				if (login.getUsuario().equals("test") && login.getPass().equals("test")) {
					UsuarioEnServidor nuevoUsuario = new UsuarioEnServidor(socket, login.getMundo(), login.getUsuario(),
							login.getPass());

					System.out.println("Syso de servidor: conectaste bien");
					Socket tempSocket = nuevoUsuario.getSocket();
					PrintWriter tempOut = new PrintWriter(tempSocket.getOutputStream());
					tempOut.println("Datos Correctos");
					tempOut.flush();

					// Este hilo es "como un método" que va a ser llamado cuando
					// podamos crear un hilo "general" que escuche y sepa
					// determinar distintas peticiones. Por ahora obligamos al
					// usuario a crear un personaje
					CrearPersonajeThread hiloCrearPersonaje;
					listaDeConexiones.add(nuevoUsuario);
					hiloCrearPersonaje = new CrearPersonajeThread(listaDeConexiones, nuevoUsuario,
							login.getUsuario());
					Thread nuevoProcesoParalelo1 = new Thread(hiloCrearPersonaje);
					nuevoProcesoParalelo1.start();
					
					// switch (login.getMundo()) {
					// case 1:
					// listaDeConexionesSala1.add(nuevoUsuario);
					// chat = new ServerThread(listaDeConexionesSala1,
					// nuevoUsuario, login.getUsuario());
					// Thread nuevoProcesoParalelo1 = new Thread(chat);
					// nuevoProcesoParalelo1.start();
					// break;
					// case 2:
					// listaDeConexionesSala2.add(nuevoUsuario);
					// chat = new ServerThread(listaDeConexionesSala2,
					// nuevoUsuario, login.getUsuario());
					// Thread nuevoProcesoParalelo2 = new Thread(chat);
					// nuevoProcesoParalelo2.start();
					// break;
					// case 3:
					// listaDeConexionesSala3.add(nuevoUsuario);
					// chat = new ServerThread(listaDeConexionesSala3,
					// nuevoUsuario, login.getUsuario());
					// Thread nuevoProcesoParalelo3 = new Thread(chat);
					// nuevoProcesoParalelo3.start();
					// break;
					// default:
					// break;
					// }
					System.out.println("Client conectado a Sala " + login.getMundo() + " desde: "
							+ nuevoUsuario.getSocket().getLocalAddress().getHostName());
				} else {
					System.out.println("Seras desconectado");
					Socket tempSocket = socket;
					PrintWriter tempOut = new PrintWriter(tempSocket.getOutputStream());
					tempOut.println("Datos Incorrectos");
					tempOut.flush();
					socket.close();
				}

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
