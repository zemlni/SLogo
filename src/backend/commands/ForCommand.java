package backend.commands;

import java.util.List;

import backend.BackendController;
import backend.Command;
import backend.Variable;
import backend.parser.Input;

/**
 * @author nikita This is the implementation of the For command. An instance of
 *         this class gets created when the parser identifies that the user
 *         typed a for command
 */

public class ForCommand extends Command {

	public ForCommand(Input in, BackendController controller) {
		super(in, controller, 2);
	}

	/**
	 * runs the command(s) given in the list in the second argument for each
	 * value specified in the range in the list in the first argument. returns
	 * the value of the final command executed (or 0 if no commands are
	 * executed) note, variable is assigned to each succeeding value so that it
	 * can be accessed by the command(s)
	 * 
	 * @return the value of the last command executed
	 */
	@Override
	public double execute() {
		int[] params = new int[3];
		for (int i = 1; i < params.length + 1; i++)
			params[i - 1] = (int) getChildren().get(0).getChildren().get(i).evaluate().getValue();
		String name = getChildren().get(0).getChildren().get(0).evaluate().getKey();
		int i = params[0];
		double ret = 0;
		while ((params[2] >= 0 && i < params[1]) || (params[2] < 0 && i > params[1])) {
			Variable counter = new Variable(name, i);
			getBackendController().setVariable(counter);
			List<Variable> args = getArgs();
			ret = args.get(1).getValue();
			i += params[2];
		}
		getBackendController().getParser().getVariableTable().removeVariable(new Variable(name, i));
		return ret;
	}
}
