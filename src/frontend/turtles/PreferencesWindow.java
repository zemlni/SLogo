package frontend.turtles;


import java.util.ResourceBundle;


import frontend.views.TurtleScreenController;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PreferencesWindow {

public static final int WINDOW_WIDTH = 400;
public static final int WINDOW_HEIGHT = 150;
private TurtleScreenController turtleScreenController;
private TabPane tabPane;
public static final String RESOURCE_PACKAGE = "English";
private ResourceBundle resources = ResourceBundle.getBundle("resources.ui/" + RESOURCE_PACKAGE);
	
	public PreferencesWindow(TurtleScreenController turtleScreenControl){
		turtleScreenController = turtleScreenControl;
		
		tabPane = new TabPane();
		tabPane.getTabs().add(new PenPreferenceTab(turtleScreenControl));
		Stage stage = new Stage();
		Scene preferencesScene = new Scene(tabPane, WINDOW_WIDTH, WINDOW_HEIGHT);
		stage.setScene(preferencesScene);
		
		stage.setTitle(resources.getString("PreferencesTitle"));
		stage.show();
	}
	
	public void createButtons(){
		VBox buttons = new VBox();
		buttons.setSpacing(10);
		buttons.setAlignment(Pos.CENTER);
		
		//ColorSelector backColor = new ColorSelector(resources.getString("BackColor"));
		//backColor.getColorPicker().setOnAction(e -> turtleScreenController.setBackground(backColor.getColorPicker().getValue()));
		
		Button imageSelect = new Button(resources.getString("ImageSelect"));
		imageSelect.setOnAction(e -> turtleScreenController.changeTurtleImage());
		
		Button currentOnToggle = new Button(resources.getString("ShowCurrents"));
		//currentOnToggle.setOnAction(e -> turtleScreenController.());
		
		//buttons.getChildren().addAll(backColor, imageSelect, currentOnToggle);
		Group root = new Group();
		root.getChildren().add(buttons);
	}
}

