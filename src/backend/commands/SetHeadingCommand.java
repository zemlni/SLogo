package backend.commands;

import backend.BackendController;
import backend.Variable;
import backend.parser.Input;

public class SetHeadingCommand extends TurtleCommand {

	public SetHeadingCommand(Input in, BackendController controller) {
		super(in, controller, 1);
	}

	/**
	 * rotate turtle by all arguments, return final angle.
	 */
	@Override
	public double execute() {
		double deltaDir = 0;
		for (Variable var : getArgs()) {
			deltaDir = var.getValue();
			rotateTurtle(deltaDir);
		}
		return deltaDir - getTurtle().getDirection();
	}

	private void rotateTurtle(double direction) {
		rotateTurtleModel(direction);
		rotateTurtleView(direction);
	}

	private void rotateTurtleView(double direction) {
		getTurtle().setDir(direction);

	}

	private void rotateTurtleModel(double direction) {
		getTurtle().getFrontController().setTurtleAngle(direction);
	}

}
