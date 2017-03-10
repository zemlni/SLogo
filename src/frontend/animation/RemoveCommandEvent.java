package frontend.animation;

import backend.commands.UserCommand;
import frontend.views.CommandsController;

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
			e.printStackTrace();
		}
		setFinishedTrue();
		return 0;
	}

}
