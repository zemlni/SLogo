package frontend.views;

import frontend.app.FrontEndController;
import frontend.history.HistoryEntry;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;


/**
 * The goal of the HistoryController class is to handle the front-end needs of the
 * History window which displays the history of commands the user has entered and allows
 * the user to click on these commands to execute them.
 * @author Matthew Tribby
 */
public class HistoryController {

	@FXML
	private VBox historyBox;
	
	private FrontEndController frontEnd;
	public void setFrontEndController(FrontEndController frontEnd) {
		this.frontEnd = frontEnd;
	}
	
	public void addHistory(String history) {
		historyBox.getChildren().add(new HistoryEntry(frontEnd, history));
	}

	public void clearHistory() {
		historyBox.getChildren().clear();
	}
	
}
