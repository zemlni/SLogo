package commands;

import java.util.List;

import backend.BackendController;
import backend.Command;
import backend.Variable;

public class RepeatCommand extends Command {

	public RepeatCommand(BackendController controller) {
		super(controller, 2);
	}

	@Override
	public double execute() {
		List<Variable> args = getArgs();
		int amount = (int) args.get(0).getValue();
		int i = 0;
		double ret = 0;
		while (i < amount) {
			getBackendController().setVariable(new Variable("repcount", i));
			ret = getBackendController().getParser().parse(args.get(1).getInfo());
			i++;
		}
		return ret;
	}

}
