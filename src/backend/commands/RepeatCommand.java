package backend.commands;

import java.util.List;

import backend.BackendController;
import backend.Command;
import backend.Variable;
import backend.parser.Input;

/**
 * @author nikita This is the implementation of the Repeat command. An instance
 *         of this class gets created when the parser identifies that the user
 *         typed a Repeat command
 */
public class RepeatCommand extends Command {

	public RepeatCommand(Input in, BackendController controller) {
		super(in, controller, 2);
	}

	/**
	 * repeat the commands given in the list in the second argument the amount
	 * of times given in the first argument. the variable repcount is added to
	 * the variable table and made available to the commands.
	 * 
	 * @return the value of the last executed command or 0 if no commands were
	 *         executed
	 */
	@Override
	public double execute() {
		int amount = (int) getChildren().get(0).evaluate().getValue();
		int i = 0;
		double ret = 0;
		while (i < amount) {
			getBackendController().setVariable(new Variable("repcount", i));
			List<Variable> args = getArgs();
			ret = args.get(1).getValue();
			i++;
		}
		getBackendController().getParser().getVariableTable().removeVariable(new Variable("repcount", i));
		return ret;
	}
}
