package frontend.nonfxml.view;

import frontend.views.ShellController;
import javafx.scene.control.ScrollPane;

public class ShellView extends ScrollPane implements IControllableView {

	private ScrollPane shellBox;
	private ShellController controller;
	
	public ShellView() {
		shellBox = new ScrollPane();
		controller = new ShellController(this);
	}
	
	public ScrollPane getShellBox() {
		return shellBox;
	}
	
	@Override
	public ShellController getController() {
		return controller;
	}

}
