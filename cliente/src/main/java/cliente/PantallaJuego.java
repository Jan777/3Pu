package cliente;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import main.java.mergame.individuos.personajes.Personaje;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;

public class PantallaJuego extends JFrame {

	private JPanel contentPane;
	private PantallaCreacionPersonaje padre;
	private Personaje personajeActivo;
	private JComboBox comboAtacables; 

	// public static void main(String[] args) {
	// EventQueue.invokeLater(new Runnable() {
	// public void run() {
	// try {
	// PantallaJuego frame = new PantallaJuego();
	// frame.setVisible(true);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	// });
	// }

	public PantallaJuego() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 384);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNombreDelPersonaje = new JLabel("Nombre del personaje");
		lblNombreDelPersonaje.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombreDelPersonaje.setBounds(10, 11, 144, 14);
		contentPane.add(lblNombreDelPersonaje);

		JLabel lblVida = new JLabel("Vida:");
		lblVida.setBounds(31, 31, 29, 14);
		contentPane.add(lblVida);

		JLabel lblPuntosAtaque = new JLabel("Puntos de ataque:");
		lblPuntosAtaque.setBounds(31, 51, 110, 14);
		contentPane.add(lblPuntosAtaque);

		JLabel lblHP = new JLabel("HP");
		lblHP.setBounds(153, 31, 46, 14);
		contentPane.add(lblHP);

		JLabel lblPA = new JLabel("PA");
		lblPA.setBounds(153, 51, 46, 14);
		contentPane.add(lblPA);
		
		JLabel lblAtacables = new JLabel("Atacables:");
		lblAtacables.setBounds(31, 95, 94, 14);
		contentPane.add(lblAtacables);
		
		comboAtacables = new JComboBox();
		comboAtacables.setBounds(31, 120, 144, 20);
		contentPane.add(comboAtacables);
		comboAtacables.addItem("hola");
	}


	public JComboBox getComboAtacables() {
		return comboAtacables;
	}


	public void setComboAtacables(JComboBox comboAtacables) {
		this.comboAtacables = comboAtacables;
	}


	public PantallaJuego(PantallaCreacionPersonaje pantallaPersonaje, Personaje personajeActivo) {
		this.padre = pantallaPersonaje;
		this.personajeActivo = personajeActivo;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 384);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNombreDelPersonaje = new JLabel(pantallaPersonaje.getNombrePersonaje());
		lblNombreDelPersonaje.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombreDelPersonaje.setBounds(10, 11, 144, 14);
		contentPane.add(lblNombreDelPersonaje);

		JLabel lblVida = new JLabel("Vida:");
		lblVida.setBounds(31, 31, 29, 14);
		contentPane.add(lblVida);

		JLabel lblNewLabel = new JLabel("Puntos de ataque:");
		lblNewLabel.setBounds(31, 51, 97, 14);
		contentPane.add(lblNewLabel);

		JLabel lblHp = new JLabel(Integer.toString(personajeActivo.getSalud()));
		lblHp.setBounds(129, 31, 46, 14);
		contentPane.add(lblHp);

		JLabel lblPa = new JLabel("PA");
		lblPa.setBounds(129, 51, 46, 14);
		contentPane.add(lblPa);
		
		comboAtacables = new JComboBox();
		comboAtacables.setBounds(31, 120, 144, 20);
		contentPane.add(comboAtacables);
		comboAtacables.addItem("hola");
	}	
	
	
}