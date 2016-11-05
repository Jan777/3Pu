package main.java.mergame.interfaz;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.Color;
import java.awt.event.KeyAdapter;

public class Principal extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldUsuario;
	private List<Mundo> listaMundos;
	private JPasswordField passwordField;
	private JLabel labelError;

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

		JLabel lblMergame = new JLabel("MerGame");
		lblMergame.setFont(new Font("Khmer OS", Font.BOLD, 50));
		lblMergame.setHorizontalAlignment(SwingConstants.CENTER);
		lblMergame.setBounds(35, 47, 270, 58);
		contentPane.add(lblMergame);

		JLabel lblInsertTuUsuario = new JLabel("Ingresá tu usuario:");
		lblInsertTuUsuario.setBounds(95, 135, 190, 15);
		contentPane.add(lblInsertTuUsuario);

		textFieldUsuario = new JTextField();
		textFieldUsuario.setBounds(95, 162, 270, 19);
		contentPane.add(textFieldUsuario);
		textFieldUsuario.setColumns(10);
		textFieldUsuario.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER){
			    	comprobarConexion();
			    }
			}
		});

		JLabel lblYAcTu = new JLabel("Y acá tu contraseña:");
		lblYAcTu.setBounds(95, 193, 270, 15);
		contentPane.add(lblYAcTu);

		JButton btnSalvAClaudia = new JButton("Salvá a Claudia");
		btnSalvAClaudia.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER){
			    	comprobarConexion();
			    }
			}
		});
		btnSalvAClaudia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				comprobarConexion();
			}
		});
		btnSalvAClaudia.setBounds(153, 327, 151, 25);
		contentPane.add(btnSalvAClaudia);

		listaMundos = new ArrayList<Mundo>();
		Mundo mundoEjemplo = new Mundo();
		listaMundos.add(mundoEjemplo);

		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(234, 259, 133, 24);
		contentPane.add(comboBox);
		comboBox.addItem("Mundo 1");
		comboBox.addItem("Mundo 2");
		comboBox.addItem("Mundo 3");

		JLabel lblNewLabel = new JLabel("Elegí el mundo:");
		lblNewLabel.setBounds(95, 264, 123, 15);
		contentPane.add(lblNewLabel);

		passwordField = new JPasswordField();
		passwordField.setBounds(95, 213, 270, 19);
		contentPane.add(passwordField);
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER){
			    	comprobarConexion();
			    }
			}
		});

		labelError = new JLabel("Error de usuario o contraseña");
		labelError.setForeground(Color.RED);
		labelError.setBounds(95, 108, 270, 15);
		contentPane.add(labelError);
		labelError.setVisible(false);
	}

	public void comprobarConexion() {
		if (textFieldUsuario.getText().equals("test") && passwordField.getText().equals("test")) {
			PantallaUsuario pantallaUsuario = new PantallaUsuario(this);
			pantallaUsuario.setVisible(true);
			dispose();
		} else {
			labelError.setVisible(true);
		}
	}

	public String getTextFieldUsuario() {
		return textFieldUsuario.getText();
	}

}
