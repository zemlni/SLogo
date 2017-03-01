package backend.commands;

import backend.BackendController;
import backend.TurtleModel;

public class PenDownCommand extends TurtleCommand{

	public PenDownCommand(BackendController controller) {
		super(controller, 0);
	}

	@Override
	public double execute() {
		TurtleModel turtle = getTurtle();
		turtle.placePen();
		
		return 1;
	}

}
