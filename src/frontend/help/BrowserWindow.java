package frontend.help;

import java.io.IOException;
import java.util.ResourceBundle;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class BrowserWindow extends Stage {
	public static final String MAIN_PAGE_RESOURCE = "/frontend/help/browser-view.fxml";
    public static final String DEFAULT_RESOURCE_PACKAGE = "resources/ui/";
    public static final String LANGUAGE = "English";
    private static ResourceBundle resourceBundle;
    
    public static final double WIDTH = 700;
    public static final double HEIGHT = 600;

    public BrowserWindow(String url) throws IOException {
    	super();
		resourceBundle = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + LANGUAGE);
        FXMLLoader loader = new FXMLLoader(getClass().getResource(MAIN_PAGE_RESOURCE));
        loader.setResources(resourceBundle);
        AnchorPane root = (AnchorPane) loader.load();
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        ((BrowserController) loader.getController()).goToURL(url);
        
        this.setTitle(resourceBundle.getString("Title"));
        this.setScene(scene);
        this.sizeToScene();
        this.getIcons().add(new Image("turtle.png"));
	}
    
}
