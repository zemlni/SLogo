package frontend.input;

import frontend.IControllableView;

public interface InputView extends IControllableView {
	
	@Override
	public InputController getController();

}
