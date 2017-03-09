package frontend.nonfxml.view;

import frontend.views.VariablesController;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class VariablesView extends ScrollPane implements IControllableView {

	
	private VBox variablesBox;
	private VariablesController controller;
	
	public VariablesView() {
		variablesBox = new VBox();
		controller = new VariablesController(this);
	}

	public VBox getVariablesBox() {
		return variablesBox;
	}

	@Override
	public VariablesController getController() {
		return controller;
	}
	
}
