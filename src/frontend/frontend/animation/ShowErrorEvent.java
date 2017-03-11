package frontend.frontend.animation;

import frontend.input.InputController;

/**
 * showAndWait is not allowed during animation or layout processing!
 * @author keping
 */
@Deprecated
public class ShowErrorEvent extends InputViewEvent {

	private String errorMsg;
	private String bad;
	
	public ShowErrorEvent(InputController control, String errorMsg, String bad) {
		super(control);
		this.errorMsg = errorMsg;
		this.bad = bad;
	}

	@Override
	public double update(double dt) {
		control.showError(errorMsg, bad);
		setFinishedTrue();
		return dt;
	}

}
