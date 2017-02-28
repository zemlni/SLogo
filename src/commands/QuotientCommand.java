package commands;

import backend.BackendController;
import backend.Command;

public class QuotientCommand extends Command {

	public QuotientCommand(BackendController controller) {
		super(controller, 2);
	}

	@Override
	public double execute() {
		return getArgs().get(0).getValue() / getArgs().get(1).getValue();
	}

}
