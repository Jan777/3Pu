package cliente.mundo.ui;

import cliente.mundo.partida.CoordinadorEventos;
import cliente.mundo.ui.entidades.TextInfo;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


///Cambio de nombre de telcado por ControladorTeclado
public class ControladorTeclado implements KeyListener {

	private CoordinadorEventos coordEventos;

	 //Singleton
	 private static ControladorTeclado instance = null;

	   public static ControladorTeclado getInstance() {
		      if(instance == null) {
		         instance = new ControladorTeclado();
		      }
		      return instance;
		   }


	private final int numerosTeclas = 120;
	private final boolean[] teclas = new boolean[numerosTeclas];

	public boolean arriba;
	public boolean abajo;
	public boolean derecha;
	public boolean izquierda;

	public boolean correr;
	public boolean salir;

	 public ControladorTeclado(){

	 }

	public void actualizar(){
		arriba = teclas[KeyEvent.VK_W];
		abajo = teclas[KeyEvent.VK_S];
		derecha = teclas[KeyEvent.VK_D];
		izquierda = teclas[KeyEvent.VK_A];
		correr = teclas[KeyEvent.VK_SHIFT];
		salir = teclas[KeyEvent.VK_ESCAPE];
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// CUANDO MANTENGO PRESIONADO UNA TECLA
		teclas[e.getKeyCode()] = true;

	  	if(e.getKeyCode() == KeyEvent.VK_Z){
    		Rectangle rc = UIService.getInstance().getRegionVisible();
    		rc.setBounds((int)rc.getX(),(int)rc.getY(),(int)rc.getWidth()+2,(int)rc.getHeight()+2);
    		UIService.getInstance().setRegionVisible(rc);
    	}
    	if(e.getKeyCode() == KeyEvent.VK_X){
    		Rectangle rc = UIService.getInstance().getRegionVisible();
    		rc.setBounds((int)rc.getX(),(int)rc.getY(),(int)rc.getWidth()-2,(int)rc.getHeight()-2);
    		UIService.getInstance().setRegionVisible(rc);
    	}

    	if(e.getKeyCode() == KeyEvent.VK_SPACE){
    		TextInfo.getInstance().Log("Iniciando batalla [...]");
			coordEventos.iniciarBatalla();
    	}


       //Se env�a a que procese la tecla el servicio de pantalla
       UIService.getInstance().procesarTecla(e.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// CUANDO SUELTO UNA TECLA
		teclas[e.getKeyCode()] = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	public void bindCoordinador(CoordinadorEventos coordEventos) {
		this.coordEventos = coordEventos;

	}

}
