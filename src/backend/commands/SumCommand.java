package backend.commands;

import backend.BackendController;
import backend.Command;
import backend.Variable;
import backend.commands.abstracts.SimpleFoldCommand;
import backend.parser.Input;

/**
 * @author nikita This is the implementation of the Sum command. An instance of
 *         this class gets created when the parser identifies that the user
 *         typed a Sum command
 */
public class SumCommand extends SimpleFoldCommand {

	public SumCommand(Input in, BackendController controller) {
		super(in, controller, 2);
	}

	@Override
	public double run(double result, Variable curArg) {
		return result + curArg.getValue();
	}
}
