package frontend.animation.turtle;

import frontend.views.TurtleScreenController;

public class MoveTurtleEvent extends TurtleEvent {
	private static final double INIT_MOVING_SPEED = 100;
	private static double v = INIT_MOVING_SPEED;
	
	private int id;
	private double x; // current position
	private double y;
	private double x1; // target position
	private double y1;
	private boolean penDown;
	
	public MoveTurtleEvent(TurtleScreenController control, int id,
			double x0, double y0, double x1, double y1, boolean penDown) {
		super(control);
		this.id = id;
		this.x = x0;
		this.y = y0;
		this.x1 = x1;
		this.y1 = y1;
		this.penDown = penDown;
	}
	
	public static void speedUp() {
		setSpeed(v * 1.5);
	}
	public static void slowDown() {
		setSpeed(v * 2.0 / 3);
	}
	private static void setSpeed(double newV) {
		v = newV;
	}

	@Override
	public double update(double dt) {
		double dx = x1 - x;
		double dy = y1 - y;
		double dist = Math.sqrt(dx*dx+dy*dy);
		if (dist == 0) {
			control.moveTurtleTo(id, x1, y1);
			setFinishedTrue();
			return dt;
		}
		double vx = v * dx / dist;
		double vy = v * dy / dist;
		if (dist / v <= dt) { // action finished 
			control.moveTurtleTo(id, x1, y1);
			if (penDown) { control.drawLine(x, y, x1, y1); }
			setFinishedTrue();
			return dt - dist / v;
		} else { // action unfinished
			control.moveTurtleTo(id, x+vx*dt, y+vy*dt);
			if (penDown) { control.drawLine(x, y, x+vx*dt, y+vy*dt); }
			x += vx*dt;
			y += vy*dt;
			return 0;
		}
	}

}
