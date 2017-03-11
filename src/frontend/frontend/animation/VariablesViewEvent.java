package frontend.frontend.animation;

import frontend.variables.VariablesController;

public abstract class VariablesViewEvent extends AnimatedEvent {
	
	protected VariablesController control;

	public VariablesViewEvent(VariablesController control) {
		this.control = control;
	}
	
}
