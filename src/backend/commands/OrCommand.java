package backend.commands;

import backend.BackendController;
import backend.Command;
import backend.Variable;
import backend.parser.Input;

/**
 * @author nikita This is the implementation of the Or command. An instance of
 *         this class gets created when the parser identifies that the user
 *         typed a Or command
 */
public class OrCommand extends Command {

	public OrCommand(Input in, BackendController controller) {
		super(in, controller, 2);
	}

	/**
	 * return 1 if at least one argument is nonzero. else return 0 supports
	 * unlimited parameters.
	 * 
	 * @return 1 if at least one argument is nonzero else return 0.
	 */
	@Override
	public double execute() {
		for (Variable var : getArgs()) {
			if (var.getValue() != 0)
				return 1;
		}
		return 0;
	}
}
