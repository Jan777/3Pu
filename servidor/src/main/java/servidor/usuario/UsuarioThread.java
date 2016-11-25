package servidor.usuario;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import servidor.comunicacion.PersonajesConectados;
import servidor.service.ServicioUsuario;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UsuarioThread implements Runnable{
    private Usuario usuario;
    private ServicioUsuario servicioUsuario;
    private Scanner sc;
    private PrintWriter pw;
    private ObjectMapper mapper;

    public UsuarioThread(Usuario usuario) {
        this.usuario = usuario;
        this.servicioUsuario = ServicioUsuario.getInstancia();
        this.mapper = new ObjectMapper();
    }

    @Override
    public void run() {
        try {
            sc = new Scanner(usuario.getSocket().getInputStream());
            pw = new PrintWriter(usuario.getSocket().getOutputStream());

            while(Boolean.TRUE) {
                if(sc.hasNext()) {
                    String mensaje = sc.nextLine();
                    String codigoMensaje = mensaje.substring(0, 4);
                    mensaje = mensaje.substring(4, mensaje.length());

                    switch (codigoMensaje) {
                        case "INGR" :
                            System.out.println("Ingreso el usuario " + usuario.getUsuario());
                            actualizarUsuarios();
                            break;
                        case "MOVI" :
                            movimientoDetectado(mensaje);
                            break;
                        case "PERS" :
                            break;
                        case "LOUT" :
                            logoutUsuario(mensaje);
                            break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            sc.close();
            pw.close();
        }
    }

    private void logoutUsuario(String usuario) {
        servicioUsuario.removerUsuario(usuario);
        actualizarUsuarios();
        System.out.println("El usuario " + this.usuario.getUsuario() + " se ha desconectado.");
    }

    private void actualizarUsuarios() {
        try {
            List<String> conectados = new ArrayList<>();
            for (Usuario usuario : servicioUsuario.getUsuarios()) {
                conectados.add(usuario.getUsuario());
            }

            PersonajesConectados pc = new PersonajesConectados(conectados);
            String mensaje = mapper.writeValueAsString(pc);
            mensajeBroadCast("INGR" + mensaje);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void movimientoDetectado(String mensaje) {
        try {
            UsuarioPosicion posicion = mapper.readValue(mensaje, UsuarioPosicion.class);
            servicioUsuario.getUsuario(posicion.getUsuario()).setPosicion(posicion);

            mensajeBroadCast("MOVI" + mensaje);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void mensajeBroadCast(String mensaje) throws IOException {
        for (Usuario usr : servicioUsuario.getUsuarios()) {
            PrintWriter pw = new PrintWriter(usr.getSocket().getOutputStream());

            pw.println(mensaje);
            pw.flush();
        }
    }
}
