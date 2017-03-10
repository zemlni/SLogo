package frontend.nonfxml.view;

import frontend.nonfxml.IConfigurableView;
import frontend.nonfxml.IControllableView;
import frontend.nonfxml.config.CommandsConfig;
import frontend.views.CommandsController;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class CommandsView extends ScrollPane implements IControllableView, IConfigurableView {

	private VBox commandsBox;
	private CommandsController controller;
	
	public CommandsView() {
		this(null);
	}
	public CommandsView(CommandsConfig config) {
		// TODO load config
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

	@Override
	public CommandsConfig getConfig() {
		// TODO get config
		return new CommandsConfig();
	}
	
}
