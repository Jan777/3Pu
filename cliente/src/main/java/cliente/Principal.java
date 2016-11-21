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
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal extends JFrame {

    //ATRIBUTOS PARA LA PANTALLA//
    private JPanel contentPane;
    private JTextField textFieldUsuario;
    private List<Mundo> listaMundos;
    private JPasswordField passwordField;
    private JLabel labelError;
    private JComboBox comboBox;
    private ObjectMapper mapper;
    private Usuario usuario;

    //ATRIBUTOS PARA LA CONEXION//
    private Socket socket;
    private Scanner entradaDatos;
    private PrintWriter salidaDatos;

    public JComboBox getComboBox() {
        return this.comboBox;
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Principal frame = new Principal();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public Principal() {
        this.mapper = new ObjectMapper();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

        JLabel lblInsertTuUsuario = new JLabel("Ingresá tu usuario:");
        lblInsertTuUsuario.setBounds(95, 135, 190, 15);
        this.contentPane.add(lblInsertTuUsuario);

        JLabel lblYAcTu = new JLabel("Y acá tu contraseña:");
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
                    conectarConServidor();
                }
            }
        });

        JButton btnSalvAClaudia = new JButton("Salvá a Claudia");
        btnSalvAClaudia.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    conectarConServidor();
                }
            }
        });

        JButton btnCrearUsuario = new JButton("¿No tenés cuenta?");
        btnCrearUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarPantallaCrearUsuario();
            }
        });
        btnCrearUsuario.setBounds(53, 327, 151, 25);
        this.contentPane.add(btnCrearUsuario);


        btnSalvAClaudia.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                conectarConServidor();
            }
        });
        btnSalvAClaudia.setBounds(213, 327, 151, 25);
        this.contentPane.add(btnSalvAClaudia);

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
                    conectarConServidor();
                }
            }
        });
    }

    private void mostrarPantallaCrearUsuario() {
        ViewCrearUsuario frame = new ViewCrearUsuario();
        frame.setVisible(true);
    }


    // compruebo que el usuario complete el nombre del personaje.
    public void conectarConServidor() {
        if (!this.textFieldUsuario.getText().equals("")) {

            String server = "127.0.0.1";
            try {
                final int PORT = 5553;
                this.socket = new Socket(server, PORT);
                this.entradaDatos = new Scanner(this.socket.getInputStream());
                this.salidaDatos = new PrintWriter(this.socket.getOutputStream());
                this.usuario = new Usuario(this.socket);

                System.out.println("Te conectaste a: " + server);

                /*PROBANDO INTERFAZ*/
                String numMundo = (String) this.comboBox.getSelectedItem();
                String nombreUsuario = this.textFieldUsuario.getText();
                String passUsuario = this.passwordField.getText();

                Login login = new Login(nombreUsuario, numMundo);
                login.setPassword(passUsuario);

                String loginMensaje = this.mapper.writeValueAsString(login);

                this.salidaDatos.println("LOGI" + loginMensaje);
                this.salidaDatos.flush();

                //LO HICE DE ESTA FORMA PARA PODER ACTIVAR EL ERROR DE USUARIO INCORRECTO//
                leer();
                //////////////////////////////////////////////////////////////
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else {
            this.labelError.setVisible(true);
        }
    }

    public void leer() {
        Thread leer_hilo = new Thread(new Runnable() {
            public void run() {
                try {
                    while (true) {
                        if (Principal.this.entradaDatos.hasNext()) {
                            String mensajeEntrante = Principal.this.entradaDatos.nextLine();
                            System.out.println(mensajeEntrante);
                            if (mensajeEntrante.equals("Datos Incorrectos")) {
                                Principal.this.labelError.setVisible(true);
                                Principal.this.socket.close();
                            }

                            if (mensajeEntrante.equals("Datos Correctos")) {
                                PantallaUsuario pantallaUsuario = new PantallaUsuario(Principal.this.usuario);
                                pantallaUsuario.setVisible(true);
                                dispose();
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        leer_hilo.start();
    }

}
