package commands_queries_turtle;

import backend.BackendController;
import backend.Parser;
import backend.TurtleModel;

public class XCoordinateCommand extends TurtleCommand{
	/*
	 * TurtleCommand has a private TurtleModel Reference. Use getTurtle to get the model.
	 */
	public XCoordinateCommand(Parser parser, BackendController controller) {
		super(parser, controller);
		setNumArgs(0);
	}

	/*
	 * Returns the turtle xCoordinate from the Turtle Model
	 */
	@Override
	public double execute() {
		return getTurtle().getXCoor();
	}

}
