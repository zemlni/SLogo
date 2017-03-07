package backend.commands;

import backend.BackendController;
import backend.Command;
import backend.parser.Input;

public class NaturalLogCommand extends Command {

	public NaturalLogCommand(Input in, BackendController controller) {
		super(in, controller, 1);
	}

	@Override
	public double execute() {
		return Math.log(getArgs().get(0).getValue());
	}
}