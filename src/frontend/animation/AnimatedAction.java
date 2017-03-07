package frontend.animation;

import frontend.app.FrontEndController;

public abstract class AnimatedAction {
	
	protected FrontEndController frontEndController;
	public AnimatedAction(FrontEndController frontEndController) {
		this.frontEndController = frontEndController;
	}
	/**
	 * Update the view and return the remaining time
	 * @param dt
	 * @return
	 */
	public abstract double update(double dt);
	public abstract boolean isFinished();
}
