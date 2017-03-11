package frontend.frontend.animation.turtle;

import frontend.turtlescreen.TurtleScreenController;

public class HideTurtleEvent extends TurtleEvent {

	private int id;
	
	public HideTurtleEvent(TurtleScreenController control, int id) {
		super(control);
		this.id = id;
	}

	@Override
	public double update(double dt) {
		control.hideTurtle(id);
		setFinishedTrue();
		return dt;
	}

}
