package cliente;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.codehaus.jackson.map.ObjectMapper;

import main.java.mergame.casta.EsDeCasta;
import main.java.mergame.casta.impl.Guerrero;
import main.java.mergame.casta.impl.Mago;
import main.java.mergame.individuos.personajes.Personaje;
import main.java.mergame.individuos.personajes.impl.Elfo;
import main.java.mergame.individuos.personajes.impl.Enano;
import main.java.mergame.individuos.personajes.impl.Humano;
import main.java.mergame.individuos.personajes.impl.Orco;
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
public class PantallaCreacionPersonaje extends JFrame {

	private JPanel contentPane;
	private Principal pantallaPrincipal;
	private JTextField nombrePersonaje;
	private JLabel labelErrorTipeo;
	private JComboBox comboBoxCastas;
	private JComboBox comboBoxRazaPersonajes;
	private Map <String, Personaje> personajes;
	private Map <String, EsDeCasta> casta;
	private Personaje personajeActivo;
	private PantallaJuego hijo;
	private ClientThread newClient;
	private JComboBox comboBox;
	
<<<<<<< HEAD:cliente/src/main/java/cliente/PantallaCreacionPersonaje.java
	public PantallaCreacionPersonaje(Principal pantallaPrincipal) {
=======
	//ATRIBUTOS PARA LA CONEXION//
	private Socket socket;
	private Scanner entradaDatos;
	private PrintWriter salidaDatos;
		
	public PantallaUsuario(Socket socket) throws IOException {
		
		this.pantallaPrincipal = pantallaPrincipal;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 699, 396);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		this.socket = socket;
		entradaDatos = new Scanner(socket.getInputStream());
		salidaDatos = new PrintWriter(socket.getOutputStream());
>>>>>>> 02cadf39c5892dd09c7c7894a5fe543840c9ed0f:cliente/src/main/java/cliente/PantallaUsuario.java
		
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
		lblBienvenido.setBounds(308, 11, 70, 15);
		contentPane.add(lblBienvenido);
		
<<<<<<< HEAD:cliente/src/main/java/cliente/PantallaCreacionPersonaje.java
		JButton btnCrearNuevoPersonaje = new JButton("IngresÃ¡ a tu mundo, a luchar!");
		btnCrearNuevoPersonaje.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!nombrePersonaje.getText().equals("")){
					conectarConServidor();	
					//abrirVentanaJuego();
=======
		//////////////////////////////////////////////////////////////////////////				
		
		JButton btnCrearNuevoPersonaje = new JButton("Ingresá a tu mundo, a luchar!");
		btnCrearNuevoPersonaje.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!textField.getText().equals("")){
					enviarPersonaje();
>>>>>>> 02cadf39c5892dd09c7c7894a5fe543840c9ed0f:cliente/src/main/java/cliente/PantallaUsuario.java
				}
				else{
					labelErrorTipeo.setVisible(true);
				}
			}
		});
		btnCrearNuevoPersonaje.setBounds(389, 304, 259, 25);
		contentPane.add(btnCrearNuevoPersonaje);
<<<<<<< HEAD:cliente/src/main/java/cliente/PantallaCreacionPersonaje.java
		
		JLabel lblSeleccionPersonaje = new JLabel("SeleccionÃ¡ personaje:");
		lblSeleccionPersonaje.setBounds(71, 87, 234, 15);
		contentPane.add(lblSeleccionPersonaje);
		
		comboBoxRazaPersonajes = new JComboBox();
		comboBoxRazaPersonajes.setBounds(71, 108, 146, 24);
		contentPane.add(comboBoxRazaPersonajes);
		comboBoxRazaPersonajes.addItem("Elfo");
		comboBoxRazaPersonajes.addItem("Enano");
		comboBoxRazaPersonajes.addItem("Humano");
		comboBoxRazaPersonajes.addItem("Orco");
		
		JLabel lblAhoraSeleccionUna = new JLabel("Ahora seleccionÃ¡ una casta:");
		lblAhoraSeleccionUna.setBounds(71, 144, 232, 15);
		contentPane.add(lblAhoraSeleccionUna);
		
=======

		comboBoxPersonajes = new JComboBox();
		comboBoxPersonajes.setBounds(71, 108, 146, 24);
		contentPane.add(comboBoxPersonajes);
		comboBoxPersonajes.addItem("Elfo");
		comboBoxPersonajes.addItem("Enano");
		comboBoxPersonajes.addItem("Humano");
		comboBoxPersonajes.addItem("Orco");
		
>>>>>>> 02cadf39c5892dd09c7c7894a5fe543840c9ed0f:cliente/src/main/java/cliente/PantallaUsuario.java
		comboBoxCastas = new JComboBox();
		comboBoxCastas.setBounds(71, 163, 156, 24);
		contentPane.add(comboBoxCastas);
		comboBoxCastas.addItem("Guerrero");
		comboBoxCastas.addItem("Mago");
		
