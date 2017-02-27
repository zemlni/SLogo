package frontend.views;

import frontend.app.FrontEndController;
import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;



/**
 * This class is used to visually show the turtle's/pen's movement and drawing
 * in the drawing window.
 * @author Matthew Tribby
 */
public class TurtleController {
	private FrontEndController frontEnd;
	private TurtleImage turtle;
	private double xOff;
	private double yOff;
	
	
	public TurtleController(double xOffset, double yOffset){
		turtle = new TurtleImage(xOffset, yOffset);
		xOff = xOffset;
		yOff = yOffset;
	}
	
	
	public void moveTurtleTo(double x, double y) {
		Point2D location = translateLocation(x,y);
		turtle.move(location.getX(), location.getY());
	}

	public void setTurtleAngle(double angle) {
		turtle.setAngle(angle);
	}

	
	private Point2D translateLocation(double x, double y){
		double newX = xOff + x;
		double newY = yOff - y;
		return new Point2D(newX, newY);
	}
	
	public ImageView getImage(){
		return turtle.getImage();
	}


	public void setFrontEndController(FrontEndController frontEndController) {
		frontEnd = frontEndController;
	}
	
}
