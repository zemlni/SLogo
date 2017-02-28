package commands;

import backend.BackendController;
import backend.Command;

public class PowerCommand extends Command {

	public PowerCommand(BackendController controller) {
		super(controller);
		setNumArgs(2);
	}

	@Override
	public double execute() {
		return Math.pow(getArgs().get(0).getValue(), getArgs().get(1).getValue());
	}
}