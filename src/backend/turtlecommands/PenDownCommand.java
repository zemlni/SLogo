package backend.turtlecommands;

import backend.BackendController;
import backend.parser.Input;
import backend.turtle.TurtleModel;

public class PenDownCommand extends TurtleCommand{

	public PenDownCommand(Input in, BackendController controller) {
		super(in, controller, 0);
	}

	@Override
	public double execute() {
		TurtleModel turtle = getTurtle();
		turtle.placePen();
		
		return 1;
	}

}
