package frontend.history;

import frontend.IConfigurableView;
import frontend.IControllableView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class HistoryView extends ScrollPane implements IControllableView, IConfigurableView {

	private VBox historyBox;
	private HistoryController controller;
	
	public HistoryView() {
		historyBox = new VBox();
		this.setContent(historyBox);
		controller = new HistoryController(this);
	}
	public HistoryView(HistoryConfig config) {
		this();
		controller.addHistory(config.getHistories());
	}
	
	public VBox getHistoryBox() {
		return historyBox;
	}
	
	@Override
	public HistoryController getController() {
		return controller;
	}

	@Override
	public HistoryConfig getConfig() {
		return new HistoryConfig(controller.getHistories());
	}

}
