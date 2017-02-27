package frontend.views;

import frontend.app.FrontEndController;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;



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
		canvas = new Canvas(500, 500);
		gc = canvas.getGraphicsContext2D();
		turtleScreen.getChildren().add(canvas);	
		turtleScreen.getChildren().add(turtle.getImage());
	}
	
	public void setFrontEndController(FrontEndController frontEnd) {
		this.frontEnd = frontEnd;
	}
	
	public void moveTurtleTo(double x, double y) {
		Point2D location = translateLocation(x,y);
		turtle.move(location.getX(), location.getY());
	}

	public void drawLine(double x0, double y0, double x1, double y1) {
		Point2D old = translateLocation(x0, y0);
		Point2D end = translateLocation(x1, y1);
		gc.strokeLine(old.getX(), old.getY(), end.getX(), end.getY());
	}

	public void setTurtleAngle(double angle) {
		turtle.setAngle(angle);
	}

	public void clearScreen() {
		gc.clearRect(0, 0, 200, 200);
	}
	
	private Point2D translateLocation(double x, double y){
		double newX = X_OFFSET + x;
		double newY = Y_OFFSET - y;
		return new Point2D(newX, newY);
	}
	
}
