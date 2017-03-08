package frontend.animation.turtle;

import frontend.views.TurtleScreenController;

public class RotateTurtleEvent extends TurtleEvent {
	private static final double INIT_ROTATING_SPEED = 20;
	private double vAbs = INIT_ROTATING_SPEED;
	
	private int id;
	private double a; // current angle
	private double a1; // target angle
	// the angle increases clock-wise
	
	public RotateTurtleEvent(TurtleScreenController control, int id, double angleStart, double angleEnd) {
		super(control);
		this.id = id;
		this.a = angleStart;
		this.a1 = angleEnd;
	}

	@Override
	public double update(double dt) {
		if (a == a1) {
			control.setTurtleAngle(id, a1);
			setFinishedTrue();
			return dt;
		}
		double da = a1 - a;
		double v = vAbs;
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
