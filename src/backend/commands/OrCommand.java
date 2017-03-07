package backend.commands;

import backend.BackendController;
import backend.Command;
import backend.Variable;
import backend.parser.Input;

public class OrCommand extends Command {

	public OrCommand(Input in, BackendController controller) {
		super(in, controller, 2);
	}

	/**
	 * return 1 if at least one argument is nonzero
	 */
	@Override
	public double execute() {
		for (Variable var : getArgs()) {
			if (var.getValue() != 0)
				return 1;
		}
		return 0;
		// return (getArgs().get(0).getValue() != 0 ||
		// getArgs().get(1).getValue() != 0) ? 1 : 0;
	}

}
