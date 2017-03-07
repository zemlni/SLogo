package backend.commands;

import backend.BackendController;
import backend.Command;
import backend.parser.Input;

public class MinusCommand extends Command {

	public MinusCommand(Input in, BackendController controller) {
		super(in, controller, 1);
	}

	@Override
	public double execute() {
		return (-getArgs().get(0).getValue());
	}

}
