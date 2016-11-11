package servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class ServerThread implements Runnable {// The Runnable interface should be implemented by any class whose instances are intended to be executed by a thread.
    private Socket socket;
    private Scanner input;
    private String mensaje = "";
    private ArrayList<Socket> listaDeConexiones = new ArrayList<>();
    ArrayList<String> listaDePersonajesActivos = new ArrayList<>();
    private String nickName;
    private DataOutputStream out;
    private DataInputStream in;

    //CONSTRUCTOR DEL CHAT
    public ServerThread(Socket socket, ArrayList<Socket> listaDeSala, String alias, ArrayList<String> listaDePersonajesActivos) {
        this.socket = socket;
        this.listaDeConexiones = listaDeSala;
        this.listaDePersonajesActivos = listaDePersonajesActivos;
        this.nickName = alias;
    }

    public boolean estaConectado() throws IOException {
        if (!this.socket.isConnected()) {// SI EL SOCKET ESTA DESCONECTADO LO ELIMINA DE MI LISTA DE CONEXIONES.
            for (int x = 0; x < this.listaDeConexiones.size(); x++) {
                if (this.listaDeConexiones.get(x) == this.socket) {
                    this.listaDeConexiones.remove(x);
                }
            }
            return false;
        }
        return true;
    }

    @Override
    public void run() {//SOBRECARGA DE RUN QUE SE REALIZARA CUANDO INICIE EL THREAD CREADO EN "SERVIDOR"

        try {
        	in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
        	
            this.input = new Scanner(this.socket.getInputStream()); // OBTENGO EL CANAL DE ENTRADA DEL SOCKET

            while (true) {
                if (this.estaConectado()) { // VERIFICO QUE EL SOCKET ESTE CONECTADO, SI NO LO ESTA CIERRO ESE SOCKET.
                    if (!this.input.hasNext()) { // SI NO TIENE MENSAJE ACTUAL, BUCLEO A LA ESPERA DE UNO
                        return;
                    }

                    this.mensaje = this.input.nextLine(); // GUARDO EN MENSAJE EL TEXTO RECIBIDO
                    System.out.println(this.nickName + " dice: " + this.mensaje);

                    for (int x = 0; x < this.listaDeConexiones.size(); x++) { // RECORRE TODA LA LISTA DE CONEXIONES DE LA SALA PARA ENVIAR EL MENSAJE RECIBIDO A TODOS.
                    	 String personajes = "";
                         for(String nombre : listaDePersonajesActivos){
                         	personajes += nombre+";";
                         }
                         out.writeUTF(personajes);
                         out.flush();
                    	
                        Socket tempSocket = this.listaDeConexiones.get(x);
                        PrintWriter tempOut = new PrintWriter(tempSocket.getOutputStream()); // OBTENGO EL CANAL DE SALIDA PARA ENVIARLE EL MENSAJE A EL SOCKET
                        tempOut.println(this.nickName + ": " + this.mensaje); // ENVIA EL MENSAJE
                        tempOut.flush(); // LIMPIO EL BUFFER DE SALIDA
                        System.out.println("mensaje enviado a: " + tempSocket.getLocalAddress().getHostName());
                        
                       
                    }
                } else {
                    this.socket.close();
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
