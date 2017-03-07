package backend.commands;

import backend.BackendController;
import backend.Command;
import backend.parser.Input;

public class ArcTangentCommand extends Command {

	public ArcTangentCommand(Input in, BackendController controller) {
		super(in, controller, 1);
	}

	@Override
	public double execute() {
		return Math.atan(getArgs().get(0).getValue());
	}
}