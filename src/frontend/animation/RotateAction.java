package frontend.animation;

import frontend.views.TurtleScreenController;

public class RotateAction extends TurtleEvent {
	private static final double ROTATING_SPEED = 20;

	private double vAngle = ROTATING_SPEED;
	private double angleRemain;
	
	public RotateAction(TurtleScreenController control, double angle) {
		super(control);
		this.angleRemain = angle;
	}

	@Override
	public double update(double dt) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
