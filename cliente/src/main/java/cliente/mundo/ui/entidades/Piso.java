package cliente.mundo.ui.entidades;


import cliente.mundo.ui.IDibujable;
import cliente.mundo.ui.math.MathUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Piso implements IDibujable {

	private Point posicion;
	private BufferedImage tile;


	public Piso(Point pos){

		this.posicion = pos;

		 try {
			 	//Varia el sprite usado entre 3 modelos
			 	Random rand = new Random();
			 	int num=rand.nextInt(3);
	            tile = ImageIO.read(new File("/home/gparis/IdeaProjects/jrpg/cliente/Texturas/piso"+num+".png"));

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

			int posRectX = (int) ( xRelativo * tamRegionX);
		    int posRectY = (int) ( yRelativo * tamRegionY);
		    Point puntoTransformado3 = MathUtils.twoDToIso(new Point(posRectX,posRectY));

	        g2d.drawImage(tile,(int)puntoTransformado3.getX(),(int)puntoTransformado3.getY(),100,100,null);
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
