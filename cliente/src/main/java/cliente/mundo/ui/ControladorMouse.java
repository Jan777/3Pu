package cliente.mundo.ui;

import cliente.mundo.partida.CoordinadorEventos;
import cliente.mundo.partida.Jugador;
import cliente.mundo.ui.entidades.TextInfo;
import cliente.mundo.ui.math.Bresenham;
import cliente.mundo.ui.math.MathUtils;
import cliente.usuario.Usuario;
import cliente.usuario.UsuarioPosicion;
import org.codehaus.jackson.map.ObjectMapper;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

public class ControladorMouse  extends TimerTask implements MouseListener {

	 private LinkedList<Point> puntosAlObjetivo;
	 private Jugador jugadorControlado;
	 private Timer timerMovimiento;
	private CoordinadorEventos coordEventos;
	private Usuario usuario;

	public ControladorMouse(){
		 this.puntosAlObjetivo = new LinkedList<Point>();
		 this.timerMovimiento = new Timer();
		 timerMovimiento.schedule(this,0, 200);
	 }
	 //Singleton
	 private static ControladorMouse instance = null;

	   public static ControladorMouse getInstance() {
		      if(instance == null) {
		         instance = new ControladorMouse();
		      }
		      return instance;
		   }

	@Override
	public void mouseClicked(MouseEvent arg0) {

		//Si tengo algun jugador para controlar, calculo posicion y actualizo jugador
		//Confrimando con el coordinador de eventos, que es valido ir a ese punto
		if(jugadorControlado!=null){

			Point puntoDestino =  getPuntoAbsoluto(MathUtils.isoTo2D(arg0.getPoint()));
			if(this.coordEventos.PuedoNuevaPosicion(puntoDestino,jugadorControlado))
				puntosAlObjetivo = Bresenham.linea(jugadorControlado.getPos(),puntoDestino);
			else
				TextInfo.getInstance().Log("No se puede mover a esa ubicaci�n.");

		}
	}

	private Point getPuntoAbsoluto(Point isoTo2D) {
		Rectangle regionVisible = UIService.getInstance().getRegionVisible();
		int anchoCeldaX =(int) (UIComponent.ANCHO_UI_COMPONENT/ regionVisible.getWidth());
		int altoCeldaY = (int) (UIComponent.ALTO_UI_COMPONENT/ regionVisible.getHeight());
		int x = (int)isoTo2D.getX()/anchoCeldaX;
		int y = (int)isoTo2D.getY()/altoCeldaY;
		int absoluteX = (int) (regionVisible.getX()+x);
		int absoluteY = (int) (regionVisible.getY()+y);
		return new Point(absoluteX-2,absoluteY-1);
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void setJugadorControlado(Jugador jugadorControlado) {
		this.jugadorControlado = jugadorControlado;

	}

	@Override
	public void run() {
		//Si tengo alguna posicion pendiente me muevo
		if(puntosAlObjetivo !=null && puntosAlObjetivo.isEmpty()==false){
			Point posicion = puntosAlObjetivo.remove();
			jugadorControlado.setPos(posicion);
			if(usuario!=null) {
				try {
					PrintWriter pw = new PrintWriter(usuario.getSocket().getOutputStream());
					ObjectMapper mapper = new ObjectMapper();
					UsuarioPosicion usuarioPosicion = new UsuarioPosicion(usuario);
					usuarioPosicion.setX((int) posicion.getX());
					usuarioPosicion.setY((int) posicion.getY());

					String mensaje = mapper.writeValueAsString(usuarioPosicion);
					//TextInfo.getInstance().Log("Mando("+posicion.toString()+")");
					pw.println("MOVI" + mensaje);
					pw.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}

	}

	public void bindCoordinador(CoordinadorEventos coordEventos) {
		this.coordEventos = coordEventos;

	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
