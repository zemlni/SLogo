package frontend;

/**
 * This interface sets forth the methods that must be implemented in the CommandsController
 * class. The goal of this class is to control the Commands window which show the 
 * commands that are saved and can be executed. This commands are basically functions created
 * by the user.
 * @author Matthew Tribby
 *
 */
public interface CommandsControllerInterface {
	/**
	 * Adds a command to be tracked by the CommandsController. Adds it to the commands window
	 * so that the user can see it.
	 * @param command
	 */
	void addCommand(Command command);
	
	/**
	 * Removes the command, basically meaning that the CommandsController will no longer
	 * keep track of this command. Removes it visually from the commands window.
	 * @param command
	 */
	void removeCommand(Command command);
}
