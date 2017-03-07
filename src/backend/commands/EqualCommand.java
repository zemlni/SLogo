package backend.commands;

import java.util.List;

import backend.BackendController;
import backend.Command;
import backend.Variable;
import backend.parser.Input;

public class EqualCommand extends Command {

	public EqualCommand(Input in, BackendController controller) {
		super(in, controller, 2);
	}

	/**
	 * return 1 if all arguments are equal; else 0
	 */
	@Override
	public double execute() {
		List<Variable> args = getArgs();
		for (Variable arg : args) {
			if (args.get(0).getValue() != arg.getValue())
				return 0;
		}
		return 1;
		// return getArgs().get(0).getValue() == getArgs().get(1).getValue() ? 1
		// : 0;
	}

}
