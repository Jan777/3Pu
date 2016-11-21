package cliente;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JList;
import javax.swing.JButton;

public class PantallaJuego extends JFrame {

	private JPanel contentPane;
	private Usuario usuarioIn;
	private JList listaPersonajesConectados;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PantallaJuego frame = new PantallaJuego();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public PantallaJuego(){
		
	}
	
	public PantallaJuego(Usuario usuarioIn) {
		this.usuarioIn = usuarioIn;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 323, 322);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombrePersonaje = new JLabel(usuarioIn.getPersonaje().getNombre());
		lblNombrePersonaje.setBounds(10, 11, 133, 14);
		contentPane.add(lblNombrePersonaje);
		
		JLabel lblTituloVida = new JLabel("Vida");
		lblTituloVida.setBounds(10, 31, 37, 14);
		contentPane.add(lblTituloVida);
		
		JLabel lblTituloNivel = new JLabel("Nivel");
		lblTituloNivel.setBounds(10, 52, 46, 14);
		contentPane.add(lblTituloNivel);
		
		JLabel lblVida = new JLabel(String.valueOf(usuarioIn.getPersonaje().getSalud()));
		lblVida.setBounds(53, 31, 46, 14);
		contentPane.add(lblVida);
		
		JLabel lblNivel = new JLabel(String.valueOf(usuarioIn.getPersonaje().getNivel()));
		lblNivel.setBounds(53, 52, 46, 14);
		contentPane.add(lblNivel);
		
		JLabel lblPersonajesConectados = new JLabel("Personajes conectados:");
		lblPersonajesConectados.setBounds(10, 93, 161, 14);
		contentPane.add(lblPersonajesConectados);
		
		listaPersonajesConectados = new JList();
		listaPersonajesConectados.setBounds(10, 118, 133, 148);
		contentPane.add(listaPersonajesConectados);
		
		JButton btnAtacar = new JButton("FuturoAtacar");
		btnAtacar.setBounds(164, 186, 133, 23);
		contentPane.add(btnAtacar);
		
		//Crea el hilo que refresca los datos en pantalla
		HiloPantallaJuego hiloPantalla = new HiloPantallaJuego(usuarioIn, listaPersonajesConectados);
		Thread hilo = new Thread(hiloPantalla);
		hilo.start();
	}
}
