package backend.commands;

import backend.BackendController;
import backend.TurtleModel;
import backend.parser.Input;

public class PenUpCommand extends TurtleCommand{

	public PenUpCommand(Input in, BackendController controller) {
		super(in, controller, 0);
	}

	@Override
	public double execute() {
		TurtleModel turtle = getTurtle();
		turtle.liftPen();
		
		return 1;
	}
}
