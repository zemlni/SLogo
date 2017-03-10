package frontend.preferences;


import frontend.views.TurtleScreenController;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import language.Language;

public class PenPreferenceTab extends PreferenceTab {
	
	public PenPreferenceTab(TurtleScreenController controller){
		super(controller, "PenPrefs");
	}

	public void addButtons() {
		ColorSelector penColor = new ColorSelector(getController().getPenColor(), "PenColor");
		penColor.getColorPicker().setOnAction(e -> getController().setPenColor(penColor.getColorPicker().getValue()));
		
		HBox penWidth = new HBox();
		Label penThick = new Label();
		penThick.textProperty().bind(Language.createStringBinding("PenThick"));
		penWidth.getChildren().addAll(penThick, new PenWidthSlider(getController()));
	
		getRoot().getChildren().addAll(penColor, penWidth, new PenUpDownButtons(getController()));
	}
}
