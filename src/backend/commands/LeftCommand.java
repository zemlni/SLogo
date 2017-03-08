package backend.commands;

import backend.BackendController;
import backend.TurtleModel;
import backend.Variable;
import backend.parser.Input;
import frontend.app.FrontEndController;

/**
 * @author gabriel nikita
 */
public class LeftCommand extends TurtleCommand {
	public LeftCommand(Input in, BackendController controller) {
		super(in, controller, 1);
	}

	/**
	 * turn the turtle left by all of the arguments supplied. supports unlimited
	 * parameters.
	 * 
	 * @return the value of the last argument
	 */
	@Override
	public double execute() {
		double deltaDir = 0;
		for (Variable var : getArgs()) {
			deltaDir = var.getValue();
			rotateTurtle(deltaDir);
		}
		return deltaDir;
	}

	public void rotateTurtle(double degrees) {
		TurtleModel turtle = getTurtle();
		double oldDir = turtle.getDirection();
		double newDir = calculateNewDir(oldDir, degrees);

		rotateTurtleModel(newDir, turtle);
		rotateTurtleView(newDir, turtle);
	}

	public void rotateTurtleModel(double newDir, TurtleModel turtle) {
		turtle.setDir(newDir);
	};

	public void rotateTurtleView(double newDir, TurtleModel turtle) {
		FrontEndController fcontroller = turtle.getFrontController();
		fcontroller.setTurtleAngle(newDir);
	};

	public double calculateNewDir(double oldD, double deltaD) {
		return oldD - deltaD;
	}
}
