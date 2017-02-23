package frontend;

import backend.Command;

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
	 * @throws Exception if command that is trying to be removed does not currently
	 * exist in the front-end. This exception will be more specifically defined.
	 */
	void removeCommand(Command command) throws Exception;
}
