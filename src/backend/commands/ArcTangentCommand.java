package backend.commands;

import backend.BackendController;
import backend.Command;

public class ArcTangentCommand extends Command {

	public ArcTangentCommand(BackendController controller) {
		super(controller, 1);
	}

	@Override
	public double execute() {
		return Math.atan(getArgs().get(0).getValue());
	}
}