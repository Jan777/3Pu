package cliente;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.codehaus.jackson.map.ObjectMapper;

import com.google.gson.Gson;

import main.java.mergame.casta.EsDeCasta;
import main.java.mergame.casta.impl.Guerrero;
import main.java.mergame.casta.impl.Mago;
import main.java.mergame.individuos.personajes.Personaje;
import main.java.mergame.individuos.personajes.impl.Elfo;
import main.java.mergame.individuos.personajes.impl.Enano;
import main.java.mergame.individuos.personajes.impl.Humano;
import main.java.mergame.individuos.personajes.impl.Orco;
import main.java.mergame.individuos.personajes.impl.PersonajeImpl;
import main.java.mergame.skill.Habilidad;
import main.java.mergame.skill.hechizo.HechizoCongelar;
import main.java.mergame.skill.hechizo.HechizoCurar;
import main.java.mergame.skill.hechizo.HechizoFuego;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class PantallaUsuario extends JFrame {

	private JPanel contentPane;
	private Principal pantallaPrincipal;
	private JTextField textField;
	private JLabel labelErrorTipeo;
	private JComboBox comboBoxCastas;
	private JComboBox comboBoxPersonajes;
	private Map <String, Personaje> personajes;
	private Map <String, EsDeCasta> casta;
	
	private Usuario usuario;
	
	//ATRIBUTOS PARA LA CONEXION//
	private Socket socket;
	private Scanner entradaDatos;
	private PrintWriter salidaDatos;
		
	public PantallaUsuario(Usuario usuarioIn) throws IOException {
		
		this.pantallaPrincipal = pantallaPrincipal;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 502, 396);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		this.usuario = usuarioIn;
		
		this.socket = usuarioIn.getSocket();
		entradaDatos = new Scanner(socket.getInputStream());
		salidaDatos = new PrintWriter(socket.getOutputStream());
		
		//INSTANCIAS DE LOS MAPAS, QUE CONTIENE TODOS LOS PERSONAJES Y LAS CASTAS
		personajes = new HashMap<>();
		personajes.put("Humano", new Humano());
		personajes.put("Elfo", new Elfo());
		personajes.put("Enano", new Enano());
		personajes.put("Orco", new Orco());
		
		casta = new HashMap<>();
		casta.put("Mago", new Mago());
		casta.put("Guerrero", new Guerrero());
		
		//COMPONENTES NO MODIFICABLES//		
		JLabel lblSeleccionPersonaje = new JLabel("Seleccioná personaje:");
		lblSeleccionPersonaje.setBounds(71, 87, 234, 15);
		contentPane.add(lblSeleccionPersonaje);
		
		JLabel lblAhoraSeleccionUna = new JLabel("Ahora seleccioná una casta:");
		lblAhoraSeleccionUna.setBounds(71, 144, 232, 15);
		contentPane.add(lblAhoraSeleccionUna);
		
		JLabel lblyQueTal = new JLabel("¿Y que tal si le agregas un nombre?");
		lblyQueTal.setBounds(71, 220, 259, 15);
		contentPane.add(lblyQueTal);
		
		labelErrorTipeo = new JLabel("Completá el nombre loco!");
		labelErrorTipeo.setBounds(320, 249, 203, 15);
		labelErrorTipeo.setForeground(Color.RED);
		contentPane.add(labelErrorTipeo);
		labelErrorTipeo.setVisible(false);
		
		JLabel lblBienvenido = new JLabel("Bienvenido");
		lblBienvenido.setHorizontalAlignment(SwingConstants.CENTER);
		lblBienvenido.setBounds(220, 11, 70, 15);
		contentPane.add(lblBienvenido);
		
		//////////////////////////////////////////////////////////////////////////				
		
		JButton btnCrearNuevoPersonaje = new JButton("Ingresá a tu mundo, a luchar!");
		btnCrearNuevoPersonaje.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!textField.getText().equals("")){
					crearPersonaje();
					abrirVentanaJuego();
				}
				else{
					labelErrorTipeo.setVisible(true);
				}
			}			
		});
		btnCrearNuevoPersonaje.setBounds(126, 298, 217, 25);
		contentPane.add(btnCrearNuevoPersonaje);

		comboBoxPersonajes = new JComboBox();
		comboBoxPersonajes.setBounds(71, 108, 146, 24);
		contentPane.add(comboBoxPersonajes);
		comboBoxPersonajes.addItem("Elfo");
		comboBoxPersonajes.addItem("Enano");
		comboBoxPersonajes.addItem("Humano");
		comboBoxPersonajes.addItem("Orco");
		
		comboBoxCastas = new JComboBox();
		comboBoxCastas.setBounds(71, 163, 156, 24);
		contentPane.add(comboBoxCastas);
		comboBoxCastas.addItem("Guerrero");
		comboBoxCastas.addItem("Mago");
		
		textField = new JTextField(); //acá va el nombre del personaje
		textField.setBounds(73, 247, 217, 19);
		contentPane.add(textField);
	}
	
	// compruebo que el usuario complete el nombre del personaje.
	public void crearPersonaje(){
		if (!this.textField.getText().equals("")) {
			Personaje personaje = this.personajes.get((String)this.comboBoxPersonajes.getSelectedItem());
			personaje.setCasta(this.casta.get((String)this.comboBoxCastas.getSelectedItem()));
			personaje.setNombre(textField.getText());
	        usuario.setPersonaje(personaje);
	        
	        try {
	        	ObjectMapper mapper = new ObjectMapper();
	        	//Mensaje nuevoMensaje = new MensajeCrear(usuario.getPersonaje());
	        	MensajeCrearPersonaje mensajeCrear = new MensajeCrearPersonaje((String)this.comboBoxPersonajes.getSelectedItem(), (String)this.comboBoxCastas.getSelectedItem(), textField.getText() );
	        	
	        	String jsonInString = mapper.writeValueAsString(mensajeCrear);
	        	//Gson gson = new Gson();
	        	//String jsonInString = gson.toJson(nuevoMensaje);
	            salidaDatos.println(jsonInString); // LE ENVIO EL MENSAJE DE SALA Y NICKNAME
	            salidaDatos.flush();
	            
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
		}else{
			labelErrorTipeo.setVisible(true);
		}
	}
	
	private void abrirVentanaJuego() {
		PantallaJuego pantallaJuego = new PantallaJuego(usuario);	
		pantallaJuego.setVisible(true);
		dispose();
	}
	
}
