package backend.commands;

import backend.BackendController;
import backend.Command;
import backend.parser.Input;

/**
 * @author nikita This is the implementation of the Minus command. An
 *         instance of this class gets created when the parser identifies that
 *         the user typed a Minus command
 */
public class MinusCommand extends Command {

	public MinusCommand(Input in, BackendController controller) {
		super(in, controller, 1);
	}

	/**
	 * returns the negative of the argument.
	 * 
	 * @return the negative of the argument
	 */
	@Override
	public double execute() {
		return (-getArgs().get(0).getValue());
	}
}
