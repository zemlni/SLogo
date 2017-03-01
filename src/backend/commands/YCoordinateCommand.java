package backend.commands;

import backend.BackendController;

public class YCoordinateCommand extends TurtleCommand{
	/*
	 * TurtleCommand has a private TurtleModel Reference. Use getTurtle to get the model.
	 */
	public YCoordinateCommand(BackendController controller) {
		super(controller, 0);
	}

	/*
	 * Returns the turtle yCoordinate from the Turtle Model
	 */
	@Override
	public double execute() {
		return getTurtle().getYCoor();
	}
}
