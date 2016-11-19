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

    public static void main(String[] args) throws Exception {
        try {
            final int PORT = 5553;
            ServerSocket server = new ServerSocket(PORT);
            ObjectMapper mapper = new ObjectMapper();

            while (true) {
                System.out.println("Esperando un cliente");
                Socket socket = server.accept();

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
                        chat = new ServerThread(socket, listaDeConexionesSala1, usuario);
                        Thread nuevoProcesoParalelo1 = new Thread(chat);
                        nuevoProcesoParalelo1.start();
                        break;
                    case 2:
                        listaDeConexionesSala2.add(socket);
                        chat = new ServerThread(socket, listaDeConexionesSala2, usuario);
                        Thread nuevoProcesoParalelo2 = new Thread(chat);
                        nuevoProcesoParalelo2.start();
                        break;
                    case 3:
                        listaDeConexionesSala3.add(socket);
                        chat = new ServerThread(socket, listaDeConexionesSala3, usuario);
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
