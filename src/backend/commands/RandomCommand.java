package backend.commands;

import backend.BackendController;
import backend.Command;
import backend.parser.Input;

/**
 * @author nikita This is the implementation of the Random command. An instance
 *         of this class gets created when the parser identifies that the user
 *         typed a Random command
 */
public class RandomCommand extends Command {

	public RandomCommand(Input in, BackendController controller) {
		super(in, controller, 1);
	}

	/**
	 * return a random value within the range [0, first argument)
	 * 
	 * @return a random value within the range [0, first argument)
	 */
	@Override
	public double execute() {
		return Math.random() * getArgs().get(0).getValue();
	}
}
