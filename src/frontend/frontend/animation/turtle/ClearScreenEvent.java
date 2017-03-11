package frontend.frontend.animation.turtle;

import frontend.turtlescreen.TurtleScreenController;

public class ClearScreenEvent extends TurtleEvent {

	public ClearScreenEvent(TurtleScreenController control) {
		super(control);
	}

	@Override
	public double update(double dt) {
		control.clearScreen();
		setFinishedTrue();
		return dt;
	}

}
