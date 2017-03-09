package backend.turtlecommands;

import backend.BackendController;
import backend.parser.Input;

public class XCoordinateCommand extends TurtleCommand{
	/*
	 * TurtleCommand has a private TurtleModel Reference. Use getTurtle to get the model.
	 */
	public XCoordinateCommand(Input in, BackendController controller) {
		super(in, controller, 1);
	}

	/*
	 * Returns the turtle xCoordinate from the Turtle Model
	 */
	@Override
	public double execute() {
		return getTurtle().getXCoor();
	}

}
