package frontend.turtlescreen.preferences;

import frontend.turtlescreen.TurtleScreenController;
import javafx.scene.control.CheckBox;
import utils.language.Language;

public class ShowCommandableToggle extends CheckBox {

	public ShowCommandableToggle(TurtleScreenController controller){
		super();
		this.textProperty().bind(Language.createStringBinding("ShowCommandable"));
		this.setSelected(true);
		this.setOnAction(e -> controller.updateShowCommandable(this.isSelected()));
	}
}
