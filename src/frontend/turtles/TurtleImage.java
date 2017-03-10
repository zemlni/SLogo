package frontend.turtles;

import frontend.app.FrontEndController;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;

public class TurtleImage {
	public static final int TURTLE_HEIGHT = 30;
	public static final int TURTLE_WIDTH = 30;
	private ImageView turtleImage;
	private Circle currentCircle;
	private boolean current = true;
	private boolean currentOn = true;
	public static final int CURRENT_CIRCLE_RADIUS = 4;
	private FrontEndController frontEnd;
	private Transformer locTransformer;
	private int id;
	
	public TurtleImage(int idNumber, Transformer locationTransformer, double startingX, double startingY, FrontEndController frontEnd){
		setInitialImage();
		turtleImage.setFitWidth(TURTLE_WIDTH);
		turtleImage.setFitHeight(TURTLE_HEIGHT);
		currentCircle = new Circle(4);
		locTransformer = locationTransformer;
		moveTo(startingX, startingY);
		this.frontEnd = frontEnd;
		id = idNumber;	
		turtleImage.setOnMouseClicked(e -> {switchState();});
	}
	
	public TurtleImage(int idNumber, Transformer locationTransformer, FrontEndController frontEnd){
		this(idNumber, locationTransformer, 0.0, 0.0, frontEnd);
	}
	
	private void switchState(){
		current = !current;
		currentCircle.setVisible(current && currentOn);
		frontEnd.toggleTurtle(id);
	}
	
	private void setInitialImage(){
		//Images gotten from open clipart site: https://openclipart.org/detail/1024/turtle
		Image turtle = new Image(getClass().getClassLoader().getResourceAsStream("turtle.png"));
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
		
		Point location = locTransformer.translateLoc(x, y);
		location = locTransformer.getTurtleLoc(location);
		turtleImage.setX(location.getX() - TURTLE_WIDTH/2);
		turtleImage.setY(location.getY() - TURTLE_HEIGHT/2);
		currentCircle.setCenterX(location.getX());
		currentCircle.setCenterY(location.getY());
	
	}
	
	public void show(){
		turtleImage.setVisible(true);
		currentCircle.setVisible(current && currentOn);
	}
	
	public void hide(){
		turtleImage.setVisible(false);
		turtleImage.setVisible(false);
	}

	public Circle getCircle() {
		return currentCircle;
	}

	public void updateCurrent() {
		currentOn = !currentOn;
		currentCircle.setVisible(current && currentOn);
	}
	
}
