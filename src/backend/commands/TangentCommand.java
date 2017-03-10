package backend.commands;

import backend.BackendController;
import backend.Command;
import backend.parser.Input;

/**
 * @author nikita This is the implementation of the Tangent command. An instance
 *         of this class gets created when the parser identifies that the user
 *         typed a Tangent command
 */
public class TangentCommand extends Command {

	public TangentCommand(Input in, BackendController controller) {
		super(in, controller, 1);
	}

	/**
	 * evaluate the tangent of the argument
	 * 
	 * @return the tangent of the argument
	 */
	@Override
	public double execute() {
		return Math.tan(getArgs().get(0).getValue());
	}
}