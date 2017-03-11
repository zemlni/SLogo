package frontend.turtlescreen.preferences;


import java.util.ResourceBundle;
import frontend.turtlescreen.TurtleScreenController;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

public class PreferencesWindow {

public static final int WINDOW_WIDTH = 400;
public static final int WINDOW_HEIGHT = 200;
private TurtleScreenController turtleScreenController;
private TabPane tabPane;
public static final String RESOURCE_PACKAGE = "English";
private ResourceBundle resources = ResourceBundle.getBundle("resources.ui/" + RESOURCE_PACKAGE);
	
	public PreferencesWindow(TurtleScreenController turtleScreenControl){
		turtleScreenController = turtleScreenControl;
		
		tabPane = new TabPane();
		tabPane.getTabs().addAll(new EnvironmentPreferenceTab(turtleScreenControl), new TurtleTab(turtleScreenController));
		Stage stage = new Stage();
		Scene preferencesScene = new Scene(tabPane, WINDOW_WIDTH, WINDOW_HEIGHT);
		stage.setScene(preferencesScene);
		
		stage.setTitle(resources.getString("PreferencesTitle"));
		stage.show();
	}
	
	public void addTab(Tab tab){
		tabPane.getTabs().add(tab);
	}
}


