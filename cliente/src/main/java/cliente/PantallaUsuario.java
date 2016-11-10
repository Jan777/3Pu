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
	
	public PantallaUsuario(Principal pantallaPrincipal) {
		
		//INSTANCIAS DE LOS MAPAS, QUE CONTIENE TODOS LOS PERSONAJES Y LAS CASTAS
		personajes = new HashMap<>();
		personajes.put("Humano", new Humano());
		personajes.put("Elfo", new Elfo());
		personajes.put("Enano", new Enano());
		personajes.put("Orco", new Orco());
		
		casta = new HashMap<>();
		casta.put("Mago", new Mago());
		casta.put("Guerrero", new Guerrero());
		
		//////////////////////////////////////////////////////////////////////////
		
		
		this.pantallaPrincipal = pantallaPrincipal;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 699, 396);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel labelNombreUsuario = new JLabel(pantallaPrincipal.getTextFieldUsuario());
		labelNombreUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		labelNombreUsuario.setBounds(308, 26, 70, 38);
		contentPane.add(labelNombreUsuario);
		
		JLabel lblBienvenido = new JLabel("Bienvenido");
		lblBienvenido.setHorizontalAlignment(SwingConstants.CENTER);
		lblBienvenido.setBounds(308, 11, 70, 15);
		contentPane.add(lblBienvenido);
		
		JButton btnCrearNuevoPersonaje = new JButton("Ingresá a tu mundo, a luchar!");
		btnCrearNuevoPersonaje.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!textField.getText().equals("")){
					conectarConServidor();
				}
				else{
					labelErrorTipeo.setVisible(true);
				}
			}
		});
		

		
		btnCrearNuevoPersonaje.setBounds(389, 304, 259, 25);
		contentPane.add(btnCrearNuevoPersonaje);
		
		JLabel lblSeleccionPersonaje = new JLabel("Seleccioná personaje:");
		lblSeleccionPersonaje.setBounds(71, 87, 234, 15);
		contentPane.add(lblSeleccionPersonaje);
		
		comboBoxPersonajes = new JComboBox();
		comboBoxPersonajes.setBounds(71, 108, 146, 24);
		contentPane.add(comboBoxPersonajes);
		comboBoxPersonajes.addItem("Elfo");
		comboBoxPersonajes.addItem("Enano");
		comboBoxPersonajes.addItem("Humano");
		comboBoxPersonajes.addItem("Orco");
		
		JLabel lblAhoraSeleccionUna = new JLabel("Ahora seleccioná una casta:");
		lblAhoraSeleccionUna.setBounds(71, 144, 232, 15);
		contentPane.add(lblAhoraSeleccionUna);
		
		comboBoxCastas = new JComboBox();
		comboBoxCastas.setBounds(71, 163, 156, 24);
		contentPane.add(comboBoxCastas);
		comboBoxCastas.addItem("Guerrero");
		comboBoxCastas.addItem("Mago");
		
		JLabel lblyQueTal = new JLabel("¿Y que tal si le agregas un nombre?");
		lblyQueTal.setBounds(71, 220, 259, 15);
		contentPane.add(lblyQueTal);
		
		
		textField = new JTextField(); //acá va el nombre del personaje
		textField.setBounds(73, 247, 217, 19);
		
		contentPane.add(textField);
		
		labelErrorTipeo = new JLabel("Completá el nombre loco!");
		labelErrorTipeo.setBounds(320, 249, 203, 15);
		labelErrorTipeo.setForeground(Color.RED);
		contentPane.add(labelErrorTipeo);
		labelErrorTipeo.setVisible(false);
		
		
	}
	
	// compruebo que el usuario complete el nombre del personaje.
	public void conectarConServidor(){
		if (!this.textField.getText().equals("")) {
			
			String server = "127.0.0.1";
	        ObjectMapper mapper = new ObjectMapper();
	        
	        try {
	            final int PORT = 5553;
	            Socket socket = new Socket(server, PORT);
	            
	            System.out.println("Te conectaste a: " + server);
	            System.out.println("Hablá todo lo que quieras");
	            
	            
	            /*PROBANDO INTERFAZ*/
	            
	            int mundo = Integer.valueOf((String) pantallaPrincipal.getComboBox().getSelectedItem());
	            
	            User user = new User(mundo, this.textField.getText() );
	            
	            String jsonInString = mapper.writeValueAsString(user);

	            PrintWriter out = new PrintWriter(socket.getOutputStream()); //OBTENGO EL CANAL DE SALIDA DEL SOCKET HACIA EL SERVIDOR
	            out.println(jsonInString); // LE ENVIO EL MENSAJE DE SALA Y NICKNAME

	            out.flush();
	            
	            //INSTANCEANDO UN PERSONAJE DENTRO DE MI CLIENTE Y MANDARLO AL SERVIDOR
	            
	            Personaje elFerra = this.personajes.get((String)this.comboBoxPersonajes.getSelectedItem());
	            
	            ///FIN INSTANCIA PERSONAJE////////////////////////////////////////////////////////////////////

	            //INSTANCIA DEL CLIENTE, DONDE SE MANDA MSJ CON EL SERVIDOR
	            ClientThread newClient = new ClientThread(socket);
	            Thread thread = new Thread(newClient);
	            thread.start();
	            
	           
	            //SE CREA ESTE BOTON DESPUES DE INSTANCEAR EL PERSONAJE, Y LO QUE HACE ES MANDAR AL SERVIDOR LA SALUD DE ELFERRA
                JButton btnGetSalud = new JButton("mostra tu salud!");
                btnGetSalud.addActionListener(new ActionListener() {
        			public void actionPerformed(ActionEvent arg0) {
        				String mensaje = "";
        					mensaje = String.valueOf(elFerra.getSalud());
        					newClient.enviarDatos(mensaje);
        			}
        		});
                btnGetSalud.setBounds(300, 250, 200, 25);
        		contentPane.add(btnGetSalud);
        		
        		//// FIN DE BOTON DE MANDAR SALUD ///
                
        		
        		/* ESTE WHILE ES PARA MANDAR VARIOS MENSAJES, ES DECIR QUE PERTENECIA AL CHAT, NO LO BORRÉ PORQUE SEGURO SIRVE
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

	        } catch (Exception e) {
	        	System.out.println(e.getMessage());
	        }
	    
			//dispose();
		}else{
			labelErrorTipeo.setVisible(true);
		}
	}
}
