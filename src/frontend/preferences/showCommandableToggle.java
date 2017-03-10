package frontend.preferences;

import frontend.views.TurtleScreenController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ToggleButton;
import language.Language;

public class showCommandableToggle extends ToggleButton {

	public showCommandableToggle(TurtleScreenController controller){
		super();
		this.textProperty().bind(Language.createStringBinding("ShowCommandable"));
		
		this.selectedProperty().addListener(new ChangeListener<Boolean>(){

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				controller.updateShowCommandable(newValue);
			}
		});
	}
}
