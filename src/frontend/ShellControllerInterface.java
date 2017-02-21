package frontend;

	/**
	 * This interface is in charge of methods that need to be implemented to handle the
	 * shell (where the user enters commands one by one).
	 * @author Matthew Tribby
	 */
public interface ShellControllerInterface {
	/**
	 * Shows an error to the user in the shell when one occurs for a variety of reasons
	 * @param error String representation of the error that occurs
	 */
	void showError(String error);
	
	/**
	 * Shows text to the user in the shell for explanatory or helpful purposes
	 * @param text
	 */
	void showText(String text);
}
