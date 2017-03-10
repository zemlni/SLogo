package frontend.nonfxml.view;

import frontend.nonfxml.IConfigurableView;
import frontend.nonfxml.IControllableView;
import frontend.nonfxml.config.HistoryConfig;
import frontend.views.HistoryController;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class HistoryView extends ScrollPane implements IControllableView, IConfigurableView {

	private VBox historyBox;
	private HistoryController controller;
	
	public HistoryView() {
		this(null);
	}
	public HistoryView(HistoryConfig config) {
		// TODO load config
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

	@Override
	public HistoryConfig getConfig() {
		// TODO get config
		return new HistoryConfig();
	}

}
