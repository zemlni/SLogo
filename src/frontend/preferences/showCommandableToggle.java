package frontend.preferences;

import javafx.scene.control.ToggleButton;
import language.Language;

public class showCommandableToggle extends ToggleButton {

	public showCommandableToggle(){
		super();
		this.textProperty().bind(Language.createStringBinding("ShowCommandable"));
		
	}
}
