package frontend.turtlescreen.preferences;

import javafx.scene.control.Alert;
import utils.language.Language;

public class PaletteIndexAlert extends Alert {

	public PaletteIndexAlert(AlertType alertType) {
		super(alertType);
		this.titleProperty().bind(Language.createStringBinding("PalIndexError"));
		this.contentTextProperty().bind(Language.createStringBinding("PalIndexErrorMessage"));
		this.showAndWait();
	}

}
