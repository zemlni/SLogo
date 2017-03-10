package frontend.nonfxml.view;

import frontend.nonfxml.IConfigurableView;
import frontend.nonfxml.IControllableView;
import frontend.nonfxml.config.TurtleScreenConfig;
import frontend.views.TurtleScreenController;
import javafx.scene.layout.Pane;

public class TurtleScreenView extends Pane implements IControllableView, IConfigurableView {

	private TurtleScreenController controller;
	
	public TurtleScreenView() {
		this(null);
	}
	public TurtleScreenView(TurtleScreenConfig config) {
		// TODO: load config
		controller = new TurtleScreenController(this);
	}

	public Pane getTurtlePane() {
		return this;
	}

	@Override
	public TurtleScreenController getController() {
		return controller;
	}

	@Override
	public TurtleScreenConfig getConfig() {
		// TODO get config
		return new TurtleScreenConfig();
	}
	
}
