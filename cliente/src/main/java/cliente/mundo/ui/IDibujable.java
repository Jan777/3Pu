package cliente.mundo.ui;

import java.awt.*;

public interface IDibujable {
	void paintComponent(Graphics g, Rectangle region);
	Point getPos();
	void setPos(Point pt);
}
