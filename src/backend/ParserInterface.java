package backend;

import java.util.List;

import backend.parser.Expression;

/**
 * Interface for the parser. This is internal API. The parser will be called
 * from the BackendController class from the evaluate method. Additionally, the
 * parser may have to be called when updating user defined commands and
 * variables.
 */
public interface ParserInterface {
	/**
	 * parse the command(s) and transform them into a list of Command instances.
	 * Throws a CommandException if the command was not recognized.
	 * 
	 * @param command
	 *            the command(s) to be parsed
	 * @param breakPoints
	 *            a list of the breakpoints designated by the user
	 * @return list of Command instances that resulted from the parsing.
	 */
	public Expression parse(String command, List<Integer> breakPoints);
}
