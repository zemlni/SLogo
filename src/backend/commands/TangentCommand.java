package backend.commands;

import backend.BackendController;
import backend.Command;
import backend.parser.Input;

public class TangentCommand extends Command {

	public TangentCommand(Input in, BackendController controller) {
		super(in, controller, 1);
	}

	@Override
	public double execute() {
		return Math.tan(getArgs().get(0).getValue());
	}
}