package backend.commands;

import java.util.List;

import backend.BackendController;
import backend.Variable;
import backend.parser.Input;

public class SetPositionCommand extends TurtleCommand {

	public SetPositionCommand(Input in, BackendController controller) {
		super(in, controller, 2);
	}

	/**
	 * moves turtle to all coordinates specified by arguments, one after the
	 * other.
	 */
	@Override
	public double execute() {
		List<Variable> args = getArgs();
		double oldX = 0, oldY = 0, newX = 0, newY = 0;
		for (int i = 0; i < args.size(); i += 2) {
			oldX = getTurtle().getXCoor();
			oldY = getTurtle().getYCoor();

			newX = getArgs().get(i).getValue();
			newY = getArgs().get(i + 1).getValue();

			updateTurtle(oldX, oldY, newX, newY);
		}
		return calculateDistanceTraveled(oldX, oldY, newX, newY);
	}

	private double calculateDistanceTraveled(double oldX, double oldY, double newX, double newY) {
		double distanceTraveled = Math.sqrt(Math.pow(oldX - newX, 2) + Math.pow(oldY - newY, 2));
		return distanceTraveled;
	}

	private void updateTurtle(double oldX, double oldY, double newX, double newY) {
		updateTurtleModel(newX, newY);
		updateTurtleView(oldX, oldY, newX, newY);
	}

	private void updateTurtleModel(double newX, double newY) {
		getTurtle().setXCoor(newX);
		getTurtle().setYCoor(newY);
	}

	private void updateTurtleView(double oldX, double oldY, double newX, double newY) {
		getTurtle().getFrontController().moveTurtleTo(newX, newY);
		if (getTurtle().penDown()) {
			getTurtle().getFrontController().drawLine(oldX, oldY, newX, newY);
		}
		getTurtle().getFrontController().moveTurtleTo(newX, newY);
	}

}
