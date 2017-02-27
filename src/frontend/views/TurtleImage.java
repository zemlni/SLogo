package frontend.views;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TurtleImage {
	public static final int TURTLE_HEIGHT = 30;
	public static final int TURTLE_WIDTH = 30;
	private ImageView turtleImage;
	
	public TurtleImage(double startingX, double startingY){
		setInitialImage();
		move(startingX, startingY);
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

	public ImageView getImage() {
		return turtleImage;
	}
	
	public void move(double x, double y){
		turtleImage.setX(x - TURTLE_WIDTH/2);
		turtleImage.setY(y - TURTLE_HEIGHT/2);
	}
	
	
}
