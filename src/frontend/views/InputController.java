package frontend.views;

/**
 * A common interface for handling user command input,
 * currently implemented by {@link ShellController} and {@link ScriptController}
 * @author keping
 */
public interface InputController {
	
	public void showText(String text);
	public void showError(String errorMsg);

}
