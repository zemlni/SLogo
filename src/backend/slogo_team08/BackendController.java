package slogo_team08;

/**
 * Interface for the backend controller. This is external API. The backend
 * controller's evaluate method will be called from the front end when the user
 * enters a command or executes a script
 */
public interface BackendController {
	/**
	 * evaluate the command or script entered by the user will parse the
	 * command(s) and evaluate one by one, updating the front end on each step.
	 * 
	 * @param command
	 *            the command or script entered by the user
	 */
	public void evaluate(String command);
}
