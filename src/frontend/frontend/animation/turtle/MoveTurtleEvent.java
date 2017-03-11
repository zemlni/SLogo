package frontend.frontend.animation.turtle;

import frontend.turtlescreen.TurtleScreenController;

public class MoveTurtleEvent extends TurtleEvent {
	
	private int id;
	private double x; // current position
	private double y;
	private double x1; // target position
	private double y1;
	private boolean penDown;
	
	public MoveTurtleEvent(TurtleScreenController control,
			int id,	double x0, double y0, double x1, double y1, boolean penDown) {
		super(control);
		this.id = id;
		this.x = x0;
		this.y = y0;
		this.x1 = x1;
		this.y1 = y1;
		this.penDown = penDown;
	}

	private double v() {
		return control.getTurtleMovingSpeed();
	}

	@Override
	public double update(double dt) {
		double v = v();
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
