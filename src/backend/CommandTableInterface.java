package backend;

/**
 * This is the interface that the command table will have to follow. This is
 * also accessed by commands in the back end and will update the command window
 * in the front end. To the front end, this will display all user defined
 * commands. To the back end, it will contain all language and user defined
 * commands. This is only internal API.
 */
public interface CommandTableInterface {

	/**
	 * get a command by its name
	 * 
	 * @param name
	 *            name of command requester
	 * @return command requested
	 */
	public Command getCommand(String name) throws CommandException;

	/**
	 * add or reset a command in the variable table
	 * 
	 * @param command
	 *            the new command to be added or to be reset
	 */
	public void setCommand(Command command);

}
