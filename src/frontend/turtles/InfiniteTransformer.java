package frontend.turtles;


import javafx.scene.canvas.GraphicsContext;

/**
 * This class is designed to be useful in translating points from an unbounded grid that starts
 * at 0,0 in the top left corner to a bounded grid that starts at 0,0 in the direct center of
 * the bounded grid
 * @author Matthew Tribby
 */
public class InfiniteTransformer extends Transformer{

	public InfiniteTransformer(double xOffset, double yOffset){
		super(xOffset, yOffset);
	}
	
	public Point getTurtleLoc(Point location) {
		return location;
	}
	
	public void drawLines(Point start, Point end, GraphicsContext gc){	
		 gc.strokeLine(start.getX(), start.getY(), end.getX(), end.getY());
	}	
}
