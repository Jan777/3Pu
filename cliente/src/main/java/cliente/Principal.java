package cliente;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.codehaus.jackson.map.ObjectMapper;

import main.java.mergame.interfaz.Mundo;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.util.ArrayList;
import java.util.*;

import javax.swing.JTextField;
import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.PrintWriter;
import java.net.Socket;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.Color;
import java.awt.event.KeyAdapter;

public class Principal extends JFrame {

	//ATRIBUTOS PARA LA PANTALLA//
	private JPanel contentPane;
	private JTextField textFieldUsuario;
	private List<Mundo> listaMundos;
	private JPasswordField passwordField;
	private JLabel labelError;
	private JComboBox comboBox ;
	
	private Usuario usuario;
	
	//ATRIBUTOS PARA LA CONEXION//
		private Socket socket;
		private Scanner entradaDatos;
		private PrintWriter salidaDatos;
		
	public JComboBox getComboBox() {
		return comboBox;
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 442, 408);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		//COMPONENTES SIN USO//
		JLabel lblMergame = new JLabel("MerGame");
		lblMergame.setFont(new Font("Khmer OS", Font.BOLD, 50));
		lblMergame.setHorizontalAlignment(SwingConstants.CENTER);
		lblMergame.setBounds(35, 47, 270, 58);
		contentPane.add(lblMergame);

		JLabel lblInsertTuUsuario = new JLabel("Ingresá tu usuario:");
		lblInsertTuUsuario.setBounds(95, 135, 190, 15);
		contentPane.add(lblInsertTuUsuario);
		
		JLabel lblYAcTu = new JLabel("Y acá tu contraseña:");
		lblYAcTu.setBounds(95, 193, 270, 15);
		contentPane.add(lblYAcTu);
		
		JLabel lblNewLabel = new JLabel("Elegí el mundo:");
		lblNewLabel.setBounds(95, 264, 123, 15);
		contentPane.add(lblNewLabel);
		lblNewLabel.setVisible(false);
		
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
					conectarConServidor();
			    }
			}
		});

		JButton btnSalvAClaudia = new JButton("Salvá a Claudia");
		btnSalvAClaudia.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER){
					conectarConServidor();
			    }
			}
		});
		btnSalvAClaudia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				conectarConServidor();
			}
		});
		btnSalvAClaudia.setBounds(153, 327, 151, 25);
		contentPane.add(btnSalvAClaudia);

		listaMundos = new ArrayList<Mundo>();
		Mundo mundoEjemplo = new Mundo();
		listaMundos.add(mundoEjemplo);

		comboBox = new JComboBox();
		comboBox.setBounds(234, 259, 133, 24);
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
					conectarConServidor();
			    }
			}
		});

		
	}


	// compruebo que el usuario complete el nombre del personaje.
		public void conectarConServidor(){
			if (!this.textFieldUsuario.getText().equals("")) {
				
				String server = "localhost";
		        try {
		            final int PORT = 5553;
		            socket = new Socket(server, PORT);
		            entradaDatos = new Scanner(socket.getInputStream());
		            salidaDatos = new PrintWriter(socket.getOutputStream());
		            usuario = new Usuario(socket);
		            ObjectMapper mapper = new ObjectMapper();
		            System.out.println("Te conectaste a: " + server);		            
		            
		            /*PROBANDO INTERFAZ*/
		            
		            int numMundo = Integer.valueOf((String) comboBox.getSelectedItem());
		            String nombreUsuario = textFieldUsuario.getText();
		            String passUsuario = passwordField.getText();
		            
		            MensajeLogin login = new MensajeLogin(numMundo, nombreUsuario, passUsuario);
		            String jsonInString = mapper.writeValueAsString(login);
		            
		            salidaDatos.println(jsonInString);
		            salidaDatos.flush();
		            
		            //LO HICE DE ESTA FORMA PARA PODER ACTIVAR EL ERROR DE USUARIO INCORRECTO//
		            leer();
		            //////////////////////////////////////////////////////////////
		        } catch (Exception e) {
		        	System.out.println(e.getMessage());
		        }
			}else{
				labelError.setVisible(true);
			}
		}
			
		public void leer(){
			Thread leer_hilo = new Thread(new Runnable(){
				public void run(){
					try{
						while(true){
							if (entradaDatos.hasNext()) {
					            String mensajeEntrante = entradaDatos.nextLine();
					            System.out.println(mensajeEntrante);
					            if(mensajeEntrante.equals("Datos Incorrectos")){
					            	labelError.setVisible(true);
					            	socket.close();
					            }	
					            
					            if(mensajeEntrante.equals("Datos Correctos")){
					            	PantallaUsuario pantallaUsuario = new PantallaUsuario(usuario);
					    			pantallaUsuario.setVisible(true);
					    			dispose();
					            }
					        }
						}
					}
					catch(Exception e){
						e.printStackTrace();
					}
				}
			});
			leer_hilo.start();
		}

}
