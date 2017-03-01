package backend.commands;

import backend.BackendController;
import backend.TurtleModel;

public class PenUpCommand extends TurtleCommand{

	public PenUpCommand(BackendController controller) {
		super(controller, 0);
	}

	@Override
	public double execute() {
		TurtleModel turtle = getTurtle();
		turtle.liftPen();
		
		return 1;
	}
}
