package cliente.mundo.ui;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

@SuppressWarnings("serial")
public class UIComponent extends JPanel {

	private Timer timer;
	public final static int ANCHO_UI_COMPONENT = 600;
	public final static int ALTO_UI_COMPONENT = 600;

    public UIComponent() {
    	 timer = new Timer();
    	 timer.schedule(new RenderTask(this),0, 100);//10fps
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(ANCHO_UI_COMPONENT, ALTO_UI_COMPONENT);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
          UIService.getInstance().run(g);
    }


}

class RenderTask extends TimerTask {
	JPanel panelARefrescar;
	RenderTask(JPanel panel){
		this.panelARefrescar = panel;
	}
    public void run() {
    	this.panelARefrescar.repaint();

    }
  }