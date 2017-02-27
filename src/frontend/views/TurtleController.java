package frontend.views;

import frontend.app.FrontEndController;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;



/**
 * This class is used to visually show the turtle's/pen's movement and drawing
 * in the drawing window.
 * @author Matthew Tribby
 */
public class TurtleController {

	@FXML
	private HBox turtleScreen;
	
	private FrontEndController frontEnd;
	public void setFrontEndController(FrontEndController frontEnd) {
		this.frontEnd = frontEnd;
	}
	
	public void moveTurtleTo(double x, double y) {
		// TODO Auto-generated method stub
		
	}

	public void drawLine(double x0, double y0, double x1, double y1) {
		// TODO Auto-generated method stub
		
	}

	public void setTurtleAngle(double angle) {
		// TODO Auto-generated method stub
		
	}

	public void clearScreen() {
		// TODO Auto-generated method stub
		
	}
	
	
}
