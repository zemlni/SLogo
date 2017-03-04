package backend.commands;

import backend.BackendController;
import backend.parser.Input;

public class HeadingCommand extends TurtleCommand{

	public HeadingCommand(Input in, BackendController controller) {
		super(in, controller, 0);
	}

	@Override
	public double execute() {
		return getTurtle().getDirection();
	}

}
