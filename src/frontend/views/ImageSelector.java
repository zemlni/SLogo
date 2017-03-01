package frontend.views;

import java.io.File;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class ImageSelector{
	FileChooser fileChooser;
	File imageFile;
	
	public ImageSelector(String title){
		//Code inspired from the link
		//https://docs.oracle.com/javase/8/javafx/api/javafx/stage/FileChooser.html
		fileChooser = new FileChooser();
		fileChooser.setTitle(title);
		fileChooser.getExtensionFilters().add(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
		imageFile = fileChooser.showOpenDialog(new Stage());
	}
	
	public void setInitialDirectory(String path){
		fileChooser.setInitialDirectory(new File(path));
	}
	
	public File getFile(){
		return imageFile;
	}
}
