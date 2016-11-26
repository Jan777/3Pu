package servidor.usuario;

import main.java.mergame.casta.EsDeCasta;
import main.java.mergame.casta.impl.Guerrero;
import main.java.mergame.casta.impl.Mago;
import main.java.mergame.individuos.personajes.Personaje;
import main.java.mergame.individuos.personajes.impl.Elfo;
import main.java.mergame.individuos.personajes.impl.Enano;
import main.java.mergame.individuos.personajes.impl.Humano;
import main.java.mergame.individuos.personajes.impl.Orco;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import servidor.comunicacion.Batalla;
import servidor.comunicacion.MensajeCrearPersonaje;
import servidor.comunicacion.PersonajesConectados;
import servidor.service.ServicioUsuario;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class UsuarioThread implements Runnable{
    private Usuario usuario;
    private ServicioUsuario servicioUsuario;
    private Scanner sc;
    private PrintWriter pw;
    private ObjectMapper mapper;
    private Map<String, Personaje> personajes;
    private Map <String, EsDeCasta> casta;

    public UsuarioThread(Usuario usuario) {
        this.usuario = usuario;
        this.servicioUsuario = ServicioUsuario.getInstancia();
        this.mapper = new ObjectMapper();

        personajes = new HashMap<>();
        personajes.put("Humano", new Humano());
        personajes.put("Elfo", new Elfo());
        personajes.put("Enano", new Enano());
        personajes.put("Orco", new Orco());

        casta = new HashMap<>();
        casta.put("Mago", new Mago());
        casta.put("Guerrero", new Guerrero());
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
                            actualizarUsuarios();
                            break;
                        case "MOVI" :
                            movimientoDetectado(mensaje);
                            break;
                        case "PERS" :
                            insertarPersonaje(mensaje);
                            break;
                        case "LOUT" :
                            logoutUsuario(mensaje);
                            break;
                        case "BATA" :
                            iniciarBatalla(mensaje);
                            break;
                        case "FIGH" :
                            pelear(mensaje);
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

    private void pelear(String mensaje) {
        try {
            Batalla batalla = mapper.readValue(mensaje, Batalla.class);

            servicioUsuario.getUsuario(batalla.getPersonajeAtacante())
                    .getPersonaje()
                    .atacar(servicioUsuario.getUsuario(batalla.getPersonajeAtacado()).getPersonaje());

            List<UsuarioPosicion> conectados = new ArrayList<>();
            for (Usuario usuario : servicioUsuario.getUsuarios()) {
                conectados.add(usuario.getPosicion());
            }

            PersonajesConectados pc = new PersonajesConectados(conectados);
            mensaje = mapper.writeValueAsString(pc);
            mensajeBroadCast("RFSH" + mensaje);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void iniciarBatalla(String mensaje) {
        try {
            mensajeBroadCast("BATA" + mensaje);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void insertarPersonaje(String mensaje) {
        try {
            MensajeCrearPersonaje m = mapper.readValue(mensaje, MensajeCrearPersonaje.class);

            Personaje personaje = personajes.get(m.getRaza());
            personaje.setCasta(casta.get(m.getCasta()));
            personaje.setNombre(m.getNombre());

        synchronized (servicioUsuario.getUsuarios()) {
            servicioUsuario.getUsuario(m.getNombre())
                    .setPersonaje(personaje);
            servicioUsuario.getUsuario(m.getNombre())
                    .setPosicion(new UsuarioPosicion(servicioUsuario.getUsuario(m.getNombre())));
        }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void logoutUsuario(String usuario) {
        try {
            servicioUsuario.getUsuario(usuario).getSocket().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        servicioUsuario.removerUsuario(usuario);
        actualizarUsuarios();
        System.out.println("El usuario " + this.usuario.getUsuario() + " se ha desconectado.");
    }

    private void actualizarUsuarios() {
        try {
            List<UsuarioPosicion> conectados = new ArrayList<>();
            for (Usuario usuario : servicioUsuario.getUsuarios()) {
                conectados.add(usuario.getPosicion());
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
        synchronized (servicioUsuario.getUsuarios()) {
            for (Usuario usr : servicioUsuario.getUsuarios()) {
                PrintWriter pw = new PrintWriter(usr.getSocket().getOutputStream());

                pw.println(mensaje);
                pw.flush();
            }
        }
    }
}
