package commands;

import backend.BackendController;
import backend.Command;
import backend.Parser;

public class SumCommand extends Command {

	public SumCommand(BackendController controller) {
		super(controller);
		setNumArgs(2);
	}

	@Override
	public double execute() {
		return getArgs().get(0).getValue() + getArgs().get(1).getValue();
	}

}
