package frontend.history;

import frontend.app.FrontEndController;
import javafx.scene.control.Button;

public class HistoryEntry extends Button {
	
	public HistoryEntry(FrontEndController frontEnd, String text) {
		super(text);
		this.setMaxWidth(10000); // hard coded.
		this.getStyleClass().add("table-entry");
		this.setOnAction(e -> frontEnd.appendText(this.getText()));
	}

}
	