package cliente.mundo.ui.entidades;

import cliente.mundo.ui.IDibujable;

import java.awt.*;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Timer;
import java.util.TimerTask;

/***
 * Clase para mostrar graficamente texto del juego en pantalla.
 * Permite mostrar 10 renglones de texto.
 * Cada 5 segundos, la cola se va flusheando
 *
 */
public class TextInfo extends TimerTask implements IDibujable {

	 //Singleton
	 private static TextInfo instance = null;

	   public static TextInfo getInstance() {
		      if(instance == null) {
		         instance = new TextInfo();
		      }
		      return instance;
		   }

	private Timer timerFlush;
	private LinkedList<String> mensajes;

	public TextInfo(){

		mensajes = new LinkedList<String>();

		mensajes.addFirst("Bienvenidos a Mergame!");
		mensajes.addFirst("*********************************************************");
		mensajes.addFirst("Utiliza el mouse para mover a tu personaje.");
		mensajes.addFirst("Utiliza las flehcas del teclado para desplazarte por pantalla");
		mensajes.addFirst(" ");
		mensajes.addFirst("Si estas al lado de un jugador, puedes utilizar la tecla space ");
		mensajes.addFirst("para comenzar una batalla.");
		mensajes.addFirst("Utiliza los numeros del 1 al 6 para seleccionar tu ataque.");
		mensajes.addFirst(" ");


		timerFlush = new Timer();
		timerFlush.schedule(this,0,5000);
	}
	public void Log(String text){
		mensajes.addLast(text);
		if(mensajes.size()>10)
			mensajes.removeFirst();
	}
	@Override
	public void paintComponent(Graphics g, Rectangle region) {

		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setColor(Color.WHITE);
		g2d.setFont(new Font("default", Font.TRUETYPE_FONT, 14));

		int j=550;
		@SuppressWarnings("unchecked")
		LinkedList<String> msj = (LinkedList<String>) mensajes.clone();
		ListIterator<String> listIterator = msj.listIterator();
		while (listIterator.hasNext()) {
			j-=15;
			g2d.drawString(">"+listIterator.next(),30,j);
		}

		g2d.dispose();

	}

	@Override
	public Point getPos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPos(Point pt) {
		// TODO Auto-generated method stub

	}

	@Override
	public void run() {
		if(mensajes!=null && mensajes.isEmpty()==false)
			mensajes.removeFirst();

	}

}
