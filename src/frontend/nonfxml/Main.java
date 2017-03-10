package frontend.nonfxml;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import language.Language;

public class Main extends Application {
	public static final String MAIN_PAGE_RESOURCE = "/frontend/app/app.fxml";
    public static final String DEFAULT_RESOURCE_PACKAGE = "resources/ui/";
    public static final String LANGUAGE = "English";
    private static ResourceBundle resourceBundle;
    
    public static final double WIDTH = 800;
    public static final double HEIGHT = 650;

    private Scene scene;
    
	@Override
	public void start(Stage stage) throws Exception {
		resourceBundle = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + LANGUAGE);

		
        scene = new Scene(new AppView(), WIDTH, HEIGHT);
		URL url = getClass().getResource("/frontend/app/app-style.css");
	    String css = url.toExternalForm(); 
	    scene.getStylesheets().add(css);
        
        stage.titleProperty().bind(Language.createStringBinding("Title"));
        stage.setScene(scene);
        stage.sizeToScene();
        stage.getIcons().add(new Image("turtle.png"));
        stage.show();  
	}
	
	public static ResourceBundle getResourceBundle() {
		return resourceBundle;
	}

	public static void main(String[] args) {
		Application.launch(args);
	}

}
