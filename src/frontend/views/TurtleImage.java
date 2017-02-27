package frontend.views;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TurtleImage {
	public static final int TURTLE_HEIGHT = 30;
	public static final int TURTLE_WIDTH = 30;
	private ImageView turtleImage;
	private double x;
	private double y;
	
	public TurtleImage(double startingX, double startingY){
		setInitialImage();
		x = startingX;
		y = startingY;
		turtleImage.setX(x);
		turtleImage.setY(y);
		turtleImage.setFitWidth(TURTLE_WIDTH);
		turtleImage.setFitHeight(TURTLE_HEIGHT);
	}
	
	public TurtleImage(){
		this(0.0, 0.0);
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
		turtleImage.setRotate(-(turtleImage.getRotate()) + angle);
	}
	
	public void move(double x, double y){
		turtleImage.setX(x);
		turtleImage.setY(y);
	}

	public ImageView getImage() {
		return turtleImage;
	}
	
	
}
