package cliente.hilos;

import cliente.comunicacion.PersonajesConectados;
import cliente.usuario.Usuario;
import cliente.vistas.ViewGame;
import org.codehaus.jackson.map.ObjectMapper;

import javax.swing.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class GameThread implements Runnable {
    private Usuario usuario;
    private Socket socket;
    private ObjectMapper mapper;
    private Scanner sc;
    private PrintWriter pw;
    private ViewGame frame;

    public GameThread(Usuario usuario, ViewGame frame) {
        this.usuario = usuario;
        this.socket = usuario.getSocket();
        this.frame = frame;
        mapper = new ObjectMapper();
    }

    @Override
    public void run() {
        try {
            sc = new Scanner(this.usuario.getSocket().getInputStream());
            pw = new PrintWriter(this.usuario.getSocket().getOutputStream());

            while (Boolean.TRUE) {
                if(sc.hasNext()) {
                    String mensaje = sc.next();
                    String codigoMensaje = mensaje.substring(0, 4);
                    mensaje = mensaje.substring(4, mensaje.length());

                    if(codigoMensaje.equals("INGR")) {
                        System.out.println(mensaje);
                        PersonajesConectados listaRecibida = mapper.readValue(mensaje,
                            PersonajesConectados.class);

                        DefaultListModel modeloLista = new DefaultListModel();

                        for (String nombrePersonajes : listaRecibida.getListaPersonajes()) {
                            if(!usuario.getUsuario().equals(nombrePersonajes))
                                modeloLista.addElement(nombrePersonajes);
                        }
                        frame.getListaPersonajesConectados().setModel(modeloLista);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            sc.close();
            pw.close();
        }
    }
}
