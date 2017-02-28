package commands;

import backend.BackendController;
import backend.Command;

public class OrCommand extends Command {

	public OrCommand(BackendController controller) {
		super(controller, 2);
	}

	@Override
	public double execute() {
		return (getArgs().get(0).getValue() != 0 || getArgs().get(1).getValue() != 0) ? 1 : 0;
	}

}
