package frontend.frontend.animation;

import backend.commands.UserCommand;
import frontend.commands.CommandsController;

public class AddCommandEvent extends CommandsViewEvent {

	private UserCommand command;
	
	public AddCommandEvent(CommandsController control, UserCommand command) {
		super(control);
		this.command = command;
	}

	@Override
	public double update(double dt) {
		control.addCommand(command);
		setFinishedTrue();
		return dt;
	}

}
