package backend.commands;

import java.util.List;

import backend.BackendController;
import backend.Command;
import backend.Variable;

public class IfCommand extends Command {

	public IfCommand(BackendController controller) {
		super(controller, 2);
	}

	@Override
	public double execute() {
		List<Variable> args = getArgs();
		double ret = 0;
		if (args.get(0).getValue() != 0) {
			ret = getBackendController().getParser().parse(args.get(1).getInfo());
		}
		return ret;
	}

}
