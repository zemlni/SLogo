package frontend.views;

import java.util.ArrayList;
import java.util.List;

import frontend.app.FrontEndController;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class TurtleScreenController {
	private Canvas canvas;
	private GraphicsContext gc;
	private List<TurtleController> turtleControls; 
	@FXML
	private Pane turtlePane;
	private FrontEndController frontEnd;
	public static final int X_OFFSET = 170;
	public static final int Y_OFFSET = 125;
	
	@FXML
	private void initialize() {
		turtleControls = new ArrayList<TurtleController>();
		turtleControls.add(new TurtleController(X_OFFSET, Y_OFFSET));
		canvas = new Canvas(500, 500);
		gc = canvas.getGraphicsContext2D();
		turtlePane.getChildren().add(canvas);	
		turtlePane.getChildren().add(turtleControls.get(0).getImage());
	}
	
	public void setFrontEndController(FrontEndController frontEnd) {
		this.frontEnd = frontEnd;
	}
	
	public void drawLine(double x0, double y0, double x1, double y1) {
		Point2D old = translateLocation(x0, y0);
		Point2D end = translateLocation(x1, y1);
		gc.strokeLine(old.getX(), old.getY(), end.getX(), end.getY());
	}
	
	public void clearScreen() {
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
	}
	

	public void setPenColor(Color penColor){
		gc.setStroke(penColor);
	}
	
	public void setBackground(String color){
		turtlePane.setStyle("-fx-background-color: " + color);
	}
	
	private Point2D translateLocation(double x, double y){
		double newX = X_OFFSET + x;
		double newY = Y_OFFSET - y;
		return new Point2D(newX, newY);
	}

	public void moveTurtleTo(double x, double y) {
		turtleControls.get(0).moveTurtleTo(x, y);
	}

	public void setTurtleAngle(double angle) {
		turtleControls.get(0).setTurtleAngle(angle);
	}
}
