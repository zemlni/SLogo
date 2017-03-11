package frontend.frontend.animation;

import java.util.List;

public class SynchronizedEventGroup extends AnimatedEvent {

	private List<AnimatedEvent> events;
	
	public SynchronizedEventGroup(List<AnimatedEvent> events) {
		this.events = events;
	}
	
	@Override
	public double update(double dt) {
		double tRemain = dt;
		for (AnimatedEvent event : events) {
			if (!event.isFinished()) {
				tRemain = Math.min(tRemain, event.update(dt));
			}
		}
		if (tRemain > 0) { setFinishedTrue(); }
		return tRemain;
	}

}
