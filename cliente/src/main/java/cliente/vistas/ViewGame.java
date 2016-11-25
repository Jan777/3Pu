package cliente.vistas;

import cliente.hilos.GameThread;
import cliente.usuario.Usuario;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ViewGame extends JFrame {
    private JPanel contentPane;
    private Usuario usuario;
    private JList listaPersonajesConectados;

    public ViewGame(Usuario usuario) {
        this.usuario = usuario;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 323, 322);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNombrePersonaje = new JLabel(usuario.getPersonaje().getNombre());
        lblNombrePersonaje.setBounds(10, 11, 133, 14);
        contentPane.add(lblNombrePersonaje);

        JLabel lblTituloVida = new JLabel("Vida");
        lblTituloVida.setBounds(10, 31, 37, 14);
        contentPane.add(lblTituloVida);

        JLabel lblTituloNivel = new JLabel("Nivel");
        lblTituloNivel.setBounds(10, 52, 46, 14);
        contentPane.add(lblTituloNivel);

        JLabel lblVida = new JLabel(String.valueOf(usuario.getPersonaje().getSalud()));
        lblVida.setBounds(53, 31, 46, 14);
        contentPane.add(lblVida);

        JLabel lblNivel = new JLabel(String.valueOf(usuario.getPersonaje().getNivel()));
        lblNivel.setBounds(53, 52, 46, 14);
        contentPane.add(lblNivel);

        JLabel lblPersonajesConectados = new JLabel("Personajes conectados: ");
        lblPersonajesConectados.setBounds(10, 93, 161, 14);
        contentPane.add(lblPersonajesConectados);

        listaPersonajesConectados = new JList();
        listaPersonajesConectados.setBounds(10, 118, 133, 148);
        contentPane.add(listaPersonajesConectados);

        JButton btnAtacar = new JButton("FuturoAtacar");
        btnAtacar.setBounds(164, 186, 133, 23);
        contentPane.add(btnAtacar);

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                try {
                    PrintWriter pw = new PrintWriter(usuario.getSocket().getOutputStream());

                    pw.println("LOUT" + usuario.getUsuario());
                    pw.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public JList getListaPersonajesConectados() {
        return listaPersonajesConectados;
    }

    public void levantaGameThread() {
        try {
            PrintWriter pw = new PrintWriter(usuario.getSocket().getOutputStream());
            Scanner sc = new Scanner(this.usuario.getSocket().getInputStream());
            pw.println("INGR");
            pw.flush();

            GameThread c = new GameThread(usuario, this);
            Thread t = new Thread(c);
            t.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
