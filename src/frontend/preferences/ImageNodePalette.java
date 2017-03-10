package frontend.preferences;

import java.io.File;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageNodePalette extends ImageView{

	public static final double IMAGE_SIDE_LENGTH = 30;
	
	public ImageNodePalette(File imageFile){
		Image image = new Image(imageFile.toURI().toString());
		setImage(image);
		this.setFitWidth(IMAGE_SIDE_LENGTH);
		this.setFitHeight(IMAGE_SIDE_LENGTH);
	}
}
