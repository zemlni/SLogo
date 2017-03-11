package backend.commands;


import backend.BackendController;
import backend.Variable;
import backend.commands.abstracts.SimpleFoldCommand;
import backend.parser.Input;

/**
 * @author nikita This is the implementation of the Quotient command. An
 *         instance of this class gets created when the parser identifies that
 *         the user typed a Quotient command. Supports unlimited parameters.
 */
public class QuotientCommand extends SimpleFoldCommand {

	public QuotientCommand(Input in, BackendController controller) {
		super(in, controller, 2);
	}

	/**
	 * return the quotient of the current dividend and the current divisor.
	 * 
	 * @param result
	 *            the current dividend
	 * @param curArg
	 *            the current divisor.
	 * 
	 * @return the quotient of the parameters
	 */
	@Override
	public double run(double result, Variable curArg) {
			return result / curArg.getValue();
	}
}
