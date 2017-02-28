package commands;

import backend.BackendController;
import backend.Command;

public class SineCommand extends Command {

	public SineCommand(BackendController controller) {
		super(controller);
		setNumArgs(1);
	}

	@Override
	public double execute() {
		return Math.sin(getArgs().get(0).getValue());
	}
}