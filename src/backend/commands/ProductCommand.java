package backend.commands;

import java.util.List;

import backend.BackendController;
import backend.Command;
import backend.Variable;
import backend.parser.Input;

public class ProductCommand extends Command {

	public ProductCommand(Input in, BackendController controller) {
		super(in, controller, 2);
	}

	/**
	 * returns product of all arguments
	 */
	@Override
	public double execute() {
		List<Variable> args = getArgs();
		double result = args.size() > 0 ? 1 : 0;
		for (Variable arg : args)
			result *= arg.getValue();
		return result;
		// return getArgs().get(0).getValue() * getArgs().get(1).getValue();

	}

}
