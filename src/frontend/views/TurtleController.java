package frontend.views;

import frontend.app.FrontEndController;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;



/**
 * This class is used to visually show the turtle's/pen's movement and drawing
 * in the drawing window.
 * @author Matthew Tribby
 */
public class TurtleController {

	private Canvas canvas;
	private GraphicsContext gc;
	private TurtleImage turtle; 
	@FXML
	private Pane turtleScreen;
	private FrontEndController frontEnd;
	public static final int X_OFFSET = 170;
	public static final int Y_OFFSET = 125;
	
	
	public TurtleController(){

	}
	
	@FXML
	private void initialize() {
		turtle = new TurtleImage(X_OFFSET, Y_OFFSET);
		canvas = new Canvas();
		gc = canvas.getGraphicsContext2D();
		turtleScreen.getChildren().add(canvas);	
		turtleScreen.getChildren().add(turtle.getImage());
	}
	
	public void setFrontEndController(FrontEndController frontEnd) {
		this.frontEnd = frontEnd;
	}
	
	public void moveTurtleTo(double x, double y) {
		turtle.move(x + X_OFFSET, y + Y_OFFSET);
	}

	public void drawLine(double x0, double y0, double x1, double y1) {
		gc.strokeLine(x0 + X_OFFSET, y0 + Y_OFFSET, x1 + X_OFFSET, y1 + Y_OFFSET);
	}

	public void setTurtleAngle(double angle) {
		turtle.setAngle(angle);
	}

	public void clearScreen() {
		gc.clearRect(0, 0, 200, 200);
	}
	
	
}
