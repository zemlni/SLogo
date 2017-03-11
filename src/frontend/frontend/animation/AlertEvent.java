package frontend.frontend.animation;

import utils.javafx.FX;

public class AlertEvent extends AnimatedEvent {

	private String errorMsg;
	private String bad;
	
	public AlertEvent(String errorMsg, String bad) {
		this.errorMsg = errorMsg;
		this.bad = bad;
	}
	
	@Override
	public double update(double dt) {
		FX.alertError("ErrorTitle", errorMsg, bad);
		setFinishedTrue();
		return dt;
	}

}
