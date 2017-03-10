package backend.commands;

import backend.BackendController;
import backend.Command;
import backend.parser.Input;

/**
 * @author nikita This class is the implementation of the Cosine command. An
 *         instance of this class gets created when the parser identifies that
 *         the user has typed a cosine command.
 */
public class CosineCommand extends Command {

	public CosineCommand(Input in, BackendController controller) {
		super(in, controller, 1);
	}

	/**
	 * evaluate the cosine of the argument
	 * 
	 * @return the cosine of the argument
	 */
	@Override
	public double execute() {
		return Math.cos(getArgs().get(0).getValue());
	}
}