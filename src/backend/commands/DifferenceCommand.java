package backend.commands;

import java.util.List;

import backend.BackendController;
import backend.Command;
import backend.Variable;
import backend.parser.Input;

/**
 * @author nikita This is the implementation of the Difference command. An
 *         instance of this class gets created when the parser identifies that
 *         the user typed a difference command
 */
public class DifferenceCommand extends Command {

	public DifferenceCommand(Input in, BackendController controller) {
		super(in, controller, 2);
	}

	/**
	 * evaluate the difference between the first argument and all subsequent
	 * arguments. supports unlimited arguments.
	 * 
	 * @return difference between first argument and all subsequent arguments
	 */
	@Override
	public double execute() {
		List<Variable> args = getArgs();
		double ret = args.get(0).getValue();
		for (int i = 1; i < args.size(); i++)
			ret -= args.get(i).getValue();
		return ret;
	}
}
