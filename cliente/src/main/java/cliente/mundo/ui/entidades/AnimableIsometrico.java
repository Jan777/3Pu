package cliente.mundo.ui.entidades;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;


public class AnimableIsometrico extends TimerTask{

	private BufferedImage tileEnCurso;
	private BufferedImage imagenStandBy1;
	private BufferedImage imagenStandBy2;
	private BufferedImage imagenDiagonalDerecha;
	private BufferedImage imagenDiagonalIzquierda;
	private BufferedImage imagenBajaIzquierda;
	private BufferedImage imagenBajaDerecha;

	//Posiciones de movimiento
	private final static String DIAGONAL_DERECHA = "diag1";
	private final static String DIAGONAL_IZQUIERDA = "diag2";
	private final static String BAJA_IZQUIERDA = "izq1";
	private final static String BAJA_DERECHA = "izq2";
	private Point puntoPrevio;
	private Timer timerCambioAnimacion;

	public AnimableIsometrico(AnimableIsometricoBuilder build){


		timerCambioAnimacion = new Timer();
	    timerCambioAnimacion.schedule(this,0,200);


		 try {
			 puntoPrevio = build.puntoInicial;
			 imagenStandBy1 = ImageIO.read(new File(build.rutaImagenStandBy1));
			 imagenStandBy2 = ImageIO.read(new File(build.rutaImagenStandBy2));
			 imagenDiagonalDerecha = ImageIO.read(new File(build.rutaImagenDiagonalDerecha));
			 imagenDiagonalIzquierda = ImageIO.read(new File(build.rutaImagenDiagonalIzquierda));
			 imagenBajaIzquierda = ImageIO.read(new File(build.rutaImagenBajaIzquierda));
			 imagenBajaDerecha = ImageIO.read(new File(build.rutaImagenBajaDerecha));
			 tileEnCurso = this.imagenStandBy1;

	        } catch (IOException ex) {
	            ex.printStackTrace();
	        }
	}


	public void run() {

		if(this.tileEnCurso == this.imagenStandBy1)
			this.tileEnCurso = this.imagenStandBy2;
	      else
	    	  this.tileEnCurso = this.imagenStandBy1;


	}


	public BufferedImage getFrame(){
		return this.tileEnCurso;
	}

	public void actualizarPosicion(Point pt) {
		setSprite(identificarDireccion(this.puntoPrevio,pt));
		this.puntoPrevio = pt;

	}

	private void setSprite(String direccion) {

		this.tileEnCurso = this.imagenStandBy1;
		switch(direccion){
			case AnimableIsometrico.BAJA_DERECHA:
				this.tileEnCurso = this.imagenBajaDerecha;
				break;
			case AnimableIsometrico.BAJA_IZQUIERDA:
				this.tileEnCurso = this.imagenBajaIzquierda;
				break;
			case AnimableIsometrico.DIAGONAL_DERECHA:
				this.tileEnCurso = this.imagenDiagonalDerecha;
				break;
			case AnimableIsometrico.DIAGONAL_IZQUIERDA:
				this.tileEnCurso = this.imagenDiagonalIzquierda;
				break;
		}

	}

	private String identificarDireccion(Point pt1, Point pt2) {

		double x1 = pt1.getX();
		double x2 = pt2.getX();
		double y1 = pt1.getY();
		double y2 = pt2.getY();

		if(x2 < x1 && y2 == y1)
			return AnimableIsometrico.DIAGONAL_IZQUIERDA;
		if(x2 <= x1 && y2 < y1)
			return AnimableIsometrico.DIAGONAL_DERECHA;
		if(x2 > x1 && y2 >= y1)
			return AnimableIsometrico.BAJA_DERECHA;
		if(x2 == x2 && y2>= y1)
		return AnimableIsometrico.BAJA_IZQUIERDA;

		return "";

	}
}
