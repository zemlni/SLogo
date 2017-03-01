package backend.commands;

import backend.BackendController;
import backend.Command;

public class CosineCommand extends Command {

	public CosineCommand(BackendController controller) {
		super(controller, 1);
	}

	@Override
	public double execute() {
		return Math.cos(getArgs().get(0).getValue());
	}
}