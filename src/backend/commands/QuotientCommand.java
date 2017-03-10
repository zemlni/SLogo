package backend.commands;

import java.util.List;

import backend.BackendController;
import backend.Command;
import backend.Variable;
import backend.parser.Input;

/**
 * @author nikita This is the implementation of the Quotient command. An
 *         instance of this class gets created when the parser identifies that
 *         the user typed a Quotient command
 */
public class QuotientCommand extends Command {

	public QuotientCommand(Input in, BackendController controller) {
		super(in, controller, 2);
	}

	/**
	 * returns the quotient of the first argument and the rest of the arguments.
	 * supports unlimited parameters.
	 * 
	 * @return the quotient of the first argument and the rest of the arguments.
	 */
	@Override
	public double execute() {
		List<Variable> args = getArgs();
		double result = args.get(0).getValue();
		for (int i = 1; i < args.size(); i++)
			result /= args.get(i).getValue();
		return result;
	}
}
