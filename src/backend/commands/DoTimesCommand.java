package backend.commands;

import java.util.List;

import backend.BackendController;
import backend.Command;
import backend.Variable;
import backend.parser.Input;

/**
 * @author nikita This class is the implementation of the DoTimes command. An
 *         instance of this class gets created when the parser identifies that
 *         the user has typed a dotimes command.
 */
public class DoTimesCommand extends Command {

	public DoTimesCommand(Input in, BackendController controller) {
		super(in, controller, 2);
	}

	/**
	 * Execute the commands in the list given to the code in the second argument
	 * the amount of times that is given in the first argument. Update the
	 * variable given in the list in the first argument on each iteration to the
	 * number of times the loop has already iterated
	 * 
	 * @return the value of the last command executed
	 */
	@Override
	public double execute() {
		int limit = (int) getChildren().get(0).getChildren().get(1).evaluate().getValue();
		int i = 1;
		double ret = 0;
		String name = "";
		while (i <= limit) {
			Variable counter = getChildren().get(0).getChildren().get(0).evaluate();
			name = counter.getKey();
			Variable set = new Variable(name, i);
			getBackendController().setVariable(set);
			List<Variable> args = getArgs();
			ret = args.get(1).getValue();
			i++;
		}
		getBackendController().getParser().getVariableTable().removeVariable(new Variable(name, i));
		return ret;
	}
}
