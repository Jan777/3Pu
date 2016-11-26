package cliente.mundo.ui.entidades;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;


import cliente.mundo.ui.IDibujable;
import cliente.mundo.ui.math.MathUtils;

public class Golpe extends TimerTask implements IDibujable{

	private BufferedImage sprite1;
	private BufferedImage sprite2;
	private BufferedImage sprite3;
	private BufferedImage sprite4;
	private BufferedImage tile;
	private int i=0,j=0;

	private Point posicion;
	private Boolean visible=false;

	private Timer timerCambioAnimacion;

	public Golpe(){


		timerCambioAnimacion = new Timer();
	    timerCambioAnimacion.schedule(this,0,200);


		 try {

			 sprite1 = ImageIO.read(new File(".\\Texturas\\pelea1.png"));
			 sprite2 = ImageIO.read(new File(".\\Texturas\\pelea2.png"));
			 sprite3 = ImageIO.read(new File(".\\Texturas\\pelea3.png"));
			 sprite4 = ImageIO.read(new File(".\\Texturas\\pelea4.png"));
			 tile = this.sprite1;

	        } catch (IOException ex) {
	            ex.printStackTrace();
	        }
	}

	public void Golpe(Point pt){
		this.posicion = pt;
		tile = sprite1;
		this.visible=true;
	}

	public void run() {

		if(i>3){
			i=0;
			j++;
			if(j>3){
				j=0;
			this.visible = false;
			}
		}

		switch(i){
		case 0: tile = sprite1; break;
		case 1: tile = sprite2; break;
		case 2: tile = sprite3; break;
		case 3: tile = sprite4; break;
		}
		i++;

	}

	@Override
	public void paintComponent(Graphics g, Rectangle r) {

		if(this.visible==false)
			return;

			double xRelativo =  this.posicion.getX() -r.getX();
			double yRelativo = this.posicion.getY() -r.getY();
			double tamRegionX = g.getClipBounds().getWidth()/r.getWidth();
			double tamRegionY = g.getClipBounds().getHeight()/r.getHeight();

			Graphics2D g2d = (Graphics2D) g.create();

			int posOgroX = (int) ( xRelativo * tamRegionX);
			int posOgroY = (int) ( yRelativo * tamRegionY);
			Point puntoTransformado = MathUtils.twoDToIso(new Point(posOgroX,posOgroY));

	        g2d.drawImage(tile,(int)puntoTransformado.getX(),(int)puntoTransformado.getY(),100,100,null);
	    	g2d.setFont(new Font("default", Font.BOLD, 14));
	        g2d.setColor(Color.RED);
	        g2d.drawString("�ATACANDO!",(int)puntoTransformado.getX()+25,(int)puntoTransformado.getY()+20);
	        g2d.dispose();


	}


	@Override
	public Point getPos() {
		return this.posicion;
	}


	@Override
	public void setPos(Point pt) {
		this.posicion = pt;

	}


	public Boolean getVisible() {
		return visible;
	}


	public void setVisible(Boolean visible) {
		this.visible = visible;
	}


}