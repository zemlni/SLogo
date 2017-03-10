package frontend.views;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import frontend.app.FrontEndController;
import frontend.nonfxml.IViewController;
import frontend.nonfxml.view.TurtleScreenView;
import frontend.turtles.ImageSelector;
import frontend.turtles.InfiniteTransformer;
import frontend.turtles.Point;
import frontend.turtles.PreferencesWindow;
import frontend.turtles.Transformer;
import frontend.turtles.TurtleImage;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

	//For this class, need to establish handling multiple turtles with IDs
public class TurtleScreenController implements IViewController {
	private static final double INIT_MOVING_SPEED = 1000;
	private static final double INIT_ROTATING_SPEED = 200;
	public static final int INITIAL_X_OFFSET = 198;
	public static final int INITIAL_Y_OFFSET = 143;
	public static final int CANVAS_WIDTH = 400;
	public static final int CANVAS_HEIGHT = 400;

	private double v = INIT_MOVING_SPEED;
	private double vAngle = INIT_ROTATING_SPEED;
	
	private Canvas canvas;
	private GraphicsContext gc;
	private Map<Integer, TurtleImage> turtles; 
	private Pane turtlePane;

	private FrontEndController frontEnd;
	private Transformer locTransformer;
	
	public TurtleScreenController(TurtleScreenView view) {
		turtlePane = view.getTurtlePane();
		locTransformer = new InfiniteTransformer(INITIAL_X_OFFSET, INITIAL_Y_OFFSET);

		turtles = new HashMap<Integer, TurtleImage>();
		canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
		gc = canvas.getGraphicsContext2D();
		turtlePane.getChildren().add(canvas);
		addTurtle(1);
		
		turtlePane.widthProperty().addListener(e -> {locTransformer.setXBound(turtlePane.getWidth());});
		turtlePane.heightProperty().addListener(e -> {locTransformer.setYBound(turtlePane.getHeight());});
		
		createPreferencePanel();
	}
	
	public void setFrontEndController(FrontEndController frontEndController){
		frontEnd =frontEndController;
	}
	
	// Turtle speed
	public double getTurtleMovingSpeed() {
		return v;
	}
	public double getTurtleRotatingSpeed() {
		return vAngle;
	}
	public void speedUp() {
		v = v * 1.5;
	}
	public void slowDown() {
		v = v * 2.0 / 3;
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
		Button preferences = new Button("Preferences");
		preferences.setOnAction(e -> new PreferencesWindow(this));
		turtlePane.getChildren().add(preferences);
	}
	
	public void updateTurtles(){
		for(TurtleImage turtle : turtles.values()){
			turtle.updateCurrent();
		}
	}
	
	public void changeTurtleImage(){
		
		ImageSelector imageSelector = new ImageSelector("Turtle Image");
		imageSelector.setInitialDirectory("images");
			File imageFile = imageSelector.getFile();
		if(imageFile != null){
			//http://stackoverflow.com/questions/7830951/how-can-i-load-computer-directory-images-in-javafx
			Image turtleImage = new Image(imageFile.toURI().toString());
			for(TurtleImage turtle : turtles.values()){
				turtle.setImage(turtleImage);
			}
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