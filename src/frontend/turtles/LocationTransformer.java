package frontend.views;

import java.util.List;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

/**
 * This class is designed to be useful in translating points from an unbounded grid that starts
 * at 0,0 in the top left corner to a bounded grid that starts at 0,0 in the direct center of
 * the bounded grid
 * @author Matthew Tribby
 */
public class LocationTransformer {
	private double xOff;
	private double yOff;
	private double xBounds;
	private double yBounds;
	private Point2D turtleLoc;
	
	public LocationTransformer(double xOffset, double yOffset){
		xOff = xOffset;
		yOff = yOffset;
		xBounds = 2*xOff;
		yBounds = 2*yOff;
	}
	
	public Point2D translateLoc(double x, double y){
		return new Point2D(xOff + x, yOff - y);
	}

	public void findTurtleLoc(Point2D location) {
		int locX = Math.floorMod((int) location.getX(),(int) xBounds);
		int locY = Math.floorMod((int) location.getY(), (int) yBounds);
		turtleLoc = new Point2D(locX, locY);
	}
	
	public void drawLines(Point2D start, Point2D end, GraphicsContext gc){
		double slope = getSlope(start, end);
		Point2D currentEnd = null;
		Point2D newStart = null;
		Point2D newEnd = null;
		
		if(end.getX() < 0){
			newStart = new Point2D(xBounds, end.getY()+slope*end.getX());
			newEnd = new Point2D(xBounds+end.getX(), end.getY());
			currentEnd = new Point2D(0, end.getY() + slope*Math.abs(end.getX()));
		}
		else if(end.getX() > xBounds){
			newStart = new Point2D(0, end.getY()+(xBounds-end.getX())*slope);
			newEnd = new Point2D(end.getX()-xBounds, end.getY());
			currentEnd = new Point2D(xBounds, end.getY()+(xBounds-end.getX())*slope);
		}
		if(end.getY() < 0){
			newStart = new Point2D(end.getX()-(1/(slope*(1/Math.abs(end.getY())))), yBounds);
			newEnd = new Point2D(end.getX(), yBounds + end.getY());
			currentEnd = new Point2D(end.getX()-(1/(slope*(1/Math.abs(end.getY())))), 0);
		}
		else if(end.getY() > yBounds){
			newStart = new Point2D(end.getX() + (1/(slope*(1/Math.abs(end.getY())))) , 0);
			newEnd = new Point2D(end.getX(), end.getY()-yBounds);
			currentEnd = new Point2D(end.getX() + (1/(slope*(1/Math.abs(end.getY())))), yBounds);
		}
		
		if(newStart == null){
			gc.strokeLine(start.getX(), start.getY(), end.getX(), end.getY());
		}
		else{
			gc.strokeLine(start.getX(), start.getY(), currentEnd.getX(), currentEnd.getY());
			drawLines(newStart, newEnd, gc);
		}
	}
	
	private double getSlope(Point2D first, Point2D second){
		return (second.getY()-first.getY())/(second.getX()-first.getX());
	}
	
	public void setBounds(double xBoundary, double yBoundary){
		xBounds = xBoundary;
		yBounds = yBoundary;
	}
	
	public Point2D getTurtleLoc(){
		return turtleLoc;
	}
	
}
