package frontend.frontend.animation;

import backend.commands.UserCommand;
import frontend.commands.CommandsController;

public class RemoveCommandEvent extends CommandsViewEvent {

	private UserCommand command;
	
	public RemoveCommandEvent(CommandsController control, UserCommand command) {
		super(control);
		this.command = command;
	}

	@Override
	public double update(double dt) {
		try {
			// TODO: Hey! Don't throw this to me!
			control.removeCommand(command);
		} catch (Exception e) {
			setFinishedTrue();
		}
		setFinishedTrue();
		return 0;
	}

}
