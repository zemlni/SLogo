package frontend.animation;

public abstract class AnimatedEvent {
	
	/**
	 * Update the view and return the remaining time
	 * @param dt
	 * @return
	 */
	public abstract double update(double dt);
	public abstract boolean isFinished();
}
