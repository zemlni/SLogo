package backend.commands;

import backend.BackendController;
import backend.Command;
import backend.parser.Input;

public class CosineCommand extends Command {

	public CosineCommand(Input in, BackendController controller) {
		super(in, controller, 1);
	}

	@Override
	public double execute() {
		return Math.cos(getArgs().get(0).getValue());
	}
}