package commands;

import backend.BackendController;
import backend.Command;

public class RandomCommand extends Command {

	public RandomCommand(BackendController controller) {
		super(controller);
		setNumArgs(1);
	}

	@Override
	public double execute() {
		return Math.random() * getArgs().get(0).getValue();
	}
}
