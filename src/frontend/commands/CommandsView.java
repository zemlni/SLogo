package frontend.commands;

import frontend.IConfigurableView;
import frontend.IControllableView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class CommandsView extends ScrollPane implements IControllableView, IConfigurableView {

	private VBox commandsBox;
	private CommandsController controller;
	
	public CommandsView() {
		this(null);
	}
	public CommandsView(CommandsConfig config) {
		commandsBox = new VBox();
		this.setContent(commandsBox);
		if (config != null) {
			controller = new CommandsController(this, config.getCommands());
		} else {
			controller = new CommandsController(this);
		}
	}
	
	public VBox getCommandsBox() {
		return commandsBox;
	}
	
	@Override
	public CommandsController getController() {
		return controller;
	}

	@Override
	public CommandsConfig getConfig() {
		return new CommandsConfig(controller.getCommands());
	}
	
}
