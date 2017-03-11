package frontend.input;

import frontend.IViewController;
import frontend.frontend.FrontEndController;
import frontend.input.shell.Shell;
import utils.language.Language;


/**
 * This interface is in charge of methods that need to be implemented to handle the
 * shell (where the user enters commands one by one).
 * @author Matthew Tribby
 */
public class ShellController implements InputController, IViewController {

	private Shell shell;

	private FrontEndController frontEnd;
	
	public ShellController(ShellView view) {
		shell = view.getShell();
	}
	
	public void setFrontEndController(FrontEndController frontEnd) {
		this.frontEnd = frontEnd;
		shell.setFrontEndController(frontEnd);
	}
	
	@Override
	public void showError(String error, String bad) {
		shell.appendToLabel(Language.getWord(error)+bad);
	}
	
	@Override
	public void showText(String text) {
		shell.appendToLabel(text);
	}

	@Override
	public void appendText(String text) {
		shell.appendToField(text);
	}
	
	
}
