package backend.commands;

import backend.BackendController;
import backend.Command;
import backend.parser.Input;

public class SineCommand extends Command {

	public SineCommand(Input in, BackendController controller) {
		super(in, controller, 1);
	}

	@Override
	public double execute() {
		return Math.sin(getArgs().get(0).getValue());
	}
}