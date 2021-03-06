package frontend.frontend.animation.turtle;

import frontend.turtlescreen.TurtleScreenController;

public class AddTurtleEvent extends TurtleEvent {

	private int id;
	
	public AddTurtleEvent(TurtleScreenController control, int id) {
		super(control);
		this.id = id;
	}

	@Override
	public double update(double dt) {
		control.addTurtle(id);
		setFinishedTrue();
		return dt;
	}

}
