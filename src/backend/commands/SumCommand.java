package backend.commands;

import backend.BackendController;
import backend.Command;
import backend.Variable;
import backend.parser.Input;

/**
 * @author nikita This is the implementation of the Sum command. An instance of
 *         this class gets created when the parser identifies that the user
 *         typed a Sum command
 */
public class SumCommand extends Command {

	public SumCommand(Input in, BackendController controller) {
		super(in, controller, 2);
	}

	/**
	 * returns sum of all arguments. supports unlimited parameters
	 * 
	 * @return the sum of all arguments
	 */
	@Override
	public double execute() {
		double result = 0;
		for (Variable var : getArgs())
			result += var.getValue();
		return result;
	}
}
