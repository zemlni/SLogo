package frontend.nonfxml.view;

import frontend.views.TurtleScreenController;
import javafx.scene.layout.Pane;

public class TurtleScreenView extends Pane implements IControllableView {

	private TurtleScreenController controller;
	
	public TurtleScreenView() {
		controller = new TurtleScreenController(this);
	}

	public Pane getTurtlePane() {
		return this;
	}

	@Override
	public TurtleScreenController getController() {
		return controller;
	}
	
}
