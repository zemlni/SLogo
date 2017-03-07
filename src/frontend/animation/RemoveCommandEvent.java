package frontend.animation;

import backend.Command;
import frontend.views.CommandsController;

public class RemoveCommandEvent extends CommandsViewEvent {

	private Command command;
	
	public RemoveCommandEvent(CommandsController control, Command command) {
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
