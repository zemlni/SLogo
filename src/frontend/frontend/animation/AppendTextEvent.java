package frontend.frontend.animation;

import frontend.input.InputController;

public class AppendTextEvent extends InputViewEvent {

	private String text;
	
	public AppendTextEvent(InputController control, String text) {
		super(control);
		this.text = text;
	}

	@Override
	public double update(double dt) {
		control.appendText(text);
		setFinishedTrue();
		return dt;
	}

}
