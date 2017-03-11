package frontend.history;

import frontend.frontend.FrontEndController;
import javafx.scene.control.Button;

public class HistoryEntry extends Button {
	
	public static final int MAX_WIDTH = 10000;
	
	public HistoryEntry(FrontEndController frontEnd, String text) {
		super(text);
		this.setMaxWidth(MAX_WIDTH);
		this.getStyleClass().add("table-entry");
		this.setOnAction(e -> frontEnd.appendText(this.getText()));
	}

}
	