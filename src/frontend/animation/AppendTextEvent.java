package frontend.animation;

import frontend.views.InputController;

public class AppendTextEvent extends InputViewEvent {

	private String text;
	
	public AppendTextEvent(InputController control, String text) {
		super(control);
		this.text = text;
	}

	@Override
	public double update(double dt) {
		control.appendText(text);
		finished = true;
		return dt;
	}

}
