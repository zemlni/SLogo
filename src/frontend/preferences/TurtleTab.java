package frontend.preferences;

import frontend.views.TurtleScreenController;
import javafx.scene.control.Button;
import utils.javafx.FX;


public class TurtleTab extends PreferenceTab{
	
	public TurtleTab(TurtleScreenController controller){
		super(controller, "Turtle");
	}

	@Override
	public void addButtons() {
		Button imageSelect = FX.button("ImageSelect", e -> getController().changeTurtleImage());
		getRoot().getChildren().addAll(imageSelect, new ShowCommandableToggle(getController()), new TurtleSpeedButtons(getController()), new TurtleVisibleButtons(getController()));
	}
}
