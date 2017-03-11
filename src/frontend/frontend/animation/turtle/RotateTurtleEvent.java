package frontend.frontend.animation.turtle;

import frontend.turtlescreen.TurtleScreenController;

public class RotateTurtleEvent extends TurtleEvent {
	
	private int id;
	private double a; // current angle
	private double a1; // target angle
	// the angle increases clock-wise
	
	
	public RotateTurtleEvent(TurtleScreenController control,
			int id, double angleStart, double angleEnd) {
		super(control);
		this.id = id;
		this.a = angleStart;
		this.a1 = angleEnd;
	}

	private double v() {
		return control.getTurtleRotatingSpeed();
	}
	
	@Override
	public double update(double dt) {
		double v = v();
		if (a == a1) {
			control.setTurtleAngle(id, a1);
			setFinishedTrue();
			return dt;
		}
		double da = a1 - a;
		if (da < 0) {
			v = -v;
		}
		if (da / v <= dt) { // action finished
			control.setTurtleAngle(id, a1);
			setFinishedTrue();
			return dt - da / v;
		} else { // action not finished
			control.setTurtleAngle(id, a + v*dt);
			a += v*dt;
			return 0;
		}
	}

}
