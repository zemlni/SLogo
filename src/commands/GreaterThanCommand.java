package commands;

import backend.BackendController;
import backend.Command;

public class GreaterThanCommand extends Command {

	public GreaterThanCommand(BackendController controller) {
		super(controller, 2);
	}

	@Override
	public double execute() {
		return getArgs().get(0).getValue() > getArgs().get(1).getValue() ? 1 : 0;
	}

}
