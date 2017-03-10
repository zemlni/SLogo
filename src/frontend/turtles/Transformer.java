package frontend.turtles;


import javafx.scene.canvas.GraphicsContext;

/**
 * This class is designed to be useful in translating points from an unbounded grid that starts
 * at 0,0 in the top left corner to a bounded grid that starts at 0,0 in the direct center of
 * the bounded grid
 * @author Matthew Tribby
 */
public abstract class Transformer {
	private double xOff;
	private double yOff;
	private double xBounds;
	private double yBounds;
	
	public Transformer(double xOffset, double yOffset){
		xOff = xOffset;
		yOff = yOffset;
		xBounds = 2*xOff;
		yBounds = 2*yOff;
	}
	
	public Point translateLoc(double x, double y){
		return new Point(xOff + x, yOff - y);
	}

	public abstract Point getTurtleLoc(Point location);
	
	public abstract void drawLines(Point start, Point end, GraphicsContext gc);
	
	public double getSlope(Point first, Point second){
		return (second.getY()-first.getY())/(second.getX()-first.getX());
	}
	
	public void setXBound(double x){
		xBounds = x;
	}
	
	public void setYBound(double y){
		yBounds = y;
	}
	
	public double getXBounds(){
		return xBounds;
	}
	
	public double getYBounds(){
		return yBounds;
	}
	
	
}
