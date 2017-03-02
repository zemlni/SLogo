package frontend.turtles;

import java.util.List;

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
	private Point turtleLoc;
	
	public LocationTransformer(double xOffset, double yOffset){
		xOff = xOffset;
		yOff = yOffset;
		xBounds = 2*xOff;
		yBounds = 2*yOff;
	}
	
	public Point translateLoc(double x, double y){
		return new Point(xOff + x, yOff - y);
	}

	public void findTurtleLoc(Point location) {
		//Commenting out but this works properly!
		/*
		int locX = Math.floorMod((int) location.getX(),(int) xBounds);
		int locY = Math.floorMod((int) location.getY(), (int) yBounds);
		*/
		//turtleLoc = new Point(locX, locY);
		turtleLoc = location;
	}
	
	public void drawLines(Point start, Point end, GraphicsContext gc){
		//System.out.println(start);
		//System.out.println(end);
		double slope = getSlope(start, end);
		/*Point currentEnd = null;
		Point newStart = null;
		Point newEnd = end;
		
		if(end.getX() < 0){
			newStart = new Point(xBounds, end.getY()+slope*end.getX());
			newEnd = new Point(xBounds+end.getX(), end.getY());
			currentEnd = new Point(0, end.getY() + slope*Math.abs(end.getX()));
		
			System.out.println("LOW X " + end.getX());
		}
		else if(end.getX() > xBounds){
			newStart = new Point(0, end.getY()+(xBounds-end.getX())*slope);
			newEnd = new Point(end.getX()-xBounds, end.getY());
			currentEnd = new Point(xBounds, end.getY()+(xBounds-end.getX())*slope);
		
			System.out.println("HIGH X");
		}
		if(end.getY() < 0){
			newStart = new Point(end.getX()-(1/(slope*(1/Math.abs(end.getY())))), yBounds);
			newEnd = new Point(end.getX(), yBounds + end.getY());
			currentEnd = new Point(end.getX()-(1/(slope*(1/Math.abs(end.getY())))), 0);
			
			System.out.println("LOW Y " + end.getY());
		}
		else if(end.getY() > yBounds){
			
			newStart = new Point(end.getX() + (1/(slope*(1/Math.abs(end.getY())))) , 0);
			newEnd = new Point(end.getX(), end.getY()-yBounds);
			currentEnd = new Point(end.getX() + (1/(slope*(1/Math.abs(end.getY())))), yBounds);
			
			System.out.println("HIGH Y");
		}
		if(end.getX() < 0 && end.getY() < 0){
			newStart = new Point()
			newEnd = new Point(end.getX() + xBounds, end.getY() + yBounds);
		}
		else if(end.getX() < 0 && end.getY() > yBounds){
			
		}
		else if(end.getX() > xBounds && end.getY() < 0){
			
		}
		else if(end.getX() > xBounds && end.getY() > yBounds){
			
		}
		
		if(newStart == null){
		*/
		
		 gc.strokeLine(start.getX(), start.getY(), end.getX(), end.getY());
			
		/*
		}
		else{
			gc.strokeLine(start.getX(), start.getY(), currentEnd.getX(), currentEnd.getY());
			drawLines(newStart, newEnd, gc);
		}
		*/
		
	}
	
	private double getSlope(Point first, Point second){
		return (second.getY()-first.getY())/(second.getX()-first.getX());
	}
	
	public void setBounds(double xBoundary, double yBoundary){
		xBounds = xBoundary;
		yBounds = yBoundary;
	}
	
	public Point getTurtleLoc(){
		return turtleLoc;
	}
	
}
