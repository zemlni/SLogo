package frontend.views;

import javafx.geometry.Point2D;

/**
 * This class is designed to be useful in translating points from an unbounded grid that starts
 * at 0,0 in the top left corner to a bounded grid that starts at 0,0 in the direct center of
 * the bounded grid
 * @author Matthew Tribby
 */
public class LocationTransformer {
	private double xOff;
	private double yOff;
	
	public LocationTransformer(double xOffset, double yOffset){
		xOff = xOffset;
		yOff = yOffset;
	}
	
	public Point2D translateLoc(double x, double y){
		return new Point2D(xOff + x, yOff - y);
	}
	
}
