package cliente.mundo.ui.math;

import java.awt.*;
import java.util.LinkedList;


public class Bresenham {

public static LinkedList<Point> linea(Point p1, Point p2){
	LinkedList<Point>puntos = new LinkedList<Point>();
    int dx = (p2.x - p1.x);
    int dy = (p2.y - p1.y);

    int stepy, stepx,x,y;
    //Determino punto para comenzar y terminar
    if (dy < 0){
        dy = -dy;
        stepy = -1;
    }
    else{
        stepy = 1;
    }
    if (dx < 0){
        dx = -dx;
        stepx = -1;
    }
    else{
        stepx = 1;
    }
    x = p1.x;
    y = p1.y;

    int p;
   //Itera hasta llegar al otro extremo de la linea
    if (dx > dy){
        p = 2*dy-dx;
        while (x != p2.getX()){
            x += stepx;
            if (p < 0)
                p += 2*dy;
            else{
                y += stepy;
                p += 2*(dy-dx);
            }

            puntos.add(new Point(x, y));
        }
    }
    else{
        p = 2*dx-dy;
        while (y != p2.y)
        {
            y = y + stepy;
            if (p < 0)
                p += 2*dx;
            else{
                x += stepx;
                p += 2*(dx-dy);
            }
            puntos.add(new Point(x, y));
        }
    }
    return puntos;

}
}