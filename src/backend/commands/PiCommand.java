package backend.commands;

import backend.BackendController;
import backend.Command;
import backend.parser.Input;

public class PiCommand extends Command {

	public PiCommand(Input in, BackendController controller) {
		super(in, controller, 0);
	}

	@Override
	public double execute() {
		return Math.PI;
	}
}