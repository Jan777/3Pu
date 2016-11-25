package cliente;

import cliente.comunicacion.Login;
import cliente.hilos.LoginThread;
import cliente.usuario.Usuario;
import cliente.vistas.ViewCrearUsuario;
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

public class Client extends JFrame {
    private JPanel contentPane;
    private JTextField textFieldUsuario;
    private List<Mundo> listaMundos;
    private JPasswordField passwordField;
    private JLabel labelError;
    private JComboBox comboBox ;
    private Usuario usuario;
    private static final int PORT = 5553;
    private static final String server = "localhost";
    private ObjectMapper mapper;

    public void init() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 525, 375);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblMergame = new JLabel("MerGame");
        lblMergame.setFont(new Font("Khmer OS", Font.BOLD, 50));
        lblMergame.setHorizontalAlignment(SwingConstants.CENTER);
        lblMergame.setBounds(43, 50, 270, 58);
        contentPane.add(lblMergame);

        JLabel lblInsertTuUsuario = new JLabel("Ingresá tu usuario:");
        lblInsertTuUsuario.setBounds(95, 135, 190, 15);
        contentPane.add(lblInsertTuUsuario);

        JLabel lblYAcTu = new JLabel("Y acá tu contraseña:");
        lblYAcTu.setBounds(95, 193, 270, 15);
        contentPane.add(lblYAcTu);

        labelError = new JLabel("Error de usuario o contraseña");
        labelError.setForeground(Color.RED);
        labelError.setBounds(95, 108, 270, 15);
        contentPane.add(labelError);
        labelError.setVisible(false);

        //COMPONENTES QUE VOY A USAR//
        textFieldUsuario = new JTextField();
        textFieldUsuario.setBounds(95, 162, 270, 19);
        contentPane.add(textFieldUsuario);
        textFieldUsuario.setColumns(10);
        textFieldUsuario.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode()==KeyEvent.VK_ENTER){
                    intentarLoguear();
                }
            }
        });

        JButton btnSalvAClaudia = new JButton("Salvá a Claudia");
        btnSalvAClaudia.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                intentarLoguear();
            }
        });
        btnSalvAClaudia.setBounds(312, 273, 151, 25);
        contentPane.add(btnSalvAClaudia);

        JButton btnCrearUsuario = new JButton("¿No tenés cuenta?");
        btnCrearUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarPantallaCrearUsuario();
            }
        });
        btnCrearUsuario.setBounds(43, 273, 200, 25);
        this.contentPane.add(btnCrearUsuario);

        listaMundos = new ArrayList<Mundo>();
        Mundo mundoEjemplo = new Mundo();
        listaMundos.add(mundoEjemplo);

        comboBox = new JComboBox();
        comboBox.setBounds(330, 57, 133, 24);
        contentPane.add(comboBox);
        comboBox.addItem("1");
        comboBox.addItem("2");
        comboBox.addItem("3");
        comboBox.setVisible(false);

        passwordField = new JPasswordField();
        passwordField.setBounds(95, 213, 270, 19);
        contentPane.add(passwordField);
        passwordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode()==KeyEvent.VK_ENTER){
                    intentarLoguear();
                }
            }
        });
        setVisible(true);
    }

    private void mostrarPantallaCrearUsuario() {
        ViewCrearUsuario frame = new ViewCrearUsuario();
        frame.setVisible(true);
    }

    public void intentarLoguear(){
        if (!this.textFieldUsuario.getText().equals("")) {
            try {
                Socket socket = new Socket(server, PORT);
                PrintWriter pw = new PrintWriter(socket.getOutputStream());

                String numMundo = (String) comboBox.getSelectedItem();
                String nombreUsuario = textFieldUsuario.getText();
                String passUsuario = passwordField.getText();

                Login login = new Login(nombreUsuario, numMundo);
                login.setPassword(passUsuario);

                mapper = new ObjectMapper();
                usuario = new Usuario(socket, login.getUsuario());
                String loginMensaje = mapper.writeValueAsString(login);

                pw.println("LOGI" + loginMensaje);
                pw.flush();

                LoginThread l = new LoginThread(usuario, this);
                Thread t = new Thread(l);
                t.start();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }else{
            labelError.setVisible(true);
        }
    }

    public JLabel getLabelError() {
        return labelError;
    }

}
