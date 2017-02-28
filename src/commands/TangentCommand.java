package commands;

import backend.BackendController;
import backend.Command;

public class TangentCommand extends Command {

	public TangentCommand(BackendController controller) {
		super(controller);
		setNumArgs(1);
	}

	@Override
	public double execute() {
		return Math.tan(getArgs().get(0).getValue());
	}
}