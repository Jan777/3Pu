package cliente.mundo.ui.entidades;

import cliente.mundo.ui.IDibujable;
import cliente.mundo.ui.math.MathUtils;

import java.awt.*;


public class PersonajeElfo implements IDibujable, ILife {

	private AnimableIsometrico animable;
	private Point posicion;
	private String nombre;
	private int vida;


	public PersonajeElfo(String nombre){
		this.nombre = nombre;
		this.posicion = new Point(1,1);
		this.vida = 100;

		AnimableIsometricoBuilder builder = new AnimableIsometricoBuilder();
		builder.puntoInicial = posicion;
		builder.rutaImagenStandBy1 = ".\\Texturas\\ElfoStb1.png";
		builder.rutaImagenStandBy2 = ".\\Texturas\\ElfoStb2.png";
		builder.rutaImagenDiagonalDerecha = ".\\Texturas\\ElfoDiagDer.png";
		builder.rutaImagenDiagonalIzquierda = ".\\Texturas\\ElfoDiagIzq.png";
		builder.rutaImagenBajaIzquierda =".\\Texturas\\ElfoBajaIzq.png";
		builder.rutaImagenBajaDerecha =".\\Texturas\\ElfoBajaDer.png";

		this.animable = new AnimableIsometrico(builder);

	}

	@Override
	public void paintComponent(Graphics g, Rectangle r) {


			double xRelativo =  this.posicion.getX() -r.getX();
			double yRelativo = this.posicion.getY() -r.getY();
			double tamRegionX = g.getClipBounds().getWidth()/r.getWidth();
			double tamRegionY = g.getClipBounds().getHeight()/r.getHeight();

			Graphics2D g2d = (Graphics2D) g.create();

			int posOgroX = (int) ( xRelativo * tamRegionX);
			int posOgroY = (int) ( yRelativo * tamRegionY);
			Point puntoTransformado = MathUtils.twoDToIso(new Point(posOgroX,posOgroY));

	        g2d.drawImage(animable.getFrame(),(int)puntoTransformado.getX(),(int)puntoTransformado.getY(),100,100,null);
	       	g2d.setColor(Color.WHITE);
	        g2d.drawString(this.nombre+" "+vida+"%",(int)puntoTransformado.getX()+25,(int)puntoTransformado.getY()+20);
	        g2d.setColor(Color.RED);
	        g2d.fillRect((int)puntoTransformado.getX()+30,(int)puntoTransformado.getY()+25,50,2);
	        g2d.setColor(Color.green);
			g2d.fillRect((int)puntoTransformado.getX()+30,(int)puntoTransformado.getY()+25,(vida*50/100),2);
	        g2d.dispose();


	}

	@Override
	public Point getPos() {
		return posicion;
	}

	@Override
	public void setVida(int vida) {
		this.vida = vida;
	}


	@Override
	public void setPos(Point pt) {
		this.animable.actualizarPosicion(pt);
		this.posicion = pt;
	}
}
