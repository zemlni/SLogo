package frontend.nonfxml.view;

import frontend.views.CommandsController;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class CommandsView extends ScrollPane implements IControllableView {

	private VBox commandsBox;
	private CommandsController controller;
	
	public CommandsView() {
		commandsBox = new VBox();
		this.setContent(commandsBox);
		controller = new CommandsController(this);
	}
	
	public VBox getCommandsBox() {
		return commandsBox;
	}
	
	@Override
	public CommandsController getController() {
		return controller;
	}
	
}
