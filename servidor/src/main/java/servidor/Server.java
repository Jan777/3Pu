package servidor;

import org.codehaus.jackson.map.ObjectMapper;
import servidor.db.ConexionSQLite;
import servidor.db.IniciarBD;
import servidor.service.LoginService;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Server {
    //SALAS DE CHAT DISPONIBLES
    public static ArrayList<Usuario> listaDeConexionesSala1 = new ArrayList<>();
    public static ArrayList<Usuario> listaDeConexionesSala2 = new ArrayList<>();
    public static ArrayList<Usuario> listaDeConexionesSala3 = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        ConexionSQLite conexionSQLite = null;
        try {
            conexionSQLite = ConexionSQLite.getInstancia();
            final int PORT = 5553;
            ServerSocket server = new ServerSocket(PORT);
            ObjectMapper mapper = new ObjectMapper();
            IniciarBD iniciarBD = new IniciarBD();

            while (true) {
                System.out.println("Esperando un cliente");
                Socket socket = server.accept();

                Scanner sc = new Scanner(socket.getInputStream());
                String mensaje = sc.next();

                String codigoMensaje = mensaje.substring(0, 4);

                mensaje = mensaje.substring(4, mensaje.length());

                switch (codigoMensaje) {
                    case "LOGI":
                        Login login = mapper.readValue(mensaje, Login.class);
                        LoginService loginService = new LoginService();
                        if (loginService.autenticar(login)) {
                            Usuario nuevoUsuario = new Usuario(socket);

                            System.out.println("Syso de servidor: conectaste bien");
                            Socket tempSocket = nuevoUsuario.getSocket();
                            PrintWriter tempOut = new PrintWriter(tempSocket.getOutputStream());
                            tempOut.println("Datos Correctos");
                            tempOut.flush();

                            ServerThread chat;
                            switch (login.getMundo()) {
                                case 1:
                                    listaDeConexionesSala1.add(nuevoUsuario);
                                    chat = new ServerThread(listaDeConexionesSala1, nuevoUsuario, login.getUsuario());
                                    Thread nuevoProcesoParalelo1 = new Thread(chat);
                                    nuevoProcesoParalelo1.start();
                                    break;
                                case 2:
                                    listaDeConexionesSala2.add(nuevoUsuario);
                                    chat = new ServerThread(listaDeConexionesSala2, nuevoUsuario, login.getUsuario());
                                    Thread nuevoProcesoParalelo2 = new Thread(chat);
                                    nuevoProcesoParalelo2.start();
                                    break;
                                case 3:
                                    listaDeConexionesSala3.add(nuevoUsuario);
                                    chat = new ServerThread(listaDeConexionesSala3, nuevoUsuario, login.getUsuario());
                                    Thread nuevoProcesoParalelo3 = new Thread(chat);
                                    nuevoProcesoParalelo3.start();
                                    break;
                                default:
                                    break;
                            }
                            System.out.println("Client conectado a Sala " + login.getMundo() + " desde: " + nuevoUsuario.getSocket().getLocalAddress().getHostName());
                        } else {
                            System.out.println("Seras desconectado");
                            Socket tempSocket = socket;
                            PrintWriter tempOut = new PrintWriter(tempSocket.getOutputStream());
                            tempOut.println("Datos Incorrectos");
                            tempOut.flush();
                            socket.close();
                        }
                        break;
                    case "CREA":
                        Login nuevoUser = mapper.readValue(mensaje, Login.class);
                        new LoginService().crearNuevoUsuario(nuevoUser);
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            conexionSQLite.cerrarConexion();
        }
    }
}
