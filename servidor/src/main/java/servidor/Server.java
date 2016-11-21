package servidor;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Server {
    //SALAS DE CHAT DISPONIBLES
    public static ArrayList<Socket> listaDeConexionesSala1 = new ArrayList<>();
    public static ArrayList<Socket> listaDeConexionesSala2 = new ArrayList<>();
    public static ArrayList<Socket> listaDeConexionesSala3 = new ArrayList<>();
    //Estas van a ser las listas con los nombres de los Personajes Activos en el juego (actualiza el comboAtacables)
    public static ArrayList<String> listaPersonajesSala1 = new ArrayList<>();
    public static ArrayList<String> listaPersonajesSala2 = new ArrayList<>();
    public static ArrayList<String> listaPersonajesSala3 = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        try {
        	
            final int PORT = 5553;
            ServerSocket server = new ServerSocket(PORT);
            ObjectMapper mapper = new ObjectMapper();

            while (true) {
                System.out.println("Esperando un cliente");
                Socket socket = server.accept();
                
                System.out.println("Se escuchó a un cliente");

                Scanner sc = new Scanner(socket.getInputStream());
                String [] mundoUsuPass = sc.nextLine().split(" ");
                int numMundo = Integer.parseInt(mundoUsuPass[0]);
                String usuario = mundoUsuPass[1];
                String password = mundoUsuPass[2];
                
                if(usuario.equals("test") && password.equals("test")){
                	System.out.println("Syso de servidor: conectaste bien");
                	Socket tempSocket = socket;
                	PrintWriter tempOut = new PrintWriter(tempSocket.getOutputStream());
                	tempOut.println("Datos Correctos");
                	tempOut.flush();

                ServerThread chat;
                switch (numMundo) {
                    case 1:
                        listaDeConexionesSala1.add(socket);
<<<<<<< HEAD
                        chat = new ServerThread(socket, listaDeConexionesSala1, user.getNombre(), listaPersonajesSala1);
                        listaPersonajesSala1.add(user.getNombre());
=======
                        chat = new ServerThread(socket, listaDeConexionesSala1, usuario);
>>>>>>> 02cadf39c5892dd09c7c7894a5fe543840c9ed0f
                        Thread nuevoProcesoParalelo1 = new Thread(chat);
                        nuevoProcesoParalelo1.start();
                        break;
                    case 2:
                        listaDeConexionesSala2.add(socket);
<<<<<<< HEAD
                        chat = new ServerThread(socket, listaDeConexionesSala2, user.getNombre(), listaPersonajesSala2);
=======
                        chat = new ServerThread(socket, listaDeConexionesSala2, usuario);
>>>>>>> 02cadf39c5892dd09c7c7894a5fe543840c9ed0f
                        Thread nuevoProcesoParalelo2 = new Thread(chat);
                        nuevoProcesoParalelo2.start();
                        break;
                    case 3:
                        listaDeConexionesSala3.add(socket);
<<<<<<< HEAD
                        chat = new ServerThread(socket, listaDeConexionesSala3, user.getNombre(), listaPersonajesSala3);
=======
                        chat = new ServerThread(socket, listaDeConexionesSala3, usuario);
>>>>>>> 02cadf39c5892dd09c7c7894a5fe543840c9ed0f
                        Thread nuevoProcesoParalelo3 = new Thread(chat);
                        nuevoProcesoParalelo3.start();
                        break;
                    default:
                        break;
                }
                System.out.println("Client conectado a Sala " + numMundo + " desde: " + socket.getLocalAddress().getHostName());
            }
            else{
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
