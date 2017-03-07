package frontend.animation;

public abstract class AnimatedEvent {
	
	protected boolean finished = false;
	
	/**
	 * Update the view and return the remaining time
	 * @param dt
	 * @return
	 */
	public abstract double update(double dt);
	public boolean isFinished() { return finished; }
}