<<<<<<< HEAD:cliente/src/main/java/cliente/PantallaCreacionPersonaje.java
		JLabel lblyQueTal = new JLabel("Â¿Y que tal si le agregas un nombre?");
		lblyQueTal.setBounds(71, 220, 259, 15);
		contentPane.add(lblyQueTal);
		
		
		nombrePersonaje = new JTextField(); //acÃ¡ va el nombre del personaje
		nombrePersonaje.setBounds(73, 247, 217, 19);
		
		contentPane.add(nombrePersonaje);
		
		labelErrorTipeo = new JLabel("CompletÃ¡ el nombre loco!");
		labelErrorTipeo.setBounds(320, 249, 203, 15);
		labelErrorTipeo.setForeground(Color.RED);
		contentPane.add(labelErrorTipeo);
		
		comboBox = new JComboBox();
		comboBox.setBounds(432, 94, 165, 20);
		contentPane.add(comboBox);
		labelErrorTipeo.setVisible(false);
		
		
	}
	
	// compruebo que el usuario complete el nombre del personaje.
	public void conectarConServidor(){
		if (!this.nombrePersonaje.getText().equals("")) {
			
			String server = "127.0.0.1";
=======
		textField = new JTextField(); //acá va el nombre del personaje
		textField.setBounds(73, 247, 217, 19);
		contentPane.add(textField);
	}
	
	// compruebo que el usuario complete el nombre del personaje.
	public void enviarPersonaje(){
		if (!this.textField.getText().equals("")) {
>>>>>>> 02cadf39c5892dd09c7c7894a5fe543840c9ed0f:cliente/src/main/java/cliente/PantallaUsuario.java
	        ObjectMapper mapper = new ObjectMapper();
	        Personaje personaje = this.personajes.get((String)this.comboBoxPersonajes.getSelectedItem());
	        
	        try {
<<<<<<< HEAD:cliente/src/main/java/cliente/PantallaCreacionPersonaje.java
	            final int PORT = 5553;
	            Socket socket = new Socket(server, PORT);
	            
	            System.out.println("Te conectaste a: " + server);
	            System.out.println("HablÃ¡ todo lo que quieras");
	            
	            
	            /*PROBANDO INTERFAZ*/
	            
	            int mundo = Integer.valueOf((String) pantallaPrincipal.getComboBox().getSelectedItem());
	            
	            User user = new User(mundo, this.nombrePersonaje.getText() );
	            
	            String jsonInString = mapper.writeValueAsString(user);

	            PrintWriter out = new PrintWriter(socket.getOutputStream()); //OBTENGO EL CANAL DE SALIDA DEL SOCKET HACIA EL SERVIDOR
	            out.println(jsonInString); // LE ENVIO EL MENSAJE DE SALA Y NICKNAME

	            out.flush();
	            
	            //INSTANCEANDO UN PERSONAJE DENTRO DE MI CLIENTE Y MANDARLO AL SERVIDOR
	            
	            personajeActivo = this.personajes.get((String)this.comboBoxRazaPersonajes.getSelectedItem());
	            
	            ///FIN INSTANCIA PERSONAJE////////////////////////////////////////////////////////////////////

	            //INSTANCIA DEL CLIENTE, DONDE SE MANDA MSJ CON EL SERVIDOR
	            newClient = new ClientThread(socket, this);
	            Thread thread = new Thread(newClient);
	            thread.start();
	            
	           
	            //SE CREA ESTE BOTON DESPUES DE INSTANCEAR EL PERSONAJE, Y LO QUE HACE ES MANDAR AL SERVIDOR LA SALUD DE ELFERRA
                JButton btnGetSalud = new JButton("mostra tu salud!");
                btnGetSalud.addActionListener(new ActionListener() {
        			public void actionPerformed(ActionEvent arg0) {
        				String mensaje = "";
        					mensaje = String.valueOf(personajeActivo.getSalud());
        					newClient.enviarDatos(mensaje);
        			}
        		});
                btnGetSalud.setBounds(300, 250, 200, 25);
        		contentPane.add(btnGetSalud);
        		
        		//// FIN DE BOTON DE MANDAR SALUD ///
                
        		
        		/* ESTE WHILE ES PARA MANDAR VARIOS MENSAJES, ES DECIR QUE PERTENECIA AL CHAT, NO LO BORRÃ‰ PORQUE SEGURO SIRVE
                 String textoTeclado = "";
	            while (!textoTeclado.equals("fin")) { //MIENTRAS NO ESCRIBA FIN PODRE ENVIAR LOS MENSAJES QUE QUIERA
	                Scanner bufferDeTeclado = new Scanner(System.in);
	                textoTeclado = bufferDeTeclado.nextLine();
	                
	                if (textoTeclado.equals("fin")) { // SI ESCRIBO FIN DESCONECTA TODO
	                    newClient.desconectar();
	                    bufferDeTeclado.close();
	                } else {
	                    newClient.enviarDatos(textoTeclado);
	                }
	            }*/
=======
	        	String jsonInString = mapper.writeValueAsString(personaje);
	            salidaDatos.println("Nuevo Personaje|"+jsonInString); // LE ENVIO EL MENSAJE DE SALA Y NICKNAME
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
>>>>>>> 02cadf39c5892dd09c7c7894a5fe543840c9ed0f:cliente/src/main/java/cliente/PantallaUsuario.java

	        } catch (Exception e) {
	        	System.out.println(e.getMessage());
	        }
			//dispose();
		}else{
			labelErrorTipeo.setVisible(true);
		}
	}
	
	public JComboBox getComboBox() {
		return comboBox;
	}

	public void setComboBox(JComboBox comboBox) {
		this.comboBox = comboBox;
	}

	public void abrirVentanaJuego(){
		hijo =  new PantallaJuego(this, personajeActivo);
		newClient.setMapa(hijo);
		hijo.setVisible(true);
		//dispose();
	}
	
	public String getNombrePersonaje(){
		return nombrePersonaje.getText();
	}
}
