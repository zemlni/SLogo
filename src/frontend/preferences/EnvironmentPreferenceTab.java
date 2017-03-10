package frontend.preferences;


import frontend.views.TurtleScreenController;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import language.Language;

public class EnvironmentPreferenceTab extends PreferenceTab {
	
	public EnvironmentPreferenceTab(TurtleScreenController controller){
		super(controller, "EnviroPrefs");
	}

	public void addButtons() {
		ColorSelector penColor = new ColorSelector(getController().getPenColor(), "PenColor");
		penColor.getColorPicker().setOnAction(e -> getController().setPenColor(penColor.getColorPicker().getValue()));
		
		HBox penWidth = new HBox();
		Label penThick = new Label();
		penThick.textProperty().bind(Language.createStringBinding("PenThick"));
		penWidth.getChildren().addAll(penThick, new PenWidthSlider(getController()));
	
		ColorSelector backColor = new ColorSelector(Color.WHITE, "BackColor");
		backColor.getColorPicker().setOnAction(e -> getController().setBackground(backColor.getColorPicker().getValue()));
		
		getRoot().getChildren().addAll(penColor, penWidth, new PenUpDownButtons(getController()), backColor);
	}
}
