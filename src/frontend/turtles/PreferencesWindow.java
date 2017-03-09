package frontend.turtles;


import java.util.ResourceBundle;

import frontend.views.TurtleScreenController;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PreferencesWindow {

public static final int WINDOW_WIDTH = 200;
public static final int WINDOW_HEIGHT = 200;
private TurtleScreenController turtleScreenController;
private Group root;
public static final String RESOURCE_PACKAGE = "English";
private ResourceBundle resources = ResourceBundle.getBundle("resources.ui/" + RESOURCE_PACKAGE);
	
	public PreferencesWindow(TurtleScreenController turtleScreenControl){
		turtleScreenController = turtleScreenControl;
		Stage stage = new Stage();
		root = new Group();
		Scene preferencesScene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
		stage.setScene(preferencesScene);
		createButtons();
		stage.setTitle(resources.getString("PreferencesTitle"));
		stage.show();
	}
	
	public void createButtons(){
		VBox buttons = new VBox();
		buttons.setSpacing(10);
		buttons.setAlignment(Pos.CENTER);
		
		ColorSelector penColor = new ColorSelector(resources.getString("PenColor"));
		penColor.getColorPicker().setOnAction(e -> turtleScreenController.setPenColor(penColor.getColorPicker().getValue()));
		
		ColorSelector backColor = new ColorSelector(resources.getString("BackColor"));
		backColor.getColorPicker().setOnAction(e -> turtleScreenController.setBackground(backColor.getColorPicker().getValue()));
		
		Button imageSelect = new Button(resources.getString("ImageSelect"));
		imageSelect.setOnAction(e -> turtleScreenController.changeTurtleImage());
		
		Button currentOnToggle = new Button(resources.getString("ShowCurrents"));
		currentOnToggle.setOnAction(e -> turtleScreenController.updateTurtles());
		
		buttons.getChildren().addAll(penColor, backColor, imageSelect, currentOnToggle);
		root.getChildren().add(buttons);
	}
}


