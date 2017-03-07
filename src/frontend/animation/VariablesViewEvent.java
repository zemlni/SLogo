package frontend.animation;

import frontend.views.VariablesController;

public abstract class VariablesViewEvent extends AnimatedEvent {
	
	protected VariablesController control;

	public VariablesViewEvent(VariablesController control) {
		this.control = control;
	}
	
}
