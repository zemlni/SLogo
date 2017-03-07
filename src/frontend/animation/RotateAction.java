package frontend.animation;

import frontend.app.FrontEndController;

public class RotateAction extends AnimatedAction {
	private static final double ROTATING_SPEED = 20;

	private double vAngle = ROTATING_SPEED;
	private double angleRemain;
	
	public RotateAction(FrontEndController frontEndController, double angle) {
		super(frontEndController);
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
