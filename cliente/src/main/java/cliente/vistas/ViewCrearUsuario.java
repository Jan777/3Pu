package cliente.vistas;

import cliente.usuario.Usuario;
import cliente.comunicacion.Login;
import main.java.mergame.interfaz.Mundo;
import org.codehaus.jackson.map.ObjectMapper;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gustavo on 20/11/2016.
 */
public class ViewCrearUsuario extends JFrame {
    private JPanel contentPane;
    private JTextField textFieldUsuario;
    private List<Mundo> listaMundos;
    private JPasswordField passwordField;
    private ObjectMapper mapper;
    private final String server = "127.0.0.1";
    private final int PORT = 5553;

    public ViewCrearUsuario() {
        this.setAlwaysOnTop(true);
        this.mapper = new ObjectMapper();
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setBounds(100, 100, 434, 210);
        this.contentPane = new JPanel();
        this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(this.contentPane);
        this.contentPane.setLayout(null);

        JLabel lblInsertTuUsuario = new JLabel("Nuevo Usuario:");
        lblInsertTuUsuario.setBounds(95, 12, 190, 15);
        this.contentPane.add(lblInsertTuUsuario);

        JLabel lblYAcTu = new JLabel("Nueva Contraseña:");
        lblYAcTu.setBounds(95, 70, 270, 15);
        this.contentPane.add(lblYAcTu);

        //COMPONENTES QUE VOY A USAR//
        this.textFieldUsuario = new JTextField();
        this.textFieldUsuario.setBounds(95, 39, 270, 19);
        this.contentPane.add(this.textFieldUsuario);
        this.textFieldUsuario.setColumns(10);
        this.textFieldUsuario.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {

                }
            }
        });

        JButton btnCancel = new JButton("Cancelar");
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelarNuevoUsuario();
            }
        });

        JButton btnCrearUsuario = new JButton("Crear usuario");

        btnCrearUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    crearUsuario();
                    cancelarNuevoUsuario();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        btnCrearUsuario.setBounds(54, 136, 151, 25);
        this.contentPane.add(btnCrearUsuario);

        btnCancel.setBounds(240, 136, 151, 25);
        this.contentPane.add(btnCancel);

        this.listaMundos = new ArrayList<Mundo>();
        Mundo mundoEjemplo = new Mundo();
        this.listaMundos.add(mundoEjemplo);

        this.passwordField = new JPasswordField();
        this.passwordField.setBounds(95, 97, 270, 19);
        this.contentPane.add(this.passwordField);
        this.passwordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    try {
                        crearUsuario();
                        cancelarNuevoUsuario();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
        this.setLocationRelativeTo(null);
    }

    private void cancelarNuevoUsuario() {
        this.setVisible(false);
        this.textFieldUsuario.setText("");
        this.passwordField.setText("");
        this.dispose();
    }

    public void crearUsuario() throws IOException {
        Socket socket = new Socket(server, PORT);
        PrintWriter pw = new PrintWriter(socket.getOutputStream());

        String numMundo = "1";
        String nombreUsuario = this.textFieldUsuario.getText();
        String passUsuario = this.passwordField.getText();

        Login login = new Login(nombreUsuario, numMundo);
        login.setPassword(passUsuario);

        String loginMensaje = this.mapper.writeValueAsString(login);

        pw.println("CREA" + loginMensaje);
        pw.flush();
    }
}

