package commands;

import backend.BackendController;
import backend.Command;

public class NotCommand extends Command {

	public NotCommand(BackendController controller) {
		super(controller, 1);
	}

	@Override
	public double execute() {
		return getArgs().get(0).getValue() == 0 ? 1 : 0;
	}

}
