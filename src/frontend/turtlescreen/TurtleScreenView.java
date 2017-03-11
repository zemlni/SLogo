package frontend.turtlescreen;

import frontend.IConfigurableView;
import frontend.IControllableView;
import javafx.scene.layout.Pane;

public class TurtleScreenView extends Pane implements IControllableView, IConfigurableView {

	private TurtleScreenController controller;
	
	public TurtleScreenView() {
		controller = new TurtleScreenController(this);
	}
	public TurtleScreenView(TurtleScreenConfig config) {
		controller = new TurtleScreenController(this, config);
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
		return controller.getTurtleScreenConfig();
	}
	
}
