package cliente.hilos;

import cliente.comunicacion.Batalla;
import cliente.comunicacion.PersonajesConectados;
import cliente.mundo.ViewGame;
import cliente.mundo.partida.Jugador;
import cliente.mundo.ui.entidades.TipoPersonaje;
import cliente.usuario.Usuario;
import cliente.usuario.UsuarioPosicion;
import org.codehaus.jackson.map.ObjectMapper;

import java.awt.*;
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
            //El bloque de red, cuando llega un nuevo player los crea
            //Jugador jugadorRemoto1 = new Jugador("Albert1",TipoPersonaje.PJ_ORCO);
            //Jugador jugadorRemoto2 = new Jugador("Albert1",TipoPersonaje.PJ_ELFO);
            sc = new Scanner(this.usuario.getSocket().getInputStream());
            pw = new PrintWriter(this.usuario.getSocket().getOutputStream());

            while (Boolean.TRUE) {
                if(sc.hasNext()) {
                    String mensaje = sc.next();
                    String codigoMensaje = mensaje.substring(0, 4);
                    mensaje = mensaje.substring(4, mensaje.length());
                    PersonajesConectados pc;

                    switch (codigoMensaje) {
                        case "INGR" :
                            pc = mapper.readValue(mensaje,
                                    PersonajesConectados.class);

                            for(UsuarioPosicion usuarioPosicion : pc.getPosicionPersonajes()) {
                                if(!usuario.getUsuario().equals(usuarioPosicion.getUsuario())) {
                                    Jugador jugadorRemoto2 = new Jugador(usuarioPosicion.getUsuario(), TipoPersonaje.GetByName(usuarioPosicion.getPersonaje()));
                                    frame.getPartida().RegistrarJugador(jugadorRemoto2,false);
                                    frame.getPartida().actualizarJugador(usuarioPosicion.getUsuario(), new Point(usuarioPosicion.getX(),usuarioPosicion.getY()));
                                }
                            }
                            break;
                        case "MOVI" :
                            UsuarioPosicion posicion = mapper.readValue(mensaje, UsuarioPosicion.class);
                                if(!usuario.getUsuario().equals(posicion.getUsuario()))
                                    frame.getPartida().actualizarJugador(posicion.getUsuario(), new Point(posicion.getX(),posicion.getY ()));
                            break;
                        case "RFSH" :
                            pc = mapper.readValue(mensaje,
                                    PersonajesConectados.class);

                            for(UsuarioPosicion usuarioPosicion : pc.getPosicionPersonajes()) {
                                System.out.println(usuarioPosicion.getPersonaje().getSalud());
                            }
                            break;
                        case "BATA" :
                            Batalla batalla = mapper.readValue(mensaje, Batalla.class);
                            Jugador jugador1 = this.frame.getPartida().ObtenerJugadorPorNombre(batalla.getPersonajeAtacante());
                            Jugador jugador2 = this.frame.getPartida().ObtenerJugadorPorNombre(batalla.getPersonajeAtacado());
                            this.frame.getPartida().IniciarBatalla(jugador1,jugador2);
                            break;
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

