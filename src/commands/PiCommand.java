package commands;

import backend.BackendController;
import backend.Command;

public class PiCommand extends Command {

	public PiCommand(BackendController controller) {
		super(controller, 0);
	}

	@Override
	public double execute() {
		return Math.PI;
	}
}