package cliente.mundo.ui.entidades;

import cliente.mundo.ui.IDibujable;

import java.awt.*;
import java.util.ArrayList;

public class Relieve implements IDibujable {

	Point tamMapa;
	ArrayList<Piso> relieve;

	public Relieve(Point tamMapa){

		this.tamMapa = tamMapa;
		this.relieve = new ArrayList<Piso>();
		GenerarBordes();

	}

	private void GenerarBordes(){

		for(int i=0; i<tamMapa.getX();i++)
			relieve.add(new Piso(new Point(0,i)));

		for(int i=0; i<tamMapa.getY();i++)
			relieve.add(new Piso(new Point(i,(int)tamMapa.getX()-1)));

		for(int i=0; i<tamMapa.getX();i++)
			relieve.add(new Piso(new Point((int)tamMapa.getY()-1,i)));

		for(int i=0; i<tamMapa.getY();i++)
			relieve.add(new Piso(new Point(i,0)));


	}

	//Pinta los tails que contiene relieve
	@Override
	public void paintComponent(Graphics g, Rectangle region) {
		for(IDibujable dib : relieve)
			dib.paintComponent(g, region);

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
