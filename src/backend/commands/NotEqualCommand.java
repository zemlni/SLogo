package backend.commands;

import java.util.List;

import backend.BackendController;
import backend.Command;
import backend.Variable;
import backend.parser.Input;

public class NotEqualCommand extends Command {

	public NotEqualCommand(Input in, BackendController controller) {
		super(in, controller, 2);
	}

	/**
	 * returns if none of the arguments are equal. results in n^2 complexity.
	 */
	@Override
	public double execute() {
		List<Variable> args = getArgs();
		for (int i = 0; i < args.size(); i++) {
			for (int j = 0; j < args.size(); j++) {
				if (i != j && args.get(i) == args.get(j))
					return 0;
			}
		}
		return 1;
		// return getArgs().get(0).getValue() != getArgs().get(1).getValue() ? 1
		// : 0;
	}

}
