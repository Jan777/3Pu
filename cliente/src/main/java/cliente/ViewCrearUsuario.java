package cliente;

import cliente.comunicacion.Login;
import main.java.mergame.interfaz.Mundo;
import org.codehaus.jackson.map.ObjectMapper;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
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

    //ATRIBUTOS PARA LA PANTALLA//
    private JPanel contentPane;
    private JTextField textFieldUsuario;
    private List<Mundo> listaMundos;
    private JPasswordField passwordField;
    private JLabel labelError;
    private JComboBox comboBox;
    private ObjectMapper mapper;
    private Usuario usuario;

    public JComboBox getComboBox() {
        return this.comboBox;
    }

    /**
     * Create the frame.
     */
    public ViewCrearUsuario() {
        this.setAlwaysOnTop(true);
        this.mapper = new ObjectMapper();
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setBounds(100, 100, 442, 408);
        this.contentPane = new JPanel();
        this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(this.contentPane);
        this.contentPane.setLayout(null);

        //COMPONENTES SIN USO//
        JLabel lblMergame = new JLabel("MerGame");
        lblMergame.setFont(new Font("Khmer OS", Font.BOLD, 50));
        lblMergame.setHorizontalAlignment(SwingConstants.CENTER);
        lblMergame.setBounds(35, 47, 270, 58);
        this.contentPane.add(lblMergame);

        JLabel lblInsertTuUsuario = new JLabel("Nuevo Usuario:");
        lblInsertTuUsuario.setBounds(95, 135, 190, 15);
        this.contentPane.add(lblInsertTuUsuario);

        JLabel lblYAcTu = new JLabel("Nueva Contraseña:");
        lblYAcTu.setBounds(95, 193, 270, 15);
        this.contentPane.add(lblYAcTu);

        JLabel lblNewLabel = new JLabel("Elegí el mundo:");
        lblNewLabel.setBounds(95, 264, 123, 15);
        this.contentPane.add(lblNewLabel);

        this.labelError = new JLabel("Error de usuario o contraseña");
        this.labelError.setForeground(Color.RED);
        this.labelError.setBounds(95, 108, 270, 15);
        this.contentPane.add(this.labelError);
        this.labelError.setVisible(false);

        //COMPONENTES QUE VOY A USAR//
        this.textFieldUsuario = new JTextField();
        this.textFieldUsuario.setBounds(95, 162, 270, 19);
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
        btnCrearUsuario.setBounds(53, 327, 151, 25);
        this.contentPane.add(btnCrearUsuario);

        btnCancel.setBounds(213, 327, 151, 25);
        this.contentPane.add(btnCancel);

        this.listaMundos = new ArrayList<Mundo>();
        Mundo mundoEjemplo = new Mundo();
        this.listaMundos.add(mundoEjemplo);

        this.comboBox = new JComboBox();
        this.comboBox.setBounds(234, 259, 133, 24);
        this.contentPane.add(this.comboBox);
        this.comboBox.addItem("1");
        this.comboBox.addItem("2");
        this.comboBox.addItem("3");

        this.passwordField = new JPasswordField();
        this.passwordField.setBounds(95, 213, 270, 19);
        this.contentPane.add(this.passwordField);
        this.passwordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {

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
        String server = "127.0.0.1";
        final int PORT = 5553;
        Socket socket = new Socket(server, PORT);
        PrintWriter salidaDatos = new PrintWriter(socket.getOutputStream());

        String numMundo = (String) this.comboBox.getSelectedItem();
        String nombreUsuario = this.textFieldUsuario.getText();
        String passUsuario = this.passwordField.getText();

        Login login = new Login(nombreUsuario, numMundo);
        login.setPassword(passUsuario);

        String loginMensaje = this.mapper.writeValueAsString(login);

        salidaDatos.println("CREA" + loginMensaje);
        salidaDatos.flush();
    }

//    public void leer() {
//        Thread leer_hilo = new Thread(new Runnable() {
//            public void run() {
//                try {
//                    while (true) {
//                        if (Principal.this.entradaDatos.hasNext()) {
//                            String mensajeEntrante = Principal.this.entradaDatos.nextLine();
//                            System.out.println(mensajeEntrante);
//                            if (mensajeEntrante.equals("Datos Incorrectos")) {
//                                Principal.this.labelError.setVisible(true);
//                                Principal.this.socket.close();
//                            }
//
//                            if (mensajeEntrante.equals("Datos Correctos")) {
//                                PantallaUsuario pantallaUsuario = new PantallaUsuario(Principal.this.usuario);
//                                pantallaUsuario.setVisible(true);
//                                dispose();
//                            }
//                        }
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//        leer_hilo.start();
//    }
}

