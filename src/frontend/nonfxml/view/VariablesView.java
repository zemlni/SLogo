package frontend.nonfxml.view;

import frontend.nonfxml.IConfigurableView;
import frontend.nonfxml.IControllableView;
import frontend.nonfxml.config.VariablesConfig;
import frontend.views.VariablesController;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class VariablesView extends ScrollPane implements IControllableView, IConfigurableView {

	private VBox variablesBox;
	private VariablesController controller;
	
	public VariablesView() {
		this(null);
	}
	public VariablesView(VariablesConfig config) {
		variablesBox = new VBox();
		this.setContent(variablesBox);

		if (config != null) {
			controller = new VariablesController(this, config.getVariables()); 
		} else {
			controller = new VariablesController(this);
		}
	}

	public VBox getVariablesBox() {
		return variablesBox;
	}

	@Override
	public VariablesController getController() {
		return controller;
	}

	@Override
	public VariablesConfig getConfig() {
		return new VariablesConfig(controller.getVariables());
	}
	
}
