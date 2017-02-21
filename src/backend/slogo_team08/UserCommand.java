package slogo_team08;

/**
 * interface for user defined commands. This is external API because update will
 * be called from the front end when it is updated by the user
 */
public interface UserCommand extends Command {
	/**
	 * update the command from the front end when user changes it in the command
	 * UI window.
	 * 
	 * @param newCommand
	 *            the new command that is now to be this command.
	 */
	public void update(String newCommand);
}
