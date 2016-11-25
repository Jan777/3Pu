package servidor;

import org.codehaus.jackson.map.ObjectMapper;
import servidor.comunicacion.Login;
import servidor.db.ConexionSQLite;
import servidor.db.IniciarBD;
import servidor.usuario.Usuario;
import servidor.usuario.UsuarioThread;
import servidor.service.ServicioLogin;
import servidor.service.ServicioUsuario;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    private ConexionSQLite conexionSQLite;
    private ServicioUsuario servicioUsuario;
    private ServerSocket serverSocket;
    private ObjectMapper mapper;
    private static final int PORT = 5553;

    public Server() {
        this.conexionSQLite = ConexionSQLite.getInstancia();
        this.servicioUsuario = ServicioUsuario.getInstancia();
        this.mapper = new ObjectMapper();
        this.initServerSocket();
        IniciarBD.initDB();
    }

    public void init() {
        try {
            while (Boolean.TRUE) {
                System.out.println("Esperando un cliente");
                Socket socket = serverSocket.accept();

                Scanner sc = new Scanner(socket.getInputStream());
                String mensaje = sc.nextLine();

                String codigoMensaje = mensaje.substring(0, 4);
                mensaje = mensaje.substring(4, mensaje.length());

                switch (codigoMensaje) {
                case "LOGI":
                    Login login = mapper.readValue(mensaje, Login.class);
                    ServicioLogin servicioLogin = new ServicioLogin();
                    if (servicioLogin.autenticar(login)) {
                        Usuario usuario = new Usuario(socket, login.getUsuario());
                        System.out.println("Usuario " + usuario.getUsuario() + " conectado");
                        this.servicioUsuario.addUsuario(usuario);
                        PrintWriter tempOut = new PrintWriter(usuario.getSocket().getOutputStream());

                        UsuarioThread usuarioThread = new UsuarioThread(usuario);
                        Thread t = new Thread(usuarioThread);
                        t.start();

                        tempOut.println("Datos Correctos");
                        tempOut.flush();
                    } else {
                        Socket tempSocket = socket;
                        PrintWriter tempOut = new PrintWriter(tempSocket.getOutputStream());
                        tempOut.println("Datos Incorrectos");
                        tempOut.flush();
                        socket.close();
                    }
                    break;
                case "CREA":
                    Login nuevoUser = mapper.readValue(mensaje, Login.class);
                    new ServicioLogin().crearNuevoUsuario(nuevoUser);
                    socket.close();
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            this.conexionSQLite.cerrarConexion();
        }
    }

    private void initServerSocket(){
        try {
            this.serverSocket = new ServerSocket(PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
