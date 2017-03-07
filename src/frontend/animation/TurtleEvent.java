package frontend.animation;

import frontend.views.TurtleScreenController;

public abstract class TurtleEvent extends AnimatedEvent {

	protected TurtleScreenController control;

	public TurtleEvent(TurtleScreenController control) {
		this.control = control;
	}

}
