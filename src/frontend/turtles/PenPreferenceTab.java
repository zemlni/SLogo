package frontend.turtles;

import java.util.ResourceBundle;

import frontend.views.TurtleScreenController;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import language.Language;

public class PenPreferenceTab extends PreferenceTab {
	
	public static final String RESOURCE_PACKAGE = "English";
	private ResourceBundle resources = ResourceBundle.getBundle("resources.ui/" + RESOURCE_PACKAGE);
	
	public PenPreferenceTab(TurtleScreenController controller){
		super(controller, "PenPrefs");
	}

	public void addButtons() {
		ColorSelector penColor = new ColorSelector("PenColor");
		penColor.getColorPicker().setOnAction(e -> getController().setPenColor(penColor.getColorPicker().getValue()));
		
		HBox penWidth = new HBox();
		Label penThick = new Label();
		penThick.textProperty().bind(Language.createStringBinding("PenThick"));
		penWidth.getChildren().addAll(penThick, new PenWidthSlider());
	
		getRoot().getChildren().addAll(penColor, penWidth, new PenUpDownButtons(getController()));
	}
}
