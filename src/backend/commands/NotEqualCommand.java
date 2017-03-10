package backend.commands;

import java.util.List;

import backend.BackendController;
import backend.Command;
import backend.Variable;
import backend.parser.Input;

/**
 * @author nikita This is the implementation of the NotEqual command. An
 *         instance of this class gets created when the parser identifies that
 *         the user typed a NotEqual command
 */
public class NotEqualCommand extends Command {

	public NotEqualCommand(Input in, BackendController controller) {
		super(in, controller, 2);
	}

	/**
	 * returns 1 if none of the arguments are equal. else returns 0. supports
	 * unlimited parameters
	 * 
	 * @return 1 if none of the arguments are equal. else return 0
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
	}
}
