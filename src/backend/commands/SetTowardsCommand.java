package backend.commands;

import java.util.List;

import backend.BackendController;
import backend.Variable;
import backend.parser.Input;

public class SetTowardsCommand extends TurtleCommand {

	public SetTowardsCommand(Input in, BackendController controller) {
		super(in, controller, 2);
	}

	/**
	 * sets the bearing of the turtle towards all coordinates specified by the
	 * arguments, one after the other
	 */
	@Override
	public double execute() {
		List<Variable> args = getArgs();
		double newDir = getTurtle().getDirection();
		for (int i = 0; i < args.size(); i += 2) {
			double towX = getArgs().get(i).getValue();
			double towY = getArgs().get(i + 1).getValue();
			newDir = calculateTurn(towX, towY);
			rotateTurtle(newDir);
		}
		return newDir - getTurtle().getDirection();
	}

	private void rotateTurtle(double newDir) {
		rotateTurtleModel(newDir);
		rotateTurtleView(newDir);
	}

	private void rotateTurtleModel(double newDir) {
		getTurtle().setDir(newDir);
	}

	private void rotateTurtleView(double newDir) {
		getTurtle().getFrontController().setTurtleAngle(newDir);
	}

	private double calculateTurn(double towX, double towY) {
		double currX = getTurtle().getXCoor();
		double currY = getTurtle().getYCoor();

		if (currX == towX && towY > currY) {
			return 0;
		} else if (currX == towX && towY < currY) {
			return 180;
		} else if (currY == towY && towX > currX) {
			return 90;
		} else if (currY == towY && towX < currX) {
			return 270;
		} else if (towX > currX && towY > currY) {
			return 0 + Math.atan((currX - towX) / (currY - towY));
		} else if (towX > currX && towY < currY) {
			return 180 - Math.atan((currX - towX) / (currY - towY));
		} else if (towX < currX && towY > currY) {
			return 180 + Math.atan((currX - towX) / (currY - towY));
		} else if (towX < currX && towY < currY) {
			return 360 - Math.atan((currX - towX) / (currY - towY));
		} else {
			return getTurtle().getDirection();
		}
	}

}
