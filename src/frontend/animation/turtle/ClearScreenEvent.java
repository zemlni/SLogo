package frontend.animation.turtle;

import frontend.views.TurtleScreenController;

public class ClearScreenEvent extends TurtleEvent {

	public ClearScreenEvent(TurtleScreenController control) {
		super(control);
	}

	@Override
	public double update(double dt) {
		control.clearScreen();
		finished = true;
		return dt;
	}

}
