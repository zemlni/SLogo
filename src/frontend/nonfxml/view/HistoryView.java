package frontend.nonfxml.view;

import frontend.views.HistoryController;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class HistoryView extends ScrollPane implements IControllableView {

	private VBox historyBox;
	private HistoryController controller;
	
	public HistoryView() {
		historyBox = new VBox();
		this.setContent(historyBox);
		controller = new HistoryController(this);
	}
	
	public VBox getHistoryBox() {
		return historyBox;
	}
	
	@Override
	public HistoryController getController() {
		return controller;
	}

}
