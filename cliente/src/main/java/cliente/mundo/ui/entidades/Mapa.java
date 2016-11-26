package cliente.mundo.ui.entidades;


import cliente.mundo.ui.IDibujable;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Mapa implements IDibujable {

	private BufferedImage tile;

	public Mapa(){
		 this(new Point(100,100),new Point(10,10),new Point(50,50));
	}

	public Mapa(Point tam, Point gril, Point inicial){


		 try {
	            tile = ImageIO.read(new File("/home/gparis/IdeaProjects/jrpg/cliente/src/main/resources/Texturas/textura_pasto.jpg"));

	        } catch (IOException ex) {
	            ex.printStackTrace();
	        }
	}
	@Override
	public void paintComponent(Graphics g, Rectangle r) {
		  Graphics2D g2d = (Graphics2D) g.create();

        int tileWidth = tile.getWidth();
        int tileHeight = tile.getHeight();
        for (int y = 0; y < g.getClipBounds().getHeight(); y += tileHeight) {
            for (int x = 0; x < g.getClipBounds().getWidth(); x += tileWidth) {
                g2d.drawImage(tile, x, y,null);
            }
        }

        g2d.setColor(Color.WHITE);
        g2d.drawString("REGION VISIBLE  ("+r.toString()+")", 20, 20);
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
