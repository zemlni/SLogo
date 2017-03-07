package frontend.animation;

import frontend.views.InputController;

public abstract class InputViewEvent extends AnimatedEvent {

	protected InputController control;
	
	public InputViewEvent(InputController control) {
		this.control = control;
	}
	
}
