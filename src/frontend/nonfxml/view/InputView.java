package frontend.nonfxml.view;

import frontend.nonfxml.IControllableView;
import frontend.views.InputController;

public interface InputView extends IControllableView {
	
	@Override
	public InputController getController();

}
