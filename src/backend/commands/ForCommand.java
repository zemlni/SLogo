package backend.commands;

import java.util.List;

import backend.BackendController;
import backend.Command;
import backend.Variable;
import backend.parser.Input;

public class ForCommand extends Command {

	public ForCommand(Input in, BackendController controller) {
		super(in, controller, 2);
	}

	@Override
	public double execute() {
		int start = (int) getChildren().get(0).getChildren().get(1).evaluate().getValue();
		int end = (int) getChildren().get(0).getChildren().get(2).evaluate().getValue();
		int increment = (int) getChildren().get(0).getChildren().get(3).evaluate().getValue();
		String name = getChildren().get(0).getChildren().get(0).evaluate().getKey();
		int i = start;
		double ret = 0;
		while ((increment >= 0 && i < end) || (increment < 0 && i > end)) {
			Variable counter = new Variable(name, i);
			getBackendController().setVariable(counter);
			List<Variable> args = getArgs();
			ret = args.get(1).getValue();
			i += increment;
		}
		getBackendController().getParser().getVariableTable().removeVariable(new Variable(name, i));
		return ret;
	}

}
