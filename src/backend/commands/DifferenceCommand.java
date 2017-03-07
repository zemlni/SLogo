package backend.commands;

import java.util.List;

import backend.BackendController;
import backend.Command;
import backend.Variable;
import backend.parser.Input;

public class DifferenceCommand extends Command {

	public DifferenceCommand(Input in, BackendController controller) {
		super(in, controller, 2);
	}

	/**
	 * returns difference between first argument and all subsequent arguments
	 */
	@Override
	public double execute() {
		List<Variable> args = getArgs();
		double ret = args.get(0).getValue();
		for (int i = 1; i < args.size(); i++)
			ret -= args.get(i).getValue();
		return ret;
		// return getArgs().get(0).getValue() - getArgs().get(1).getValue();
	}

}
