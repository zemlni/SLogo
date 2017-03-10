package frontend.preferences;

import java.io.File;
import java.util.ResourceBundle;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class ImageSelector{
	FileChooser fileChooser;
	File imageFile;
	public static final String RESOURCE_PACKAGE = "English";
	private ResourceBundle resources = ResourceBundle.getBundle("resources.ui/" + RESOURCE_PACKAGE);
	
	public ImageSelector(String titleKey){
		//Code inspired from the link
		//https://docs.oracle.com/javase/8/javafx/api/javafx/stage/FileChooser.html
		fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().add(new ExtensionFilter(resources.getString("ImageFiles"), "*.png", "*.jpg", "*.gif"));
		imageFile = fileChooser.showOpenDialog(new Stage());
	}
	
	public void setInitialDirectory(String path){
		fileChooser.setInitialDirectory(new File(path));
	}
	
	public File getFile(){
		return imageFile;
	}
}
