package backend.commands;

import backend.BackendController;

public class HeadingCommand extends TurtleCommand{

	public HeadingCommand(BackendController controller) {
		super(controller, 0);
	}

	@Override
	public double execute() {
		return getTurtle().getDirection();
	}

}
