package frontend.frontend.animation;

public abstract class AnimatedEvent {
	
	private boolean finished = false;
	
	/**
	 * Update the view and return the remaining time
	 * @param dt
	 * @return
	 */
	public abstract double update(double dt);
	protected void setFinishedTrue() { finished = true; }
	public boolean isFinished() { return finished; }
}
