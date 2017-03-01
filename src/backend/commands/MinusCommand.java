package backend.commands;

import backend.BackendController;
import backend.Command;

public class MinusCommand extends Command {

	public MinusCommand(BackendController controller) {
		super(controller, 1);
	}

	@Override
	public double execute() {
		return (-getArgs().get(0).getValue());
	}

}
