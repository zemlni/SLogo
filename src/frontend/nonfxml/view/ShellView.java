package frontend.nonfxml.view;

import frontend.views.ShellController;
import javafx.scene.control.ScrollPane;

public class ShellView extends ScrollPane implements InputView {

	private ShellController controller;
	
	public ShellView() {
		controller = new ShellController(this);
	}
	
	public ScrollPane getShellBox() {
		return this;
	}
	
	@Override
	public ShellController getController() {
		return controller;
	}

}
