package backend.commands;

import backend.BackendController;
import backend.Command;
import backend.Variable;
import backend.parser.Input;

/**
 * @author nikita This is the class for and command. This class is created when
 *         an and command is typed by the user.
 */
public class AndCommand extends Command {

	public AndCommand(Input in, BackendController controller) {
		super(in, controller, 2);
	}

	/**
	 * executes the AND of all of the arguments (min 2). supports unlimited
	 * parameters.
	 * 
	 * @return 0 if any of the arguments are 0 else 1
	 */
	@Override
	public double execute() {
		for (Variable var : getArgs()) {
			if (var.getValue() == 0)
				return 0;
		}
		return 1;
	}
}
