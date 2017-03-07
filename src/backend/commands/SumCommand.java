package backend.commands;

import backend.BackendController;
import backend.Command;
import backend.Variable;
import backend.parser.Input;

public class SumCommand extends Command {

	public SumCommand(Input in, BackendController controller) {
		super(in, controller, 2);
	}

	/**
	 * returns sum of all arguments;
	 */
	@Override
	public double execute() {
		double result = 0;
		for (Variable var : getArgs())
			result += var.getValue();
		return result;
		// return getArgs().get(0).getValue() + getArgs().get(1).getValue();
	}

}
