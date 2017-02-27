package backend;

/**
 * This is what will be thrown in the case when the command is not found in the
 * parser
 */
public class CommandException extends Exception {

	public CommandException(String text) {
		super(text);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
