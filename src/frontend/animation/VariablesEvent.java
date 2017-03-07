package frontend.animation;

import frontend.views.VariablesController;

public abstract class VariablesEvent extends AnimatedEvent {
	
	protected VariablesController control;
	
	public VariablesEvent(VariablesController control) {
		this.control = control;
	}
	
}
