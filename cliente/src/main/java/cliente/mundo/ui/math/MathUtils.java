package cliente.mundo.ui.math;

import java.awt.*;

public class MathUtils {

	public static Point twoDToIso(Point pt ){
		  Point tempPt = new Point(0,0);
		  tempPt.x = pt.x - pt.y;
		  tempPt.y = (pt.x + pt.y) / 2;
		  return tempPt;
		}

	public static Point isoTo2D(Point pt){
		  Point tempPt = new Point(0, 0);
		  tempPt.x = (2 * pt.y + pt.x) / 2;
		  tempPt.y = (2 * pt.y - pt.x) / 2;
		  return(tempPt);
		}

}
