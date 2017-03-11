package backend.commands;

import backend.BackendController;
import backend.Variable;
import backend.parser.Input;

public class HeadingCommand extends TurtleCommand{

	public HeadingCommand(Input in, BackendController controller) {
		super(in, controller, 1);
	}

	@Override
	public double execute() {
		double heading = 0;
		for(Variable var: getArgs()){
			heading = getTurtlePool().getTurtle((int)var.getValue()).getDirection();
		}
		return heading;
	}

}
