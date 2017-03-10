package backend.commands;

import backend.BackendController;
import backend.Command;
import backend.parser.Input;

/**
 * @author nikita This is the implementation of the Power command. An instance
 *         of this class gets created when the parser identifies that the user
 *         typed a Power command
 */
public class PowerCommand extends Command {

	public PowerCommand(Input in, BackendController controller) {
		super(in, controller, 2);
	}

	/**
	 * evaluate the first argument to the power of the second.
	 * 
	 * @return the first argument to the power of the second.
	 */
	@Override
	public double execute() {
		return Math.pow(getArgs().get(0).getValue(), getArgs().get(1).getValue());
	}
}