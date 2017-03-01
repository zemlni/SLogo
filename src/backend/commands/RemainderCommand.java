package backend.commands;

import backend.BackendController;
import backend.Command;

public class RemainderCommand extends Command {

	public RemainderCommand(BackendController controller) {
		super(controller, 2);
	}

	@Override
	public double execute() {
		return getArgs().get(0).getValue() % getArgs().get(1).getValue();
	}

}
