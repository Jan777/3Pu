package cliente.mundo.ui.entidades;

import cliente.mundo.ui.IDibujable;

import java.awt.*;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 by
 */
public class ModoBatalla implements IDibujable{

    private String jugadorLocal;
    private String jugadorRemoto;
    private Boolean visible=false;

    @Override
    public void paintComponent(Graphics g, Rectangle region) {

        if(visible==false)
            return;
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(Color.RED);
        g2d.setFont(new Font("default", Font.BOLD, 34));
        g2d.drawString(this.getJugadorLocal()+" vs "+this.getJugadorRemoto(),40,40);
        g2d.dispose();
    }

    @Override
    public Point getPos() {
        return null;
    }

    @Override
    public void setPos(Point pt) {

    }

    public String getJugadorLocal() {
        return jugadorLocal;
    }

    public void setJugadorLocal(String jugadorLocal) {
        this.jugadorLocal = jugadorLocal;
    }

    public String getJugadorRemoto() {
        return jugadorRemoto;
    }

    public void setJugadorRemoto(String jugadorRemoto) {
        this.jugadorRemoto = jugadorRemoto;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }
}
