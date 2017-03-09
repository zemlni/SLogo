package frontend.animation;

import backend.Command;
import frontend.views.CommandsController;

public class AddCommandEvent extends CommandsViewEvent {

	private Command command;
	
	public AddCommandEvent(CommandsController control, Command command) {
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
