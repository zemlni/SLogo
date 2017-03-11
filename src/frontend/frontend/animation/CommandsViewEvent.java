package frontend.frontend.animation;

import frontend.commands.CommandsController;

public abstract class CommandsViewEvent extends AnimatedEvent {

	protected CommandsController control;
	
	public CommandsViewEvent(CommandsController control) {
		this.control = control;
	}

}
