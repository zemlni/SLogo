package frontend.animation.turtle;

import frontend.views.TurtleScreenController;

public class HideTurtleEvent extends TurtleEvent {

	private int id;
	
	public HideTurtleEvent(TurtleScreenController control, int id) {
		super(control);
		this.id = id;
	}

	@Override
	public double update(double dt) {
		control.hideTurtle(id);
		finished = true;
		return dt;
	}

}
