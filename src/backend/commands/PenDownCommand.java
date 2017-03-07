package backend.commands;

import backend.BackendController;
import backend.TurtleModel;
import backend.parser.Input;

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
