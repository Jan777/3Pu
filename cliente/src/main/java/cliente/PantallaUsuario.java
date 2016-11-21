package cliente;

import cliente.comunicacion.CrearPersonaje;
import main.java.mergame.casta.EsDeCasta;
import main.java.mergame.casta.impl.Guerrero;
import main.java.mergame.casta.impl.Mago;
import main.java.mergame.individuos.personajes.Personaje;
import main.java.mergame.individuos.personajes.impl.Elfo;
import main.java.mergame.individuos.personajes.impl.Enano;
import main.java.mergame.individuos.personajes.impl.Humano;
import main.java.mergame.individuos.personajes.impl.Orco;
import org.codehaus.jackson.map.ObjectMapper;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class PantallaUsuario extends JFrame {

    private JPanel contentPane;
    private Principal pantallaPrincipal;
    private JTextField textField;
    private JLabel labelErrorTipeo;
    private JComboBox comboBoxCastas;
    private JComboBox comboBoxPersonajes;
    private Map<String, Personaje> personajes;
    private Map<String, EsDeCasta> casta;

    private Usuario usuario;

    //ATRIBUTOS PARA LA CONEXION//
    private Socket socket;
    private Scanner entradaDatos;
    private PrintWriter salidaDatos;

    public PantallaUsuario(Usuario usuarioIn) throws IOException {

        this.pantallaPrincipal = this.pantallaPrincipal;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 699, 396);
        this.contentPane = new JPanel();
        this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(this.contentPane);
        this.contentPane.setLayout(null);

        this.usuario = usuarioIn;

        this.socket = usuarioIn.getSocket();
        this.entradaDatos = new Scanner(this.socket.getInputStream());
        this.salidaDatos = new PrintWriter(this.socket.getOutputStream());

        //INSTANCIAS DE LOS MAPAS, QUE CONTIENE TODOS LOS PERSONAJES Y LAS CASTAS
        this.personajes = new HashMap<>();
        this.personajes.put("Humano", new Humano());
        this.personajes.put("Elfo", new Elfo());
        this.personajes.put("Enano", new Enano());
        this.personajes.put("Orco", new Orco());

        this.casta = new HashMap<>();
        this.casta.put("Mago", new Mago());
        this.casta.put("Guerrero", new Guerrero());

        //COMPONENTES NO MODIFICABLES//
        JLabel lblSeleccionPersonaje = new JLabel("Seleccioná personaje:");
        lblSeleccionPersonaje.setBounds(71, 87, 234, 15);
        this.contentPane.add(lblSeleccionPersonaje);

        JLabel lblAhoraSeleccionUna = new JLabel("Ahora seleccioná una casta:");
        lblAhoraSeleccionUna.setBounds(71, 144, 232, 15);
        this.contentPane.add(lblAhoraSeleccionUna);

        JLabel lblyQueTal = new JLabel("¿Y que tal si le agregas un nombre?");
        lblyQueTal.setBounds(71, 220, 259, 15);
        this.contentPane.add(lblyQueTal);

        this.labelErrorTipeo = new JLabel("Completá el nombre loco!");
        this.labelErrorTipeo.setBounds(320, 249, 203, 15);
        this.labelErrorTipeo.setForeground(Color.RED);
        this.contentPane.add(this.labelErrorTipeo);
        this.labelErrorTipeo.setVisible(false);

        JLabel lblBienvenido = new JLabel("Bienvenido");
        lblBienvenido.setHorizontalAlignment(SwingConstants.CENTER);
        lblBienvenido.setBounds(308, 11, 70, 15);
        this.contentPane.add(lblBienvenido);

        //////////////////////////////////////////////////////////////////////////

        JButton btnCrearNuevoPersonaje = new JButton("Ingresá a tu mundo, a luchar!");
        btnCrearNuevoPersonaje.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (!PantallaUsuario.this.textField.getText().equals("")) {
                    crearPersonaje();
                } else {
                    PantallaUsuario.this.labelErrorTipeo.setVisible(true);
                }
            }
        });
        btnCrearNuevoPersonaje.setBounds(389, 304, 259, 25);
        this.contentPane.add(btnCrearNuevoPersonaje);

        this.comboBoxPersonajes = new JComboBox();
        this.comboBoxPersonajes.setBounds(71, 108, 146, 24);
        this.contentPane.add(this.comboBoxPersonajes);
        this.comboBoxPersonajes.addItem("Elfo");
        this.comboBoxPersonajes.addItem("Enano");
        this.comboBoxPersonajes.addItem("Humano");
        this.comboBoxPersonajes.addItem("Orco");

        this.comboBoxCastas = new JComboBox();
        this.comboBoxCastas.setBounds(71, 163, 156, 24);
        this.contentPane.add(this.comboBoxCastas);
        this.comboBoxCastas.addItem("Guerrero");
        this.comboBoxCastas.addItem("Mago");

        this.textField = new JTextField(); //acá va el nombre del personaje
        this.textField.setBounds(73, 247, 217, 19);
        this.contentPane.add(this.textField);
    }

    // compruebo que el usuario complete el nombre del personaje.
    public void crearPersonaje() {
        if (!this.textField.getText().equals("")) {
            Personaje personaje = this.personajes.get((String) this.comboBoxPersonajes.getSelectedItem());

            this.usuario.setPersonaje(personaje);
            System.out.println(this.usuario.getPersonaje().getSalud());
            ObjectMapper mapper = new ObjectMapper();

            try {
                Mensaje nuevoMensaje = new CrearPersonaje(this.usuario);
                String jsonInString = mapper.writeValueAsString(nuevoMensaje);

                this.salidaDatos.println(jsonInString); // LE ENVIO EL MENSAJE DE SALA Y NICKNAME
                this.salidaDatos.flush();

//	            //SE CREA ESTE BOTON DESPUES DE INSTANCEAR EL PERSONAJE, Y LO QUE HACE ES MANDAR AL SERVIDOR LA SALUD DE ELFERRA
//                JButton btnGetSalud = new JButton("mostra tu salud!");
//                btnGetSalud.addActionListener(new ActionListener() {
//        			public void actionPerformed(ActionEvent arg0) {
//        				String mensaje = "";
//        					mensaje = String.valueOf(personaje.getSalud());
//        					newClient.enviarDatos(mensaje);
//        			}
//        		});
//                btnGetSalud.setBounds(300, 250, 200, 25);
//        		contentPane.add(btnGetSalud);
//        		
//        		//// FIN DE BOTON DE MANDAR SALUD ///

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            //dispose();
        } else {
            this.labelErrorTipeo.setVisible(true);
        }
    }
}
