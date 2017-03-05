package backend.commands;

import java.util.List;

import backend.BackendController;
import backend.Command;
import backend.Variable;
import backend.parser.Input;

public class RepeatCommand extends Command {

	public RepeatCommand(Input in, BackendController controller) {
		super(in, controller, 2);
	}

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
