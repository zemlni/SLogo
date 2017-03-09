package frontend.views;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import frontend.app.FrontEndController;
import frontend.turtles.ColorSelector;
import frontend.turtles.ImageSelector;
import frontend.turtles.LocationTransformer;
import frontend.turtles.Point;
import frontend.turtles.TurtleImage;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

	//For this class, need to establish handling multiple turtles with IDs
public class TurtleScreenController {
	private Canvas canvas;
	private GraphicsContext gc;
	private Map<Integer, TurtleImage> turtles; 
	@FXML
	private Pane turtlePane;
	public static final int X_OFFSET = 198;
	public static final int Y_OFFSET = 143;
	public static final int CANVAS_WIDTH = 4000;
	public static final int CANVAS_HEIGHT = 4000;
	private FrontEndController frontEnd;
	private LocationTransformer locTransformer;
	//private double xBounds = 2*X_OFFSET;
	//private double yBounds = 2*Y_OFFSET;
	
	
	@FXML
	private void initialize() {
		locTransformer = new LocationTransformer(X_OFFSET, Y_OFFSET);
		turtles = new HashMap<Integer, TurtleImage>();
		addTurtle(1);
		canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
		gc = canvas.getGraphicsContext2D();
		turtlePane.getChildren().add(canvas);	
		//turtlePane.widthProperty().addListener(e -> {xBounds = turtlePane.getWidth();});
		//turtlePane.heightProperty().addListener(e -> {yBounds = turtlePane.getHeight();});
		
		createPreferencePanel();
	}
	
	public void setFrontEndController(FrontEndController frontEndController){
		frontEnd =frontEndController;
	}
	
	public void addTurtle(int idNumber){
		TurtleImage newTurtle = new TurtleImage(idNumber, locTransformer, frontEnd);
		turtles.put(idNumber, newTurtle);
		turtlePane.getChildren().add(newTurtle.getImage());
		turtlePane.getChildren().add(newTurtle.getCircle());
	}
	
	public void drawLine(double x0, double y0, double x1, double y1) {
		Point original = locTransformer.translateLoc(x0, y0);
		Point end = locTransformer.translateLoc(x1, y1);
		locTransformer.drawLines(original, end, gc);
	}
	
	public void clearScreen() {
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
	}
	
	public void setPenColor(Color penColor){
		gc.setStroke(penColor);
	}
	
	public void changePenThickness(double newWidth){
		gc.setLineWidth(newWidth);
	}
	
	public void setBackground(Color color){
		turtlePane.setStyle("-fx-background-color: #" + color.toString().substring(2));
	}
	
	private void createPreferencePanel(){
		//TO DO: Need to abstract this out to the FXML
		HBox pref = new HBox();
		ColorSelector penColor = new ColorSelector("Pen Color");
		penColor.getColorPicker().setOnAction(e -> setPenColor(penColor.getColorPicker().getValue()));
		
		ColorSelector backColor = new ColorSelector("Background");
		backColor.getColorPicker().setOnAction(e -> setBackground(backColor.getColorPicker().getValue()));
		
		Button imageSelect = new Button("Image Select");
		imageSelect.setOnAction(e -> changeTurtleImage());
		
		Button currentOnToggle = new Button("Show Currents");
		currentOnToggle.setOnAction(e -> updateTurtles());
		
		pref.getChildren().addAll(penColor, backColor, imageSelect, currentOnToggle);
		turtlePane.getChildren().add(pref);
	}
	
	private void updateTurtles(){
		for(TurtleImage turtle : turtles.values()){
			turtle.updateCurrent();
		}
	}
	
	private void changeTurtleImage(){
		
		//Iterate through Map
		
		ImageSelector imageSelector = new ImageSelector("Turtle Image");
		imageSelector.setInitialDirectory("images");
			File imageFile = imageSelector.getFile();
		if(imageFile != null){
			//http://stackoverflow.com/questions/7830951/how-can-i-load-computer-directory-images-in-javafx
			Image turtleImage = new Image(imageFile.toURI().toString());
			turtles.get(1).setImage(turtleImage);
		}
	}

	public void moveTurtleTo(int id, double x, double y) {
		turtles.get(id).moveTo(x, y);
	}

	public void setTurtleAngle(int id, double angle) {
		turtles.get(id).setAngle(angle);
	}
	
	public void showTurtle(int id){
		turtles.get(id).show();
	}
	
	public void hideTurtle(int id){
		turtles.get(id).hide();
	}
}
