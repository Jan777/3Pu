package cliente.mundo.ui;

import com.sun.glass.events.KeyEvent;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class UIService {

	 private Collection<IDibujable> dibujables;
	 private Rectangle regionVisible;
	 private Point regionTotal;

	 //Singleton
	 private static UIService instance = null;
	   protected UIService() {
		   dibujables = Collections.synchronizedCollection(new ArrayList<IDibujable>());
		   regionVisible = new Rectangle(0,0,12,12);
		   regionTotal = new Point(96,96);


	   }

	public Rectangle getRegionVisible() {
		return regionVisible;
	}
	public void setRegionVisible(Rectangle regionVisible) {
		this.regionVisible = regionVisible;
	}

	public static UIService getInstance() {
	      if(instance == null) {
	         instance = new UIService();
	      }
	      return instance;
	   }

	   public void registrarComponente(IDibujable dib){
		   dibujables.add(dib);
	   }

	//Metodo de refresco, en cada frame refresca a los componentes registrados
	public void run(Graphics g) {
		synchronized (dibujables) {
			for (IDibujable dib : dibujables)
				dib.paintComponent(g, this.regionVisible);
		}
	}

	public boolean procesarTecla(int keyCode) {

			boolean movi=false;
			//Verifica no superar limites y actualiza posicion del mapa
			switch(keyCode){
			case KeyEvent.VK_LEFT:
				if(regionVisible.getX() > -(regionTotal.getX()/2)){
						regionVisible.setLocation(new Point((int)regionVisible.getX()-1,(int)regionVisible.getY()+1));
						movi=true;
				}
				break;
			case KeyEvent.VK_RIGHT:
				if((regionVisible.getX()+regionVisible.getWidth())< regionTotal.getX()+10){
					regionVisible.setLocation(new Point((int)regionVisible.getX()+1,(int)regionVisible.getY()-1));
					movi=true;
				}
				break;
			case KeyEvent.VK_UP:
				if(regionVisible.getY() > -(regionTotal.getX()/2)){
					regionVisible.setLocation(new Point((int)regionVisible.getX()-1,(int)regionVisible.getY()-1));
					movi=true;
				}
				break;
			case KeyEvent.VK_DOWN:
				if((regionVisible.getY()+regionVisible.getHeight())< regionTotal.getY()+10){
				regionVisible.setLocation(new Point((int)regionVisible.getX()+1,(int)regionVisible.getY()+1));
					movi=true;
				}
				break;
			}
			return movi;

		}

	//Verifica si es valido mover a una entidad en la direccion especificada por keyCode
	public boolean movimientoValido(int keyCode, Point mov) {

		boolean movi=false;

		switch(keyCode){
		case KeyEvent.VK_LEFT:
			if(mov.getX() > 0)
				movi =  true;
			break;
		case KeyEvent.VK_RIGHT:
			if(mov.getX()< regionTotal.getX())
					movi=true;
			break;
		case KeyEvent.VK_UP:
			if(mov.getY() > 0)
				movi=true;
			break;
		case KeyEvent.VK_DOWN:
			if(mov.getY()< regionTotal.getY())
				movi=true;
			break;
		}
		return movi;

	}

	}



