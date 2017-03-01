package frontend.views;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import frontend.app.FrontEndController;
import frontend.turtles.ColorSelector;
import frontend.turtles.ImageSelector;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class TurtleScreenController {
	private Canvas canvas;
	private GraphicsContext gc;
	private List<TurtleController> turtleControls; 
	@FXML
	private Pane turtlePane;
	@FXML
	private HBox prefButtons;
	public static final int X_OFFSET = 200;
	public static final int Y_OFFSET = 140;
	public static final int CANVAS_WIDTH = 400;
	public static final int CANVAS_HEIGHT = 400;
	private FrontEndController frontEnd;
	
	@FXML
	private void initialize() {
		turtleControls = new ArrayList<TurtleController>();
		turtleControls.add(new TurtleController(X_OFFSET, Y_OFFSET));
		canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
		gc = canvas.getGraphicsContext2D();
		turtlePane.getChildren().add(canvas);	
		turtlePane.getChildren().add(turtleControls.get(0).getImage());
		createPreferencePanel();
	}
	
	public void setFrontEndController(FrontEndController frontEndController){
		frontEnd =frontEndController;
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
	
	public void setBackground(Color color){
		turtlePane.setStyle("-fx-background-color: #" + color.toString().substring(2));
	}
	
	private Point2D translateLocation(double x, double y){
		double newX = X_OFFSET + x;
		double newY = Y_OFFSET - y;
		return new Point2D(newX, newY);
	}
	
	private void createPreferencePanel(){
		HBox pref = new HBox();
		ColorSelector penColor = new ColorSelector("Pen Color: ");
		penColor.getColorPicker().setOnAction(e -> setPenColor(penColor.getColorPicker().getValue()));
		
		ColorSelector backColor = new ColorSelector("Background: ");
		backColor.getColorPicker().setOnAction(e -> setBackground(backColor.getColorPicker().getValue()));
		
		Button imageSelect = new Button("Image Select ");
		imageSelect.setOnAction(e -> changeTurtleImage());
		
		pref.getChildren().addAll(penColor.getPanel(),backColor.getPanel(), imageSelect);
		turtlePane.getChildren().add(pref);
	}
	
	private void changeTurtleImage(){
		ImageSelector imageSelector = new ImageSelector("Turtle Image");
		imageSelector.setInitialDirectory("images");
			File imageFile = imageSelector.getFile();
		if(imageFile != null){
			//http://stackoverflow.com/questions/7830951/how-can-i-load-computer-directory-images-in-javafx
			Image turtleImage = new Image(imageFile.toURI().toString());
			turtleControls.get(0).setTurtleImage(turtleImage);
		}
	}

	public void moveTurtleTo(double x, double y) {
		turtleControls.get(0).moveTurtleTo(x, y);
	}

	public void setTurtleAngle(double angle) {
		turtleControls.get(0).setTurtleAngle(angle);
	}
	
	public void showTurtle(){
		turtleControls.get(0).showTurtle();
	}
	
	public void hideTurtle(){
		turtleControls.get(0).hideTurtle();
	}
}
