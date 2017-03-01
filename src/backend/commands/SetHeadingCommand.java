package backend.commands;

import backend.BackendController;

public class SetHeadingCommand extends TurtleCommand{

	public SetHeadingCommand(BackendController controller) {
		super(controller, 1);
	}

	@Override
	public double execute() {
		double direction = getArgs().get(0).getValue();
		rotateTurtle(direction);
		return direction - getTurtle().getDirection();
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
