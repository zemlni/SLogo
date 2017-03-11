package frontend.turtlescreen;

import frontend.frontend.FrontEndController;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;

public class TurtleImage {
	public static final int TURTLE_HEIGHT = 30;
	public static final int TURTLE_WIDTH = 30;
	private ImageView turtleImage;
	private Circle currentCircle;
	private boolean commandable = true;
	private boolean currentOn = true;
	public static final int CURRENT_CIRCLE_RADIUS = 4;
	private FrontEndController frontEnd;
	private Transformer locTransformer;
	private int id;
	public static final String INITIAL_TURTLE_IMAGE = "turtle.png";
	
	public TurtleImage(int idNumber, Transformer locationTransformer, double startingX, double startingY, FrontEndController frontEnd){
		setInitialImage();
		turtleImage.setFitWidth(TURTLE_WIDTH);
		turtleImage.setFitHeight(TURTLE_HEIGHT);
		currentCircle = new Circle(4);
		locTransformer = locationTransformer;
		moveTo(startingX, startingY);
		this.frontEnd = frontEnd;
		id = idNumber;	
		turtleImage.setOnMouseClicked(e -> {toggleCommandable();});
	}
	
	public TurtleImage(int idNumber, Transformer locationTransformer, FrontEndController frontEnd){
		this(idNumber, locationTransformer, 0.0, 0.0, frontEnd);
	}
	
	
	private void setInitialImage(){
		//Images gotten from open clipart site: https://openclipart.org/detail/1024/turtle
		Image turtle = new Image(getClass().getClassLoader().getResourceAsStream(INITIAL_TURTLE_IMAGE));
		turtleImage = new ImageView(turtle);
	}
	
	public void setImage(Image newImage){
		turtleImage.setImage(newImage);
	}
	
	public void setAngle(double angle){
		turtleImage.setRotate(angle);
	}

	public ImageView getImage() {
		return turtleImage;
	}
	
	public void moveTo(double x, double y){
		
		Point2D location = locTransformer.translateLoc(x, y);
		location = locTransformer.getTurtleLoc(location);
		turtleImage.setX(location.getX() - TURTLE_WIDTH/2);
		turtleImage.setY(location.getY() - TURTLE_HEIGHT/2);
		currentCircle.setCenterX(location.getX());
		currentCircle.setCenterY(location.getY());
	
	}
	
	public void show(){
		turtleImage.setVisible(true);
		currentCircle.setVisible(commandable && currentOn);
	}
	
	public void hide(){
		turtleImage.setVisible(false);
		turtleImage.setVisible(false);
	}

	public Circle getCircle() {
		return currentCircle;
	}

	private void toggleCommandable(){
		commandable = !commandable;
		updateCircle();
		frontEnd.toggleTurtle(id);
	}
	
	public void updateCommandable(){
		commandable = true;
		updateCircle();
	}
	
	public void updateShowCommandable(boolean show) {
		currentOn = show;
		updateCircle();
	}
	
	private void updateCircle(){
		currentCircle.setVisible(commandable && currentOn);
	}
	
}
