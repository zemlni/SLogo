package commands;

import backend.BackendController;
import backend.Command;

public class NaturalLogCommand extends Command {

	public NaturalLogCommand(BackendController controller) {
		super(controller, 1);
	}

	@Override
	public double execute() {
		return Math.log(getArgs().get(0).getValue());
	}
}