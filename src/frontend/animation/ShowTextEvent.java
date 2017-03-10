package frontend.animation;

import frontend.views.InputController;

public class ShowTextEvent extends InputViewEvent {

	private String text;
	
	public ShowTextEvent(InputController control, String text) {
		super(control);
		this.text = text;
	}

	@Override
	public double update(double dt) {
		control.showText(text);
		setFinishedTrue();
		return dt;
	}

}
