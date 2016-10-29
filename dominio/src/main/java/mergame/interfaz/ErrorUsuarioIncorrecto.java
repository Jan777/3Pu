package main.java.mergame.interfaz;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class ErrorUsuarioIncorrecto extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ErrorUsuarioIncorrecto frame = new ErrorUsuarioIncorrecto();
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
	public ErrorUsuarioIncorrecto() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 318, 103);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLePifiasteAl = new JLabel("Le pifiaste al usuario o a la contraseña");
		lblLePifiasteAl.setHorizontalAlignment(SwingConstants.CENTER);
		lblLePifiasteAl.setBounds(12, 12, 292, 15);
		contentPane.add(lblLePifiasteAl);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setBounds(97, 39, 117, 25);
		contentPane.add(btnVolver);
	}

}
