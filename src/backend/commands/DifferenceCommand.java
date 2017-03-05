package backend.commands;

import backend.BackendController;
import backend.Command;
import backend.parser.Input;

public class DifferenceCommand extends Command {

	public DifferenceCommand(Input in, BackendController controller) {
		super(in, controller, 2);
	}

	@Override
	public double execute() {
		System.out.println("ARG0: " +getArgs().get(0).getValue() );
		System.out.println("ARG01: " +getArgs().get(1).getValue() );
		return getArgs().get(0).getValue() - getArgs().get(1).getValue();
	}

}
