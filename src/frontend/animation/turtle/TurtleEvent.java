package frontend.animation.turtle;

import frontend.animation.AnimatedEvent;
import frontend.views.TurtleScreenController;

public abstract class TurtleEvent extends AnimatedEvent {

	protected TurtleScreenController control;

	public TurtleEvent(TurtleScreenController control) {
		this.control = control;
	}

}
