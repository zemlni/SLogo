package frontend.history;

import frontend.frontend.FrontEndController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class HistoryEntry extends Button {
	
	public static final int MAX_WIDTH = 10000;
	private FrontEndController controller;
	
	public HistoryEntry(FrontEndController frontEnd, String text) {
		super(text);
		this.setMaxWidth(MAX_WIDTH);
		this.getStyleClass().add("table-entry");
		this.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				controller.appendText(text);				
			}
		});
	}

	public void setFrontEndController(FrontEndController frontEnd) {
		controller = frontEnd;
	}
	
}