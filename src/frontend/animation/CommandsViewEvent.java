package frontend.animation;

import frontend.views.CommandsController;

public abstract class CommandsViewEvent extends AnimatedEvent {

	protected CommandsController control;
	
	public CommandsViewEvent(CommandsController control) {
		this.control = control;
	}

}
