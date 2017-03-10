package frontend.turtles;

import java.util.ResourceBundle;

import frontend.views.TurtleScreenController;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PenPreferenceTab extends Tab {
	
	public static final String RESOURCE_PACKAGE = "English";
	private ResourceBundle resources = ResourceBundle.getBundle("resources.ui/" + RESOURCE_PACKAGE);
	private VBox tabRoot;
	private TurtleScreenController turtleScreenController;
	
	public PenPreferenceTab(TurtleScreenController controller){
		super();
		turtleScreenController = controller;
		
		setText(resources.getString("PenPrefs"));
		tabRoot = new VBox();
		
		setContent(tabRoot);
		addButtons();
	}

	private void addButtons() {
		ColorSelector penColor = new ColorSelector(resources.getString("PenColor"));
		penColor.getColorPicker().setOnAction(e -> turtleScreenController.setPenColor(penColor.getColorPicker().getValue()));
		
		HBox penWidth = new HBox();
		penWidth.getChildren().addAll(new Label("Pen Thickness"), new PenWidthSlider());
		
		tabRoot.getChildren().addAll(penColor, penWidth);
	}
}
