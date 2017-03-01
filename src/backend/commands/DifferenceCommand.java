package backend.commands;

import backend.BackendController;
import backend.Command;

public class DifferenceCommand extends Command {

	public DifferenceCommand(BackendController controller) {
		super(controller, 2);
	}

	@Override
	public double execute() {
		return getArgs().get(0).getValue() - getArgs().get(1).getValue();
	}

}
