package backend.commands;

import backend.BackendController;
import backend.Command;
import backend.parser.Input;

public class NotCommand extends Command {

	public NotCommand(Input in, BackendController controller) {
		super(in, controller, 1);
	}

	@Override
	public double execute() {
		return getArgs().get(0).getValue() == 0 ? 1 : 0;
	}

}
