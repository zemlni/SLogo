package backend.commands;

import java.util.List;

import backend.BackendController;
import backend.Command;
import backend.Variable;
import backend.parser.Input;

/**
 * @author nikita This is the implementation of the Product command. An instance
 *         of this class gets created when the parser identifies that the user
 *         typed a Product command
 */
public class ProductCommand extends Command {

	public ProductCommand(Input in, BackendController controller) {
		super(in, controller, 2);
	}

	/**
	 * returns product of all arguments. supports unlimited parameters
	 * 
	 * @returns the product of all arguments
	 */
	@Override
	public double execute() {
		List<Variable> args = getArgs();
		double result = args.size() > 0 ? 1 : 0;
		for (Variable arg : args)
			result *= arg.getValue();
		return result;
	}
}
