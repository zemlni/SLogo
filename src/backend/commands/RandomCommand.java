package backend.commands;

import backend.BackendController;
import backend.Command;
import backend.parser.Input;

public class RandomCommand extends Command {

	public RandomCommand(Input in, BackendController controller) {
		super(in, controller, 1);
	}

	@Override
	public double execute() {
		return Math.random() * getArgs().get(0).getValue();
	}
}
