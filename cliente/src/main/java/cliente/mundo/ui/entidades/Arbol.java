package cliente.mundo.ui.entidades;


import cliente.mundo.ui.IDibujable;
import cliente.mundo.ui.math.MathUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Arbol implements IDibujable {

	private Point posicion;
	private BufferedImage tile;


	public Arbol(Point pos){

		this.posicion = pos;

		 try {
	            tile = ImageIO.read(new File(".\\Texturas\\textura_arbol.png"));

	        } catch (IOException ex) {
	            ex.printStackTrace();
	        }
	}
	@Override
	public void paintComponent(Graphics g, Rectangle r) {


			double xRelativo =  this.posicion.getX() -r.getX();
			double yRelativo = this.posicion.getY() -r.getY();
			double tamRegionX = g.getClipBounds().getWidth()/r.getWidth();
			double tamRegionY = g.getClipBounds().getHeight()/r.getHeight();

			Graphics2D g2d = (Graphics2D) g.create();

			int posX = (int) ( xRelativo * tamRegionX);
			int posY = (int) ( yRelativo * tamRegionY);
			Point puntoTransformado = MathUtils.twoDToIso(new Point(posX,posY));

	        g2d.drawImage(tile, (int) puntoTransformado.getX(), (int) puntoTransformado.getY(),100,100,null);
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


	}
