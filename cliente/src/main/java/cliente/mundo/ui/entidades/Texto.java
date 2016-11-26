package cliente.mundo.ui.entidades;


import cliente.mundo.ui.IDibujable;

import java.awt.*;

public class Texto implements IDibujable {

	Point posicionFija;

	public Texto(){
		posicionFija = new Point(12,12);
	}

	@Override
	public void paintComponent(Graphics g, Rectangle region) {
		// TODO Auto-generated method stub

		if(region.contains(this.posicionFija)){
		Graphics2D g2d = (Graphics2D) g.create();

       g2d.drawString("Titulo", 400, 200);
        g2d.dispose();
		}

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
