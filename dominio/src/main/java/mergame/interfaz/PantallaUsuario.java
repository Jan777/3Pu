package main.java.mergame.interfaz;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
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
	
	public PantallaUsuario(Principal pantallaPrincipal) {
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
				if(textField.getText().equals("")){
					comprobarTipeo();
				}
				else
					dispose();
			}
		});
		

		
		btnCrearNuevoPersonaje.setBounds(389, 304, 259, 25);
		contentPane.add(btnCrearNuevoPersonaje);
		
		JLabel lblSeleccionPersonaje = new JLabel("Seleccioná personaje:");
		lblSeleccionPersonaje.setBounds(71, 87, 234, 15);
		contentPane.add(lblSeleccionPersonaje);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(71, 108, 146, 24);
		contentPane.add(comboBox);
		comboBox.addItem("Elfo");
		comboBox.addItem("Enano");
		comboBox.addItem("Humano");
		comboBox.addItem("Orco");
		
		JLabel lblAhoraSeleccionUna = new JLabel("Ahora seleccioná una casta:");
		lblAhoraSeleccionUna.setBounds(71, 144, 232, 15);
		contentPane.add(lblAhoraSeleccionUna);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(71, 163, 156, 24);
		contentPane.add(comboBox_1);
		comboBox_1.addItem("Guerrero");
		comboBox_1.addItem("Mago");
		
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
	public void comprobarTipeo(){
		if (!textField.getText().equals("")) {
			dispose();
		}else{
			labelErrorTipeo.setVisible(true);
		}
	}
}
