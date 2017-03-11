package frontend.frontend.animation;

import frontend.input.InputController;

public abstract class InputViewEvent extends AnimatedEvent {

	protected InputController control;
	
	public InputViewEvent(InputController control) {
		this.control = control;
	}
	
}
