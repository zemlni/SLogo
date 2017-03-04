package backend.commands;

import backend.BackendController;
import backend.Command;
import backend.parser.Input;

public class OrCommand extends Command {

	public OrCommand(Input in, BackendController controller) {
		super(in, controller, 2);
	}

	@Override
	public double execute() {
		return (getArgs().get(0).getValue() != 0 || getArgs().get(1).getValue() != 0) ? 1 : 0;
	}

}
