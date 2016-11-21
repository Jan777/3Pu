package servidor;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ServerThread implements Runnable {// The Runnable interface should be implemented by any class whose instances are intended to be executed by a thread.
    Usuario usuario;
    Scanner input;
    String mensaje = "";
    ArrayList<Usuario> listaDeConexiones = new ArrayList<>();
    String nickName;

    //CONSTRUCTOR DEL CHAT
    public ServerThread(ArrayList<Usuario> listaDeSala, Usuario usuario, String alias) {
        this.usuario = usuario;
        this.listaDeConexiones = listaDeSala;
        this.nickName = alias;
    }

    public boolean estaConectado() throws IOException {
        if (!this.usuario.getSocket().isConnected()) {// SI EL SOCKET ESTA DESCONECTADO LO ELIMINA DE MI LISTA DE CONEXIONES.
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
    public void run() {//SOBRECARGAR DE RUN QUE SE REALIZARA CUANDO INICIE EL THREAD CREADO EN "SERVIDOR"

        try {
            this.input = new Scanner(this.usuario.getSocket().getInputStream()); // OBTENGO EL CANAL DE ENTRADA DEL SOCKET
            ObjectMapper mapper = new ObjectMapper();

            while (true) {
                if (this.estaConectado()) { // VERIFICO QUE EL SOCKET ESTE CONECTADO, SI NO LO ESTA CIERRO ESE SOCKET.
                    if (!this.input.hasNext()) { // SI NO HA TIENE MENSAJE ACTUAL BUCLEO A LA ESPERA DE UNO
                        return;
                    }

                    this.mensaje = this.input.nextLine(); // GUARDO EN MENSAJE EL TEXTO RECIBIDO
                    //System.out.println(this.nickName + " dice: " + this.mensaje);
                    Mensaje mensajeNuevo = mapper.readValue(this.mensaje, Mensaje.class);
                    mensajeNuevo.ejecutarMensaje(this.listaDeConexiones);
                    System.out.println(this.usuario.getPersonaje().getSalud());


//                    for (int x = 0; x < this.listaDeConexiones.size(); x++) { // RECORRE TODA LA LISTA DE CONEXIONES DE LA SALA PARA ENVIAR EL MENSAJE RECIBIDO A TODOS.
//                        Socket tempSocket = this.listaDeConexiones.get(x);
//                        PrintWriter tempOut = new PrintWriter(tempSocket.getOutputStream()); // OBTENGO EL CANAL DE SALIDA PARA ENVIARLE EL MENSAJE A EL SOCKET
//                        tempOut.println(this.nickName + ": " + this.mensaje); // ENVIA EL MENSAJE
//                        tempOut.flush(); // LIMPIO EL BUFFER DE SALIDA
//                        System.out.println("mensaje enviado a: " + tempSocket.getLocalAddress().getHostName());
//                    }
                } else {
                    this.usuario.getSocket().close();
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
