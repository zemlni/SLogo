package frontend.history;

import java.util.ArrayList;
import java.util.List;
import frontend.IViewController;
import frontend.frontend.FrontEndController;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.VBox;


/**
 * The goal of the HistoryController class is to handle the front-end needs of the
 * History window which displays the history of commands the user has entered and allows
 * the user to click on these commands to execute them.
 * @author Matthew Tribby
 */
public class HistoryController implements IViewController {

	@FXML
	private VBox historyBox;
	
	private FrontEndController frontEnd;
	
	public HistoryController(HistoryView view) {
		historyBox = view.getHistoryBox();
	}
	
	public void setFrontEndController(FrontEndController frontEnd) {
		this.frontEnd = frontEnd;
		for (Node entry : historyBox.getChildren()) {
			((HistoryEntry) entry).setFrontEndController(frontEnd);
		}
	}
	
	public void addHistory(String history) {
		historyBox.getChildren().add(new HistoryEntry(frontEnd, history));
	}
	public void addHistory(List<String> histories) {
		for (String history : histories) {
			addHistory(history);
		}
	}
	public List<String> getHistories() {
		List<String> histories = new ArrayList<>();
		for (Node entry : historyBox.getChildren()) {
			histories.add(((HistoryEntry) entry).getText());
		}
		return histories;
	}

	public void clearHistory() {
		historyBox.getChildren().clear();
	}
	
}
