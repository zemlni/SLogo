package backend.commands;

import backend.BackendController;
import backend.Variable;
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
		double xCoor = 0;
		for(Variable var: getArgs()){
			xCoor = getTurtlePool().getTurtle((int)var.getValue()).getXCoor();
		}
		return xCoor;
	}

}
