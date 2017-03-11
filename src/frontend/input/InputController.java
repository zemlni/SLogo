package frontend.input;

import frontend.IViewController;

/**
 * A common interface for handling user command input,
 * currently implemented by {@link ShellController} and {@link ScriptController}
 * @author keping
 */
public interface InputController extends IViewController {
	
	public void showText(String text);
	public void showError(String errorMsg, String bad);
	public void appendText(String text);
}
