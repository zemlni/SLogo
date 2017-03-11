package frontend.frontend.animation.turtle;

import frontend.turtlescreen.TurtleScreenController;

public class ShowTurtleEvent extends TurtleEvent{

	private int id;
	
	public ShowTurtleEvent(TurtleScreenController control, int id) {
		super(control);
		this.id = id;
	}

	@Override
	public double update(double dt) {
		control.showTurtle(id);
		setFinishedTrue();
		return dt;
	}

}
