package cliente.mundo;

import cliente.hilos.GameThread;
import cliente.mundo.partida.Jugador;
import cliente.mundo.partida.Partida;
import cliente.mundo.ui.ControladorMouse;
import cliente.mundo.ui.ControladorTeclado;
import cliente.mundo.ui.UIComponent;
import cliente.mundo.ui.entidades.TipoPersonaje;
import cliente.usuario.Usuario;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ViewGame {
    private Usuario usuario;
    private  Partida partida;

    public ViewGame(Usuario usuario) {
        this.usuario = usuario;
    	partida = new Partida(usuario);
    }

    public void init() {
    	//Creamos la partida y registramos al jugador local

    	//Creamos el jugador local y registramos el componente visual
    	Jugador jugadorLocal = new Jugador(usuario.getUsuario(), TipoPersonaje.GetByName(usuario.getPersonaje()));


    	partida.RegistrarJugador(jugadorLocal,true);
    	//partida.RegistrarJugador(jugadorRemoto1,true);
    //	partida.RegistrarJugador(jugadorRemoto2,false);

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                }

                JFrame frame = new JFrame("Mergame");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLayout(new BorderLayout());
                frame.setBounds(0, 0, 800, 600);


                UIComponent uic = new UIComponent();
                frame.add(uic);


                frame.addMouseListener(ControladorMouse.getInstance());
                frame.addKeyListener(ControladorTeclado.getInstance());

                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }

    public void levantaGameThread() {
        try {
            PrintWriter pw = new PrintWriter(usuario.getSocket().getOutputStream());
            pw.println("INGR");
            pw.flush();

            GameThread c = new GameThread(this.usuario, this);
            Thread t = new Thread(c);
            t.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Partida getPartida() {
        return partida;
    }
}