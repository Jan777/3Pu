package cliente;

import java.awt.image.BandCombineOp;
import java.io.IOException;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

import javax.swing.DefaultListModel;
import javax.swing.JList;

import org.codehaus.jackson.map.ObjectMapper;

public class HiloPantallaJuego implements Runnable {

	private Usuario usuario;
	private Socket socket;
	private JList listaPersonajesConectados;
	private ObjectMapper mapper;
	private Scanner input;
	private Scanner output;
	private String inputString = "";
	private MensajeListaDePersonajesConectados mensaje;

	private int verCuantasVecesEntra = 0;
	public HiloPantallaJuego() {

	}

	public HiloPantallaJuego(Usuario usuario, JList listaPersonajesConectados) {
		this.usuario = usuario;
		this.socket = usuario.getSocket();
		this.listaPersonajesConectados = listaPersonajesConectados;
	}

	@Override
	public void run() {
		//mensaje.addObserver(this);
		mapper = new ObjectMapper();
		int banderaBoba = 1;
		try {
			while (banderaBoba == 1) {
				banderaBoba=0;
				this.input = new Scanner(this.socket.getInputStream());
				inputString = input.nextLine();
				
				MensajeListaDePersonajesConectados listaRecibida = mapper.readValue(inputString,
						MensajeListaDePersonajesConectados.class);

				DefaultListModel modeloLista = new DefaultListModel();

				for (String nombrePersonajes : listaRecibida.getListaPersonajes()) {
					modeloLista.addElement(nombrePersonajes);
				}
				listaPersonajesConectados.setModel(modeloLista);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

//	@Override
//	public void update(Observable o, Object arg) {
//		mapper = new ObjectMapper();
//		int banderaBoba = 1;
//		try {
//			//while (banderaBoba == 1) {
//				banderaBoba=0;
//				this.input = new Scanner(this.socket.getInputStream());
//				inputString = input.nextLine();
//
//				MensajeListaDePersonajesConectados listaRecibida = mapper.readValue(inputString,
//						MensajeListaDePersonajesConectados.class);
//
//				DefaultListModel modeloLista = new DefaultListModel();
//
//				for (String nombrePersonajes : listaRecibida.getListaPersonajes()) {
//					modeloLista.addElement(nombrePersonajes);
//					System.out.println("re loopea");
//				}
//				listaPersonajesConectados.setModel(modeloLista);
//				System.out.println("LA CONCHA DE LA LORA");
//				
//			//}
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}

}
