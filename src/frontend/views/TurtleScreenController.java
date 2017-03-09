package frontend.views;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import frontend.app.FrontEndController;
import frontend.nonfxml.view.IViewController;
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
	private Canvas canvas;
	private GraphicsContext gc;
	private Map<Integer, TurtleImage> turtles; 
	private Pane turtlePane;
	public static final int X_OFFSET = 198;
	public static final int Y_OFFSET = 143;
	public static final int CANVAS_WIDTH = 4000;
	public static final int CANVAS_HEIGHT = 4000;
	private FrontEndController frontEnd;
	private Transformer locTransformer;
	
	

	public TurtleScreenController(TurtleScreenView view) {
		turtlePane = view.getTurtlePane();
		locTransformer = new InfiniteTransformer(X_OFFSET, Y_OFFSET);
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
