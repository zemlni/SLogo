package frontend.views;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import frontend.app.FrontEndController;
import frontend.nonfxml.IViewController;
import frontend.nonfxml.view.TurtleScreenView;
import frontend.preferences.ImageSelector;
import frontend.preferences.Palette;
import frontend.preferences.PreferencesWindow;
import frontend.turtles.InfiniteTransformer;
import frontend.turtles.Transformer;
import frontend.turtles.TurtleImage;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import language.Language;
import utils.javafx.FX;

	//For this class, need to establish handling multiple turtles with IDs
public class TurtleScreenController implements IViewController {
	private Canvas canvas;
	private GraphicsContext gc;
	private Map<Integer, TurtleImage> turtles; 
	private Pane turtlePane;
	public static final int INITIAL_X_OFFSET = 198;
	public static final int INITIAL_Y_OFFSET = 143;
	public static final int CANVAS_WIDTH = 400;
	public static final int CANVAS_HEIGHT = 400;
	private FrontEndController frontEnd;
	private DisplayController displayController;
	private Transformer locTransformer;
	
	public TurtleScreenController(TurtleScreenView view) {
		turtlePane = view.getTurtlePane();
		locTransformer = new InfiniteTransformer(INITIAL_X_OFFSET, INITIAL_Y_OFFSET);

		turtles = new HashMap<Integer, TurtleImage>();
		canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
		gc = canvas.getGraphicsContext2D();
		turtlePane.getChildren().add(canvas);
		
		turtlePane.widthProperty().addListener(e -> {locTransformer.setXBound(turtlePane.getWidth());});
		turtlePane.heightProperty().addListener(e -> {locTransformer.setYBound(turtlePane.getHeight());});
		
		createPreferencePanel();
	}
	
	public void setFrontEndController(FrontEndController frontEndController){
		frontEnd =frontEndController;
	}
	
	public void setDisplayController(DisplayController displayController) {
		this.displayController = displayController;
	}
	
	public void addTurtle(int idNumber){
		TurtleImage newTurtle = new TurtleImage(idNumber, locTransformer, frontEnd);
		turtles.put(idNumber, newTurtle);
		turtlePane.getChildren().add(newTurtle.getImage());
		turtlePane.getChildren().add(newTurtle.getCircle());
	}
	
	public void drawLine(double x0, double y0, double x1, double y1) {
		Point2D original = locTransformer.translateLoc(x0, y0);
		Point2D end = locTransformer.translateLoc(x1, y1);
		locTransformer.drawLines(original, end, gc);
	}
	
	public void clearScreen() {
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
	}
	
	public void setPenColor(Color penColor){
		gc.setStroke(penColor);
	}
	
	public Paint getPenColor(){
		return gc.getStroke();
	}
	
	public void setPenThickness(double newWidth){
		gc.setLineWidth(newWidth);
	}
	
	public double getPenWidth() {
		return gc.getLineWidth();
	}
	
	public void setBackground(Color color){
		turtlePane.setStyle("-fx-background-color: #" + color.toString().substring(2));
	}
	
	private void createPreferencePanel(){
		HBox buttonPanel = new HBox();
		Button preferences = new Button();
		preferences.textProperty().bind(Language.createStringBinding("PreferencesTitle"));
		preferences.setOnAction(e -> new PreferencesWindow(this));
		
		Button colorPal = new Button();
		colorPal.textProperty().bind(Language.createStringBinding("ColorPal"));
		colorPal.setOnAction(e -> displayController.showColorPalette());
		buttonPanel.getChildren().addAll(preferences, colorPal);
		turtlePane.getChildren().add(buttonPanel);
	}
	
	public void changeTurtleImage(){
		
		ImageSelector imageSelector = new ImageSelector("Turtle Image");
		
		imageSelector.setInitialDirectory("images");
		File imageFile = imageSelector.getFile();
		setTurtleImage(imageFile);
	}
	
	public void setTurtleImage(File imageFile){
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
	
	public void allPensUp(){
		frontEnd.allPensUp();
	}
	
	public void allPensDown(){
		frontEnd.allPensDown();
	}

	public void updateCommandable(List<Integer> turtleIds) {
		for(Integer id : turtleIds){
			turtles.get(id).updateCommandable();
		}
	}
	
	public void updateShowCommandable(boolean show){
		for(TurtleImage turtle : turtles.values()){
			turtle.updateShowCommandable(show);
		}
	}

	
}