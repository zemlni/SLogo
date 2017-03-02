package frontend.views;

import frontend.app.FrontEndController;


/**
 * This interface is in charge of methods that need to be implemented to handle the
 * shell (where the user enters commands one by one).
 * @author Matthew Tribby
 */
public class ShellController implements InputController {

	private FrontEndController frontEnd;
	public void setFrontEndController(FrontEndController frontEnd) {
		this.frontEnd = frontEnd;
	}
	
	@Override
	public void showError(String error, String bad) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showText(String text) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void appendText(String text) {
		// TODO Auto-generated method stub
		
	}
	
	
}
