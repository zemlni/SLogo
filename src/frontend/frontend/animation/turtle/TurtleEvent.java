package frontend.frontend.animation.turtle;

import frontend.frontend.animation.AnimatedEvent;
import frontend.turtlescreen.TurtleScreenController;

public abstract class TurtleEvent extends AnimatedEvent {

	protected TurtleScreenController control;

	public TurtleEvent(TurtleScreenController control) {
		this.control = control;
	}

}
