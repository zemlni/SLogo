package frontend.preferences;

import java.io.File;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageNodePalette extends ImageView{

	public static final double IMAGE_SIDE_LENGTH = 30;
	
	public ImageNodePalette(File imageFile){
		Image image = new Image(imageFile.toURI().toString());
		setImage(image);
		this.setX(IMAGE_SIDE_LENGTH);
		this.setY(IMAGE_SIDE_LENGTH);
	}
}
