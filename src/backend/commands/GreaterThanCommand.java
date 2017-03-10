package backend.commands;

import backend.BackendController;
import backend.Command;
import backend.parser.Input;

/**
 * @author nikita This is the implementation of the GreaterThan command. An
 *         instance of this class gets created when the parser identifies that
 *         the user typed a GreaterThan command
 */
public class GreaterThanCommand extends Command {

	public GreaterThanCommand(Input in, BackendController controller) {
		super(in, controller, 2);
	}

	/**
	 * return 1 if the first argument is greater than the second. else return 0
	 * 
	 * @return 1 if the first argument is greater than the second. else return 0
	 */
	@Override
	public double execute() {
		return getArgs().get(0).getValue() > getArgs().get(1).getValue() ? 1 : 0;
	}

}
