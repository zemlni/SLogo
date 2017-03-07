package frontend.animation;

import frontend.app.FrontEndController;

public class MoveDrawLineAction extends AnimatedAction {
	private static final double INIT_MOVING_SPEED = 100;
	
	private double x; // current position
	private double y;
	private static double v = INIT_MOVING_SPEED;
	private double x1; // target position
	private double y1;
	private boolean finished;
	
	public MoveDrawLineAction(FrontEndController frontEndController,
			double x0, double y0, double x1, double y1) {
		super(frontEndController);
		this.x = x0;
		this.y = y0;
		this.x1 = x1;
		this.y1 = y1;
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
			finished = true;
			return dt;
		}
		double vx = v * dx / dist;
		double vy = v * dy / dist;
		if (dist / v <= dt) { // action finished 
			frontEndController.drawLine(x, y, x1, y1);
			frontEndController.moveTurtleTo(x1, y1);
			finished = true;
			return dt - dist / v;
		} else { // action unfinished
			frontEndController.drawLine(x, y, x+vx*dt, y+vy*dt);
			frontEndController.moveTurtleTo(x+vx*dt, y+vy*dt);
			x += vx*dt;
			y += vy*dt;
			finished = false;
			return 0;
		}
	}

	@Override
	public boolean isFinished() {
		return finished;
	}

}
