package backend;

/**
 * This will be the interface for the abstract class Command. All commands must
 * be of this format. This is only internal API. These will be subclassed for
 * every possible command
 */
public interface CommandInterface {
	/**
	 * execute command, update variables and commands and update front end
	 * accordingly. Check variables if they were defined in the `VariableTable`
	 * 
	 * @return return value for this command
	 */
	public abstract double execute();

	/**
	 * get the key (name) of this command
	 * 
	 * @return the key(name) of the command
	 */
	public String getKey();

	/**
	 * get the amount of arguments this command takes
	 * 
	 * @return amount of arguments this command takes
	 */
	public int getNumArgs();
}
