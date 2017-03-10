package backend.commands;

import java.util.List;

import backend.BackendController;
import backend.Command;
import backend.Variable;
import backend.parser.Input;

/**
 * @author nikita This is the implementation of the Equal command. An instance
 *         of this class gets created when the parser identifies that the user
 *         typed a equal command
 */

public class EqualCommand extends Command {

	public EqualCommand(Input in, BackendController controller) {
		super(in, controller, 2);
	}

	/**
	 * Return 1 if all arguments are equal; else 0. supports unlimited
	 * parameters.
	 * 
	 * @return 1 if all arguments are equal; else 0
	 */
	@Override
	public double execute() {
		List<Variable> args = getArgs();
		for (Variable arg : args) {
			if (args.get(0).getValue() != arg.getValue())
				return 0;
		}
		return 1;
	}
}
