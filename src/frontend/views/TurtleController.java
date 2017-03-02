package frontend.views;

import frontend.app.FrontEndController;
import frontend.turtles.TurtleImage;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
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
	private LocationTransformer locTransformer;
	
	
	public TurtleController(double xOffset, double yOffset, LocationTransformer locationTransformer){
		turtle = new TurtleImage(xOffset, yOffset);
		xOff = xOffset;
		yOff = yOffset;
		locTransformer = locationTransformer;
	}
	
	public TurtleController(LocationTransformer locationTransformer){
		this(0,0, locationTransformer);
	}
	
	public void moveTurtleTo(double x, double y) {
		Point2D location = locTransformer.translateLoc(x, y);
		locTransformer.findTurtleLoc(location);
		location = locTransformer.getTurtleLoc();
		turtle.move(location.getX(), location.getY());
	}

	public void setTurtleAngle(double angle) {
		turtle.setAngle(angle);
	}
	
	public ImageView getImage(){
		return turtle.getImage();
	}


	public void setFrontEndController(FrontEndController frontEndController) {
		frontEnd = frontEndController;
	}


	public void setTurtleImage(Image newImage) {
		turtle.setImage(newImage);
	}
	
	public void showTurtle(){
		turtle.showImage();
	}
	
	public void hideTurtle(){
		turtle.hideImage();
	}
	
}
