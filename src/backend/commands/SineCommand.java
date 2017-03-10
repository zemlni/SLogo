package backend.commands;

import backend.BackendController;
import backend.Command;
import backend.parser.Input;

/**
 * @author nikita This is the implementation of the Sinecommand. An instance of
 *         this class gets created when the parser identifies that the user
 *         typed a Sine command
 */
public class SineCommand extends Command {

	public SineCommand(Input in, BackendController controller) {
		super(in, controller, 1);
	}

	/**
	 * returns the sine of the argument
	 * 
	 * @return the sine of the argument
	 */
	@Override
	public double execute() {
		return Math.sin(getArgs().get(0).getValue());
	}
}