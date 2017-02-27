package backend;

/**
 * Interface for the parser. This is internal API. The parser will be called
 * from the BackendController class from the evaluate method. Additionally, the
 * parser may have to be called when updating user defined commands and
 * variables.
 */
public interface ParserInterface {
	/**
	 * parse the command(s) and transform them into a list of Command instances.
	 * Throws a CommandError if the command was not recognized.
	 * 
	 * @param command
	 *            the command(s) to be parsed
	 * @return list of Command instances that resulted from the parsing.
	 */
	public double parse(String command) throws CommandError;
}
